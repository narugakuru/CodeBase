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
data1 = pd.read_excel("./data/��1-�����б��ٴ���Ϣ.xlsx")
data2 = pd.read_excel("./data/��2-����Ӱ����ϢѪ�׼�ˮ�׵������λ��.xlsx")
data3_ED = pd.read_excel("./data/��3-����Ӱ����ϢѪ�׼�ˮ�׵���״���Ҷȷֲ�.xlsx", sheet_name='ED')
data3_Hemo = pd.read_excel(
    "./data/��3-����Ӱ����ϢѪ�׼�ˮ�׵���״���Ҷȷֲ�.xlsx", sheet_name='Hemo')


data1.head(), data2.head(), data3_ED.head(), data3_Hemo.head()

# AB�ʽ����ݴ���������ȡ��ͬ
# ���ݻ���ID����ˮ�źϲ��������ݿ�
# ������B
merged_data = data1.merge(data2[data2.columns], left_on=[
                          'Unnamed: 0', '��Ժ�״�Ӱ������ˮ��'], right_on=['ID', '�״μ����ˮ��'], how='left')
# ������A
# merged_data = data1.merge(data2[data2.columns[:24]], left_on=['Unnamed: 0', '��Ժ�״�Ӱ������ˮ��'], right_on=['ID', '�״μ����ˮ��'], how='left')
merged_data = merged_data.merge(
    data3_ED, left_on='��Ժ�״�Ӱ������ˮ��', right_on='��ˮ��', how='left')
merged_data = merged_data.merge(
    data3_Hemo, left_on='��Ժ�״�Ӱ������ˮ��', right_on='��ˮ��', how='left')
# data = data.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)
data = merged_data
data.columns


merged_data = data1.merge(data2[data2.columns], left_on=[
                          'Unnamed: 0', '��Ժ�״�Ӱ������ˮ��'], right_on=['ID', '�״μ����ˮ��'], how='left')
merged_data = merged_data.merge(
    data3_ED, left_on='��Ժ�״�Ӱ������ˮ��', right_on='��ˮ��', how='left')
merged_data = merged_data.merge(
    data3_Hemo, left_on='��Ժ�״�Ӱ������ˮ��', right_on='��ˮ��', how='left')
# ɾ��������
data = data.drop(columns=['ID', '��Ժ�״�Ӱ������ˮ��',
                 '�״μ����ˮ��', '��ע_x', '��ע_y', '��ˮ��_x', '��ˮ��_y'])
data


merged_data = pd.read_excel("./data/merged_all.xlsx")
# ���߸�����Ϣ
columns_a = merged_data.columns[:21].to_list()
tmp_a = ['HM_volume', 'ED_volume', 'Ѫѹ_���ֵ', 'Ѫѹ_��Сֵ']

for i in tmp_a:
    columns_a.append(i)
data_a = merged_data[columns_a]
# Ӱ����Ϣ
data_a


# �鿴ȱʧֵ
index_list = []
for column in data.columns:
    if data[column].isnull().sum() > 0:
        index_list.append(column)

data[index_list].isnull().sum()


# ��ֵ���,����90��mRS
for index in index_list[1:]:
    data[index] = data[index].fillna(data[index].mean())

data[index_list].isnull().sum()


# ����Ԥ����
data[['�Ա�', 'Ѫѹ']]
data['�Ա�'] = data['�Ա�'].map(
    {
        'Ů': 0,
        '��': 1
    })
data['Ѫѹ_���ֵ'] = data['Ѫѹ'].apply(lambda x: int(x.split('/')[0]))
data['Ѫѹ_��Сֵ'] = data['Ѫѹ'].apply(lambda x: int(x.split('/')[1]))
del data['���ݼ�����']
del data['Ѫѹ']


warnings.filterwarnings('ignore')


# # �����������ݵĴ���ʽ
# numeric_transformer = Pipeline(steps=[
#     ('imputer', SimpleImputer(strategy='mean')),
#     ('scaler', StandardScaler())
# ])
# categorical_transformer = Pipeline(steps=[
#     ('imputer', SimpleImputer(strategy='constant', fill_value='missing')),
#     ('onehot', OneHotEncoder(handle_unknown='ignore'))
# ])
# # ���崦����
# preprocessor = ColumnTransformer(
#     transformers=[
#         ('num', numeric_transformer, numeric_cols),
#         ('cat', categorical_transformer, categorical_cols)
#     ])


# A������Ԥ����
numeric_cols = data.columns.tolist()
categorical_cols = ['�Ա�', '�Գ�ѪǰmRS����', '��Ѫѹ��ʷ', '���в�ʷ', '����ʷ', '����ʷ', '���Ĳ�ʷ',
                    '����ʷ', '����ʷ', '��������', 'ֹѪ����', '��­ѹ����', '��ѹ����', '�򾲡���ʹ����', 'ֹ�»�θ', 'Ӫ����']
remove_cols = ['Unnamed: 0', '90��mRS']
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


# ��90��mRS�Ļ�������ѵ������
train_data = data[data['90��mRS'].notnull()]
X = train_data.drop(columns=['90��mRS', 'Unnamed: 0'])
Y = train_data['90��mRS']
train_X, test_X, train_y, test_y = train_test_split(
    X, Y, test_size=0.1, random_state=148)
train_X


warnings.filterwarnings('ignore')
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False


# ����xgboost�ع�ģ��
xgb_model = xgb.XGBRegressor()
xgb_model.fit(train_X, train_y)
xgb_y_pred = xgb_model.predict(test_X)
xgb_y_pred = np.round(xgb_y_pred, 0).astype(int)

# ���xgboostģ�����۽��
print('XGBoostģ�����۽����')
print('R2 Score:', r2_score(test_y, xgb_y_pred))
print('MAE:', mean_absolute_error(test_y, xgb_y_pred))
print('MSE:', mean_squared_error(test_y, xgb_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, xgb_y_pred)))


# �������ɭ�ֻع�ģ��
rf_model = RandomForestRegressor(
    n_estimators=100, max_depth=3, random_state=42)
rf_model.fit(train_X, train_y)
rf_y_pred = rf_model.predict(test_X)
rf_y_pred = np.round(rf_y_pred, 0).astype(int)
# ������ɭ��ģ�����۽��
print('���ɭ��ģ�����۽����')
print('R2 Score:', r2_score(test_y, rf_y_pred))
print('MAE:', mean_absolute_error(test_y, rf_y_pred))
print('MSE:', mean_squared_error(test_y, rf_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, rf_y_pred)))

# ����LGBM�ع�ģ��
lgbm_model = LGBMRegressor()
lgbm_model.fit(train_X, train_y)
lgbm_y_pred = lgbm_model.predict(test_X)

lgbm_y_pred = np.round(lgbm_y_pred, 0).astype(int)
# ���LGBMģ�����۽��
print('LGBMģ�����۽����')
print('R2 Score:', r2_score(test_y, lgbm_y_pred))
print('MAE:', mean_absolute_error(test_y, lgbm_y_pred))
print('MSE:', mean_squared_error(test_y, lgbm_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, lgbm_y_pred)))

# ����Adaboost�ع�ģ��
ada_model = AdaBoostRegressor(n_estimators=100)
ada_model.fit(train_X, train_y)
ada_y_pred = ada_model.predict(test_X)
ada_y_pred = np.round(ada_y_pred, 0).astype(int)
# ���Adaboostģ�����۽��
print('Adaboostģ�����۽����')
print('R2 Score:', r2_score(test_y, ada_y_pred))
print('MAE:', mean_absolute_error(test_y, ada_y_pred))
print('MSE:', mean_squared_error(test_y, ada_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, ada_y_pred)))

# ����GBDT�ع�ģ��
gbdt_model = GradientBoostingRegressor(
    n_estimators=100, max_depth=3, learning_rate=0.1, random_state=42)
gbdt_model.fit(train_X, train_y)
gbdt_y_pred = gbdt_model.predict(test_X)
gbdt_y_pred = np.round(gbdt_y_pred, 0).astype(int)
# ���GBDTģ�����۽��
print('GBDTģ�����۽����')
print('R2 Score:', r2_score(test_y, gbdt_y_pred))
print('MAE:', mean_absolute_error(test_y, gbdt_y_pred))
print('MSE:', mean_squared_error(test_y, gbdt_y_pred))
print('RMSE:', sqrt(mean_squared_error(test_y, gbdt_y_pred)))


warnings.filterwarnings("ignore")

lgbm_model = LGBMRegressor()
lgbm_model.fit(X, Y)
lgbm_y_pred = lgbm_model.predict(X)

lgbm_y_pred = np.round(lgbm_y_pred, 0).astype(int)
print('LGBMģ�����۽����')
print('R2 Score:', r2_score(Y, lgbm_y_pred))
print('MAE:', mean_absolute_error(Y, lgbm_y_pred))
print('MSE:', mean_squared_error(Y, lgbm_y_pred))
print('RMSE:', sqrt(mean_squared_error(Y, lgbm_y_pred)))

# ���ӻ�ģ��Ԥ����ʵ��ֵ�ıȽ�
fig, ax = plt.subplots(figsize=(10, 8))
ax.scatter(Y, lgbm_y_pred, alpha=0.5)
ax.plot([0, 6], [0, 6], '--', color='black')
ax.set_xlabel('Actual')
ax.set_ylabel('Predicted')
plt.show()


# Ԥ��160������
lgbm_y_pred = lgbm_model.predict(data.drop(columns=['90��mRS', 'Unnamed: 0']))
lgbm_y_pred = np.round(lgbm_y_pred, 0).astype(int)

# pd.DataFrame(lgbm_y_pred).to_excel('./data/p3_a_predicted_mRS.xlsx')
pd.DataFrame(lgbm_y_pred).to_excel('./data/p3_b_predicted_mRS.xlsx')
lgbm_y_pred
