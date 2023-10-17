from sklearn.metrics import r2_score, mean_absolute_error, mean_squared_error, classification_report
from sklearn.metrics import confusion_matrix, classification_report, roc_curve, roc_auc_score, auc, accuracy_score, recall_score, f1_score
from math import sqrt
from sklearn.ensemble import RandomForestRegressor, AdaBoostRegressor, GradientBoostingRegressor
from sklearn.tree import DecisionTreeRegressor
import xgboost as xgb
import joblib
from sklearn.preprocessing import StandardScaler, OneHotEncoder
from sklearn.impute import SimpleImputer
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from lightgbm import LGBMRegressor
import matplotlib.pyplot as plt
import numpy as np
import warnings
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.model_selection import train_test_split
import pandas as pd
data1 = pd.read_excel("./data/表1-患者列表及临床信息.xlsx")
data2 = pd.read_excel("./data/表2-患者影像信息血肿及水肿的体积及位置.xlsx")
data3_ED = pd.read_excel("./data/表3-患者影像信息血肿及水肿的形状及灰度分布.xlsx", sheet_name='ED')
data3_Hemo = pd.read_excel(
    "./data/表3-患者影像信息血肿及水肿的形状及灰度分布.xlsx", sheet_name='Hemo')


data1.head(), data2.head(), data3_ED.head(), data3_Hemo.head()

# AB问仅数据处理特征提取不同
# 根据患者ID和流水号合并三个数据框
# 第三问B
merged_data = data1.merge(data2[data2.columns], left_on=[
                          'Unnamed: 0', '入院首次影像检查流水号'], right_on=['ID', '首次检查流水号'], how='left')
# 第三问A
# merged_data = data1.merge(data2[data2.columns[:24]], left_on=['Unnamed: 0', '入院首次影像检查流水号'], right_on=['ID', '首次检查流水号'], how='left')
merged_data = merged_data.merge(
    data3_ED, left_on='入院首次影像检查流水号', right_on='流水号', how='left')
merged_data = merged_data.merge(
    data3_Hemo, left_on='入院首次影像检查流水号', right_on='流水号', how='left')
# data = data.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)
data = merged_data
data.columns


merged_data = data1.merge(data2[data2.columns], left_on=[
                          'Unnamed: 0', '入院首次影像检查流水号'], right_on=['ID', '首次检查流水号'], how='left')
merged_data = merged_data.merge(
    data3_ED, left_on='入院首次影像检查流水号', right_on='流水号', how='left')
merged_data = merged_data.merge(
    data3_Hemo, left_on='入院首次影像检查流水号', right_on='流水号', how='left')
# 删除多余列
data = data.drop(columns=['ID', '入院首次影像检查流水号',
                 '首次检查流水号', '备注_x', '备注_y', '流水号_x', '流水号_y'])
data


merged_data = pd.read_excel("./data/merged_all.xlsx")
# 患者个人信息
columns_a = merged_data.columns[:21].to_list()
tmp_a = ['HM_volume', 'ED_volume', '血压_最大值', '血压_最小值']

for i in tmp_a:
    columns_a.append(i)
data_a = merged_data[columns_a]
# 影像信息
data_a


# 查看缺失值
index_list = []
for column in data.columns:
    if data[column].isnull().sum() > 0:
        index_list.append(column)

data[index_list].isnull().sum()


# 均值填充,除了90天mRS
for index in index_list[1:]:
    data[index] = data[index].fillna(data[index].mean())

data[index_list].isnull().sum()


# 数据预处理
data[['性别', '血压']]
data['性别'] = data['性别'].map(
    {
        '女': 0,
        '男': 1
    })
data['血压_最大值'] = data['血压'].apply(lambda x: int(x.split('/')[0]))
data['血压_最小值'] = data['血压'].apply(lambda x: int(x.split('/')[1]))
del data['数据集划分']
del data['血压']


warnings.filterwarnings('ignore')


# # 定义两种数据的处理方式
# numeric_transformer = Pipeline(steps=[
#     ('imputer', SimpleImputer(strategy='mean')),
#     ('scaler', StandardScaler())
# ])
# categorical_transformer = Pipeline(steps=[
#     ('imputer', SimpleImputer(strategy='constant', fill_value='missing')),
#     ('onehot', OneHotEncoder(handle_unknown='ignore'))
# ])
# # 定义处理器
# preprocessor = ColumnTransformer(
#     transformers=[
#         ('num', numeric_transformer, numeric_cols),
#         ('cat', categorical_transformer, categorical_cols)
#     ])


# A的数据预处理
numeric_cols = data.columns.tolist()
categorical_cols = ['性别', '脑出血前mRS评分', '高血压病史', '卒中病史', '糖尿病史', '房颤史', '冠心病史',
                    '吸烟史', '饮酒史', '脑室引流', '止血治疗', '降颅压治疗', '降压治疗', '镇静、镇痛治疗', '止吐护胃', '营养神经']
remove_cols = ['Unnamed: 0', '90天mRS']
for i in categorical_cols:
    numeric_cols.remove(i)
for i in remove_cols:
    numeric_cols.remove(i)

numeric_cols


onehot = OneHotEncoder(handle_unknown='ignore')
scaler = StandardScaler()
data[numeric_cols] = scaler.fit_transform(data[numeric_cols])
# data.loc[:,categorical_cols]= onehot.fit_transform(data.loc[:,categorical_cols])
data = pd.get_dummies(data, columns=categorical_cols)
data


# 把90天mRS的划分用于训练测试
train_data = data[data['90天mRS'].notnull()]
X = train_data.drop(columns=['90天mRS', 'Unnamed: 0'])
Y = train_data['90天mRS']
train_X, test_X, train_y, test_y = train_test_split(
    X, Y, test_size=0.1, random_state=148)
train_X


warnings.filterwarnings('ignore')
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False


# 构建xgboost回归模型
xgb_model = xgb.XGBRegressor()
xgb_model.fit(train_X, train_y)
xgb_y_pred = xgb_model.predict(test_X)
xgb_y_pred = np.round(xgb_y_pred, 0).astype(int)

# 输出xgboost模型评价结果
print('XGBoost模型评价结果：')
print('R2 Score:', r2_score(test_y, xgb_y_pred))
print('MAE:', mean_absolute_error(test_y, xgb_y_pred))
print('MSE:', mean_squared_error(test_y, xgb_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, xgb_y_pred)))


# 构建随机森林回归模型
rf_model = RandomForestRegressor(
    n_estimators=100, max_depth=3, random_state=42)
rf_model.fit(train_X, train_y)
rf_y_pred = rf_model.predict(test_X)
rf_y_pred = np.round(rf_y_pred, 0).astype(int)
# 输出随机森林模型评价结果
print('随机森林模型评价结果：')
print('R2 Score:', r2_score(test_y, rf_y_pred))
print('MAE:', mean_absolute_error(test_y, rf_y_pred))
print('MSE:', mean_squared_error(test_y, rf_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, rf_y_pred)))

# 构建LGBM回归模型
lgbm_model = LGBMRegressor()
lgbm_model.fit(train_X, train_y)
lgbm_y_pred = lgbm_model.predict(test_X)

lgbm_y_pred = np.round(lgbm_y_pred, 0).astype(int)
# 输出LGBM模型评价结果
print('LGBM模型评价结果：')
print('R2 Score:', r2_score(test_y, lgbm_y_pred))
print('MAE:', mean_absolute_error(test_y, lgbm_y_pred))
print('MSE:', mean_squared_error(test_y, lgbm_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, lgbm_y_pred)))

# 构建Adaboost回归模型
ada_model = AdaBoostRegressor(n_estimators=100)
ada_model.fit(train_X, train_y)
ada_y_pred = ada_model.predict(test_X)
ada_y_pred = np.round(ada_y_pred, 0).astype(int)
# 输出Adaboost模型评价结果
print('Adaboost模型评价结果：')
print('R2 Score:', r2_score(test_y, ada_y_pred))
print('MAE:', mean_absolute_error(test_y, ada_y_pred))
print('MSE:', mean_squared_error(test_y, ada_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, ada_y_pred)))

# 构建GBDT回归模型
gbdt_model = GradientBoostingRegressor(
    n_estimators=100, max_depth=3, learning_rate=0.1, random_state=42)
gbdt_model.fit(train_X, train_y)
gbdt_y_pred = gbdt_model.predict(test_X)
gbdt_y_pred = np.round(gbdt_y_pred, 0).astype(int)
# 输出GBDT模型评价结果
print('GBDT模型评价结果：')
print('R2 Score:', r2_score(test_y, gbdt_y_pred))
print('MAE:', mean_absolute_error(test_y, gbdt_y_pred))
print('MSE:', mean_squared_error(test_y, gbdt_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, gbdt_y_pred)))


warnings.filterwarnings("ignore")

lgbm_model = LGBMRegressor()
lgbm_model.fit(X, Y)
lgbm_y_pred = lgbm_model.predict(X)

lgbm_y_pred = np.round(lgbm_y_pred, 0).astype(int)
print('LGBM模型评价结果：')
print('R2 Score:', r2_score(Y, lgbm_y_pred))
print('MAE:', mean_absolute_error(Y, lgbm_y_pred))
print('MSE:', mean_squared_error(Y, lgbm_y_pred))
print('RMSE:', sqrt(mean_squared_error(Y, lgbm_y_pred)))

# 可视化模型预测与实际值的比较
fig, ax = plt.subplots(figsize=(10, 8))
ax.scatter(Y, lgbm_y_pred, alpha=0.5)
ax.plot([0, 6], [0, 6], '--', color='black')
ax.set_xlabel('Actual')
ax.set_ylabel('Predicted')
plt.show()


# 预测160个患者
lgbm_y_pred = lgbm_model.predict(data.drop(columns=['90天mRS', 'Unnamed: 0']))
lgbm_y_pred = np.round(lgbm_y_pred, 0).astype(int)

# pd.DataFrame(lgbm_y_pred).to_excel('./data/p3_a_predicted_mRS.xlsx')
pd.DataFrame(lgbm_y_pred).to_excel('./data/p3_b_predicted_mRS.xlsx')
lgbm_y_pred
