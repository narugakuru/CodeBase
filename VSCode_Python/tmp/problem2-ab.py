from scipy.spatial.distance import cdist
from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
import warnings
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
from sklearn.metrics import r2_score
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

data1 = pd.read_excel("./data/表1-患者列表及临床信息.xlsx")
data2 = pd.read_excel("./data/表2-患者影像信息血肿及水肿的体积及位置.xlsx")

data1


data2


# 合并两个表格，入院首次影像检查流水号,首次检查流水号
merged_data = pd.merge(data2, data1[['入院首次影像检查流水号', '发病到首次影像检查时间间隔']],
                       left_on='首次检查流水号', right_on='入院首次影像检查流水号', how='left')
merged_data = merged_data.drop_duplicates(subset=['首次检查流水号'], keep='first')
merged_data


merged_data = merged_data.iloc[:100, :]
filtered_data = merged_data.loc[:, ['ID', 'ED_volume', '发病到首次影像检查时间间隔']]
# 保存
# filtered_data.to_excel('./data/p2_a_data.xlsx')
filtered_data


filtered_data = pd.read_excel('./data/p2_a_data.xlsx')
# 配置plt
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

X = filtered_data['发病到首次影像检查时间间隔'].values.reshape(-1, 1)
y = filtered_data['ED_volume'].values


# 尝试拟14次多项式回归模型
degrees = list(range(1, 15))
models = []
r2_scores = []
# 生成多项式特征，训练线性回归
for degree in degrees:

    poly = PolynomialFeatures(degree=degree, include_bias=False)
    X_poly = poly.fit_transform(X)

    model = LinearRegression().fit(X_poly, y)
    y_pred = model.predict(X_poly)

    r2 = r2_score(y, y_pred)

    models.append(model)
    r2_scores.append(r2)


# 根据R^2值选择最佳模型
best_degree = degrees[np.argmax(r2_scores)]
best_model = models[np.argmax(r2_scores)]

w = best_model.coef_
b = best_model.intercept_
formula = [f"{w[i]:.4f}x^{i+1}" for i in range(len(w))]
formula = "y = " + " + ".join(formula) + f" + {b:.4f}"
print(formula)


# 绘图
plt.figure(figsize=(10, 5))
plt.scatter(X, y, color='green', label='实际数据')
# 100组
X_range = np.linspace(X.min(), X.max(), 100).reshape(-1, 1)
Y_range = best_model.predict(PolynomialFeatures(degree=best_degree,
                                                include_bias=False).fit_transform(X_range))
plt.plot(X_range, Y_range, color='red', label=f'最佳拟合（{best_degree}次多项式）')
plt.xlabel('发病到首次影像检查时间间隔')
plt.ylabel('ED_volume')
plt.legend()
plt.title(f'最佳多项式拟合（{best_degree}次多项式） (R^2 = {max(r2_scores):.4f})')
plt.grid(True)
plt.show()
plt.savefig('./data/p2_a_多项式回归.jpg')


# 输出最佳模型的阶数和最大R^2值
best_degree, max(r2_scores), y


# 计算残差（实际值与预测值之间的差异）
y_pred = best_model.predict(PolynomialFeatures(
    degree=best_degree, include_bias=False).fit_transform(X_range))
residuals = y - y_pred


# 将残差存储在一个数据框中
residuals_data = filtered_data.copy()
residuals_data['残差'] = residuals
residuals_data[['ID', 'ED_volume', '发病到首次影像检查时间间隔', '残差']].head()


residuals_data.to_excel('./data/p2_a_残差.xlsx')
residuals_data.head()


# 聚类
# 选择特征'发病到首次影像检查时间间隔' 和 'ED_volume'
clustering_data = filtered_data[['发病到首次影像检查时间间隔', 'ED_volume']]

# 标准化特征处理
scaler = StandardScaler()
# 执行
clustering_data_scaled = scaler.fit_transform(clustering_data)


warnings.filterwarnings('ignore')
# 遍历寻找最佳聚类数量
inertia = []  # 存储惯性（inertia）值
K = range(1, 10)  # 尝试的聚类数量范围

# 遍历不同的聚类数量
for k in K:
    # 拟合K均值聚类模型
    kmeans = KMeans(n_clusters=k, random_state=42).fit(clustering_data_scaled)
    # 计算并记录该模型的惯性值
    inertia.append(kmeans.inertia_)


plt.figure(figsize=(8, 5))  # 设置图形的尺寸
plt.plot(K, inertia, 'bx-')  # 绘制肘部法则曲线，x轴为聚类数量，y轴为惯性值
plt.xlabel('聚类数量 (k)')  # 设置x轴标签
plt.ylabel('惯性值')  # 设置y轴标签
plt.title('K值惯性变化')  # 设置图形标题
plt.grid(True)  # 显示网格线
plt.show()  # 显示图形


select_clusters = 3
kmeans = KMeans(n_clusters=select_clusters, random_state=42).fit(
    clustering_data_scaled)
# 获取数据所属的簇标签，存储聚类标签
filtered_data['Cluster'] = kmeans.labels_


# 绘制聚类结果
plt.figure(figsize=(10, 6))  # 设置图形的尺寸
for i in range(select_clusters):
    cluster_data = filtered_data[filtered_data['Cluster'] == i]  # 获取属于当前簇的数据
    plt.scatter(cluster_data['发病到首次影像检查时间间隔'],
                cluster_data['ED_volume'], label=f'簇 {i+1}')  # 绘制散点图，每个簇一个颜色
plt.xlabel('发病到首次影像检查时间间隔(小时)')  # 设置x轴标签
plt.ylabel('ED_volume')  # 设置y轴标签
plt.legend()  # 显示图例
plt.title('患者不同人群聚类')  # 设置图形标题
plt.grid(True)  # 显示网格线
plt.show()  # 显示图形


# 为每个簇拟合多项式回归模型
cluster_models = []  # 存储每个簇的回归模型
cluster_r2_scores = []  # 存储每个簇的R^2分数
all_cluster_data = pd.DataFrame()  # 创建一个新的数据框


# 重复ab的做法
# 创建一个 2x2 的子图布局，即两行两列
fig, axes = plt.subplots(2, 2, figsize=(12, 12))

for i in range(select_clusters):
    cluster_data = filtered_data[filtered_data['Cluster'] == i]  # 获取属于当前簇的数据

    # 获取特征
    X = cluster_data['发病到首次影像检查时间间隔'].values.reshape(-1, 1)
    y = cluster_data['ED_volume'].values

    # 尝试拟合1到14次多项式回归模型
    degrees = list(range(1, 15))  # 尝试的多项式阶数范围
    models = []  # 存储不同阶数的回归模型
    r2_scores = []  # 存储不同阶数的R^2分数
    for degree in degrees:
        poly = PolynomialFeatures(degree=degree, include_bias=False)
        X_poly = poly.fit_transform(X)

        model = LinearRegression().fit(X_poly, y)
        y_pred = model.predict(X_poly)

        r2 = r2_score(y, y_pred)

        models.append(model)
        r2_scores.append(r2)

    # 根据R^2值选择最佳模型
    best_degree = degrees[np.argmax(r2_scores)]
    best_model = models[np.argmax(r2_scores)]

    # 提取模型的系数和截距
    coefficients = best_model.coef_
    intercept = best_model.intercept_

    # 创建多项式方程
    formula_terms = [
        f"{coefficients[i]:.4f}x^{i+1}" for i in range(len(coefficients))]
    formula = "y = " + " + ".join(formula_terms) + f" + {intercept:.4f}"

    print(formula)

    # 在相应的子图中绘制图形
    row = i // 2  # 行索引
    col = i % 2  # 列索引
    ax = axes[row, col]

    ax.scatter(X, y, color='blue', label='实际数据')
    X_range = np.linspace(X.min(), X.max(), 100).reshape(-1, 1)
    ax.plot(X_range, best_model.predict(PolynomialFeatures(degree=best_degree,
                                                           include_bias=False).fit_transform(X_range)), color='red', label=f'最佳拟合（{best_degree}次多项式）')
    ax.set_xlabel('发病到首次影像检查时间间隔')
    ax.set_ylabel('ED_volume')
    ax.legend()
    ax.set_title(f'最佳多项式拟合（{best_degree}次多项式） (R^2 = {max(r2_scores):.4f})')
    ax.grid(True)

    cluster_data['predic'] = best_model.predict(PolynomialFeatures(
        degree=best_degree, include_bias=False).fit_transform(X))  # 添加预测值列
    all_cluster_data = pd.concat([all_cluster_data, cluster_data])
# 调整子图之间的间距
plt.tight_layout()
plt.show()
# 保存图片
plt.savefig('./data/p2_b_多项式回归.jpg')


all_cluster_data = all_cluster_data.sort_values('ID')
all_cluster_data


# 计算残差（实际值与预测值之间的差异）
residuals = all_cluster_data['ED_volume'] - all_cluster_data['predic']

# 将残差存储在一个数据框中
residuals_data = filtered_data.copy()  # 复制筛选后的数据到一个新的数据框
residuals_data['残差'] = residuals  # 将计算得到的残差添加为新列 'Residuals'

# 选择并显示包括ID、ED_volume、发病到首次影像检查时间间隔以及残差的列
residuals_data[['ID', 'ED_volume', '发病到首次影像检查时间间隔', '残差']]


residuals_data.to_excel('./data/p2_b_残差_3分类.xlsx')

residuals_data['残差'].abs().sum()


r2_scores = r2_score(all_cluster_data['ED_volume'], all_cluster_data['predic'])
r2_scores


# 预测和实际的散点图
plt.figure(figsize=(10, 6))
plt.scatter(all_cluster_data['发病到首次影像检查时间间隔'], all_cluster_data['ED_volume'],
            color='blue', label='Actual data')
plt.scatter(all_cluster_data['发病到首次影像检查时间间隔'],
            all_cluster_data['predic'], color='red')
plt.xlabel('发病到首次影像检查时间间隔')
plt.ylabel('ED_volume')
plt.legend()

plt.grid(True)
plt.show()
