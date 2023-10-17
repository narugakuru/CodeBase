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

data1 = pd.read_excel("./data/��1-�����б��ٴ���Ϣ.xlsx")
data2 = pd.read_excel("./data/��2-����Ӱ����ϢѪ�׼�ˮ�׵������λ��.xlsx")

data1


data2


# �ϲ����������Ժ�״�Ӱ������ˮ��,�״μ����ˮ��
merged_data = pd.merge(data2, data1[['��Ժ�״�Ӱ������ˮ��', '�������״�Ӱ����ʱ����']],
                       left_on='�״μ����ˮ��', right_on='��Ժ�״�Ӱ������ˮ��', how='left')
merged_data = merged_data.drop_duplicates(subset=['�״μ����ˮ��'], keep='first')
merged_data


merged_data = merged_data.iloc[:100, :]
filtered_data = merged_data.loc[:, ['ID', 'ED_volume', '�������״�Ӱ����ʱ����']]
# ����
# filtered_data.to_excel('./data/p2_a_data.xlsx')
filtered_data


filtered_data = pd.read_excel('./data/p2_a_data.xlsx')
# ����plt
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

X = filtered_data['�������״�Ӱ����ʱ����'].values.reshape(-1, 1)
y = filtered_data['ED_volume'].values


# ������14�ζ���ʽ�ع�ģ��
degrees = list(range(1, 15))
models = []
r2_scores = []
# ���ɶ���ʽ������ѵ�����Իع�
for degree in degrees:

    poly = PolynomialFeatures(degree=degree, include_bias=False)
    X_poly = poly.fit_transform(X)

    model = LinearRegression().fit(X_poly, y)
    y_pred = model.predict(X_poly)

    r2 = r2_score(y, y_pred)

    models.append(model)
    r2_scores.append(r2)


# ����R^2ֵѡ�����ģ��
best_degree = degrees[np.argmax(r2_scores)]
best_model = models[np.argmax(r2_scores)]

w = best_model.coef_
b = best_model.intercept_
formula = [f"{w[i]:.4f}x^{i+1}" for i in range(len(w))]
formula = "y = " + " + ".join(formula) + f" + {b:.4f}"
print(formula)


# ��ͼ
plt.figure(figsize=(10, 5))
plt.scatter(X, y, color='green', label='ʵ������')
# 100��
X_range = np.linspace(X.min(), X.max(), 100).reshape(-1, 1)
Y_range = best_model.predict(PolynomialFeatures(degree=best_degree,
                                                include_bias=False).fit_transform(X_range))
plt.plot(X_range, Y_range, color='red', label=f'�����ϣ�{best_degree}�ζ���ʽ��')
plt.xlabel('�������״�Ӱ����ʱ����')
plt.ylabel('ED_volume')
plt.legend()
plt.title(f'��Ѷ���ʽ��ϣ�{best_degree}�ζ���ʽ�� (R^2 = {max(r2_scores):.4f})')
plt.grid(True)
plt.show()
plt.savefig('./data/p2_a_����ʽ�ع�.jpg')


# ������ģ�͵Ľ��������R^2ֵ
best_degree, max(r2_scores), y


# ����вʵ��ֵ��Ԥ��ֵ֮��Ĳ��죩
y_pred = best_model.predict(PolynomialFeatures(
    degree=best_degree, include_bias=False).fit_transform(X_range))
residuals = y - y_pred


# ���в�洢��һ�����ݿ���
residuals_data = filtered_data.copy()
residuals_data['�в�'] = residuals
residuals_data[['ID', 'ED_volume', '�������״�Ӱ����ʱ����', '�в�']].head()


residuals_data.to_excel('./data/p2_a_�в�.xlsx')
residuals_data.head()


# ����
# ѡ������'�������״�Ӱ����ʱ����' �� 'ED_volume'
clustering_data = filtered_data[['�������״�Ӱ����ʱ����', 'ED_volume']]

# ��׼����������
scaler = StandardScaler()
# ִ��
clustering_data_scaled = scaler.fit_transform(clustering_data)


warnings.filterwarnings('ignore')
# ����Ѱ����Ѿ�������
inertia = []  # �洢���ԣ�inertia��ֵ
K = range(1, 10)  # ���Եľ���������Χ

# ������ͬ�ľ�������
for k in K:
    # ���K��ֵ����ģ��
    kmeans = KMeans(n_clusters=k, random_state=42).fit(clustering_data_scaled)
    # ���㲢��¼��ģ�͵Ĺ���ֵ
    inertia.append(kmeans.inertia_)


plt.figure(figsize=(8, 5))  # ����ͼ�εĳߴ�
plt.plot(K, inertia, 'bx-')  # �����ⲿ�������ߣ�x��Ϊ����������y��Ϊ����ֵ
plt.xlabel('�������� (k)')  # ����x���ǩ
plt.ylabel('����ֵ')  # ����y���ǩ
plt.title('Kֵ���Ա仯')  # ����ͼ�α���
plt.grid(True)  # ��ʾ������
plt.show()  # ��ʾͼ��


select_clusters = 3
kmeans = KMeans(n_clusters=select_clusters, random_state=42).fit(
    clustering_data_scaled)
# ��ȡ���������Ĵر�ǩ���洢�����ǩ
filtered_data['Cluster'] = kmeans.labels_


# ���ƾ�����
plt.figure(figsize=(10, 6))  # ����ͼ�εĳߴ�
for i in range(select_clusters):
    cluster_data = filtered_data[filtered_data['Cluster'] == i]  # ��ȡ���ڵ�ǰ�ص�����
    plt.scatter(cluster_data['�������״�Ӱ����ʱ����'],
                cluster_data['ED_volume'], label=f'�� {i+1}')  # ����ɢ��ͼ��ÿ����һ����ɫ
plt.xlabel('�������״�Ӱ����ʱ����(Сʱ)')  # ����x���ǩ
plt.ylabel('ED_volume')  # ����y���ǩ
plt.legend()  # ��ʾͼ��
plt.title('���߲�ͬ��Ⱥ����')  # ����ͼ�α���
plt.grid(True)  # ��ʾ������
plt.show()  # ��ʾͼ��


# Ϊÿ������϶���ʽ�ع�ģ��
cluster_models = []  # �洢ÿ���صĻع�ģ��
cluster_r2_scores = []  # �洢ÿ���ص�R^2����
all_cluster_data = pd.DataFrame()  # ����һ���µ����ݿ�


# �ظ�ab������
# ����һ�� 2x2 ����ͼ���֣�����������
fig, axes = plt.subplots(2, 2, figsize=(12, 12))

for i in range(select_clusters):
    cluster_data = filtered_data[filtered_data['Cluster'] == i]  # ��ȡ���ڵ�ǰ�ص�����

    # ��ȡ����
    X = cluster_data['�������״�Ӱ����ʱ����'].values.reshape(-1, 1)
    y = cluster_data['ED_volume'].values

    # �������1��14�ζ���ʽ�ع�ģ��
    degrees = list(range(1, 15))  # ���ԵĶ���ʽ������Χ
    models = []  # �洢��ͬ�����Ļع�ģ��
    r2_scores = []  # �洢��ͬ������R^2����
    for degree in degrees:
        poly = PolynomialFeatures(degree=degree, include_bias=False)
        X_poly = poly.fit_transform(X)

        model = LinearRegression().fit(X_poly, y)
        y_pred = model.predict(X_poly)

        r2 = r2_score(y, y_pred)

        models.append(model)
        r2_scores.append(r2)

    # ����R^2ֵѡ�����ģ��
    best_degree = degrees[np.argmax(r2_scores)]
    best_model = models[np.argmax(r2_scores)]

    # ��ȡģ�͵�ϵ���ͽؾ�
    coefficients = best_model.coef_
    intercept = best_model.intercept_

    # ��������ʽ����
    formula_terms = [
        f"{coefficients[i]:.4f}x^{i+1}" for i in range(len(coefficients))]
    formula = "y = " + " + ".join(formula_terms) + f" + {intercept:.4f}"

    print(formula)

    # ����Ӧ����ͼ�л���ͼ��
    row = i // 2  # ������
    col = i % 2  # ������
    ax = axes[row, col]

    ax.scatter(X, y, color='blue', label='ʵ������')
    X_range = np.linspace(X.min(), X.max(), 100).reshape(-1, 1)
    ax.plot(X_range, best_model.predict(PolynomialFeatures(degree=best_degree,
                                                           include_bias=False).fit_transform(X_range)), color='red', label=f'�����ϣ�{best_degree}�ζ���ʽ��')
    ax.set_xlabel('�������״�Ӱ����ʱ����')
    ax.set_ylabel('ED_volume')
    ax.legend()
    ax.set_title(f'��Ѷ���ʽ��ϣ�{best_degree}�ζ���ʽ�� (R^2 = {max(r2_scores):.4f})')
    ax.grid(True)

    cluster_data['predic'] = best_model.predict(PolynomialFeatures(
        degree=best_degree, include_bias=False).fit_transform(X))  # ���Ԥ��ֵ��
    all_cluster_data = pd.concat([all_cluster_data, cluster_data])
# ������ͼ֮��ļ��
plt.tight_layout()
plt.show()
# ����ͼƬ
plt.savefig('./data/p2_b_����ʽ�ع�.jpg')


all_cluster_data = all_cluster_data.sort_values('ID')
all_cluster_data


# ����вʵ��ֵ��Ԥ��ֵ֮��Ĳ��죩
residuals = all_cluster_data['ED_volume'] - all_cluster_data['predic']

# ���в�洢��һ�����ݿ���
residuals_data = filtered_data.copy()  # ����ɸѡ������ݵ�һ���µ����ݿ�
residuals_data['�в�'] = residuals  # ������õ��Ĳв����Ϊ���� 'Residuals'

# ѡ����ʾ����ID��ED_volume���������״�Ӱ����ʱ�����Լ��в����
residuals_data[['ID', 'ED_volume', '�������״�Ӱ����ʱ����', '�в�']]


residuals_data.to_excel('./data/p2_b_�в�_3����.xlsx')

residuals_data['�в�'].abs().sum()


r2_scores = r2_score(all_cluster_data['ED_volume'], all_cluster_data['predic'])
r2_scores


# Ԥ���ʵ�ʵ�ɢ��ͼ
plt.figure(figsize=(10, 6))
plt.scatter(all_cluster_data['�������״�Ӱ����ʱ����'], all_cluster_data['ED_volume'],
            color='blue', label='Actual data')
plt.scatter(all_cluster_data['�������״�Ӱ����ʱ����'],
            all_cluster_data['predic'], color='red')
plt.xlabel('�������״�Ӱ����ʱ����')
plt.ylabel('ED_volume')
plt.legend()

plt.grid(True)
plt.show()
