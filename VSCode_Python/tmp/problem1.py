from sklearn.metrics import confusion_matrix, classification_report, roc_curve, roc_auc_score, auc, accuracy_score, recall_score, f1_score
import joblib
from sklearn.preprocessing import StandardScaler, OneHotEncoder
from sklearn.impute import SimpleImputer
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier, GradientBoostingClassifier
from lightgbm import LGBMRegressor
import matplotlib.pyplot as plt
import numpy as np
import warnings
import pandas as pd

data1 = pd.read_excel("./data/表1-患者列表及临床信息.xlsx")
data2 = pd.read_excel("./data/表2-患者影像信息血肿及水肿的体积及位置.xlsx")
data = pd.read_excel("./data/附表1-检索表格-流水号vs时间.xlsx")

data1.head()


data2.head()


data.head()


# 获取每个患者的索引ID
sub_list = [f'sub{i:03}' for i in range(1, 101)]
# 合并患者个人信息和检索表
merged_data = pd.merge(data1[['Unnamed: 0', '发病到首次影像检查时间间隔', '入院首次影像检查流水号']],
                       data, how='left', left_on='入院首次影像检查流水号', right_on='入院首次检查流水号')
merged_data = merged_data[merged_data['Unnamed: 0_x'].isin(sub_list)]
merged_data = merged_data.dropna(axis=1, thresh=1)

merged_data


# 计算每位患者发病后的每个影像检查的相对时间
for i in range(1, 13):
    time_column = f'随访{i}时间点'
    if time_column in data.columns:
        # 计算随访的时间间隔
        merged_data[f'发病到随访{i}时间间隔'] = (
            data[time_column] - data['入院首次检查时间点']).dt.total_seconds() / 3600 + merged_data['发病到首次影像检查时间间隔']
# 中间有很多Unnamed待处理
data2.columns, merged_data.columns


initial_hm_volumes = data2[['ID', 'HM_volume']].set_index('ID').to_dict()[
    'HM_volume']
# 初始化列表以存储所有患者的结果
absolute_volume_increase_flag = []
relative_volume_increase_flag = []
hematoma_expansion_flag = []
expansion_time_list = []
# 遍历患者 sub001 到 sub100
for patient_id in sub_list:
    # 提取当前患者的首次血肿体积
    initial_volume = data2.loc[data2['ID']
                               == patient_id, 'HM_volume'].values[0]

    # 提取当前患者可用的随访血肿体积列
    available_columns_patient = [col for col in ['HM_volume', 'HM_volume.1', 'HM_volume.2', 'HM_volume.3', 'HM_volume.4', 'HM_volume.5',
                                                 'HM_volume.6', 'HM_volume.7', 'HM_volume.8', 'HM_volume.9', 'HM_volume.10'] if col in data2.columns]
    followup_volumes_patient = data2.loc[data2['ID']
                                         == patient_id, available_columns_patient].values[0]

    # 提取当前患者的时间间隔，最后10列是时间间隔，从最后取数据不处理Unnamed
    time_intervals_patient = merged_data.loc[merged_data['Unnamed: 0_x']
                                             == patient_id].iloc[:, -12:].values[0]
    print(time_intervals_patient)

    # 初始化标志和值
    has_expansion = False
    expansion_detected_time = None
    absolute_increase = 0
    relative_increase = 0

    # 遍历每个可用的随访血肿体积并检查当前患者是否在48小时内发生了血肿扩张事件
    for followup_volume, interval in zip(followup_volumes_patient[1:], time_intervals_patient):
        # 体积和时间数据都有，且时间在48小时内
        if not pd.isna(followup_volume) and not pd.isna(interval) and 0 < interval <= 48:
            # 计算绝对体积
            absolute_increase = followup_volume - initial_volume
            # 计算相对体积
            relative_increase = absolute_increase / initial_volume

            if absolute_increase >= 6000 or relative_increase >= 0.33:
                has_expansion = True
                expansion_detected_time = interval
                break
    # 将结果添加到列表中
    absolute_volume_increase_flag.append(int(absolute_increase >= 6000))
    relative_volume_increase_flag.append(
        int(relative_increase >= 0.33))

    hematoma_expansion_flag.append(int(has_expansion))
    expansion_time_list.append(expansion_detected_time)


# 将结果转化为pandas输出
results_data = pd.DataFrame({
    'ID': sub_list,
    'Absolute_Volume_Increase_≥6mL': absolute_volume_increase_flag,
    'Relative_Volume_Increase_≥33%': relative_volume_increase_flag,
    'Hematoma_Expansion': hematoma_expansion_flag,
    'Expansion_Time_h': expansion_time_list
})


def format_hours(value):
    # print(type(value))
    if value > 0:
        return '{:.2f}小时'.format(value)
    else:
        return None


results_data['Expansion_Time_h'] = results_data['Expansion_Time_h'].apply(
    format_hours)
results_data.to_excel('./data/p1_a_results.xlsx')
results_data


count_expansion = results_data['Hematoma_Expansion'].value_counts(0, 1)
count_expansion


warnings.filterwarnings('ignore')
# 1. 加载数据
data1_path = "./data/表1-患者列表及临床信息.xlsx"
data2_path = "./data/表2-患者影像信息血肿及水肿的体积及位置.xlsx"
data3_path = "./data/表3-患者影像信息血肿及水肿的形状及灰度分布.xlsx"

data1 = pd.read_excel(data1_path)
data2 = pd.read_excel(data2_path)
data3 = pd.read_excel(data3_path)
data3_2 = pd.read_excel(data3_path, sheet_name='Hemo')


# 患者的个人信息和疾病史，治疗方式
data1_features = data1[['Unnamed: 0'] + list(data1.columns[4:23])]
data1_features.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)
# 患者的首次检查结果
data2_features = data2[['ID'] + list(data2.columns[3:23])]

# 根据流水号合并123
data3_first_record_all = pd.merge(
    data1[['Unnamed: 0', '入院首次影像检查流水号']], data3, left_on='入院首次影像检查流水号', right_on='流水号', how='left')
data3_features_all = data3_first_record_all[[
    'Unnamed: 0'] + list(data3_first_record_all.columns[2:33])]
data3_features_all.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)

data3_2_first_record_all = pd.merge(
    data1[['Unnamed: 0', '入院首次影像检查流水号']], data3_2, left_on='入院首次影像检查流水号', right_on='流水号', how='left')
data3_2_features_all = data3_2_first_record_all[[
    'Unnamed: 0'] + list(data3_2_first_record_all.columns[2:33])]
data3_2_features_all.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)


data3_2_features_all


results_df_1a = pd.read_excel('./data/p1_a_results.xlsx')
results_df_1a


# 合并从123中的特征
merged_features_all = pd.merge(
    data1_features, data2_features, on='ID', how='inner')
merged_features_all = pd.merge(
    merged_features_all, data3_features_all, on='ID', how='inner')
merged_features_all = pd.merge(
    merged_features_all, data3_2_features_all, on='ID', how='inner')
# 血肿扩张
merged_features_all = pd.merge(merged_features_all, results_df_1a[[
                               'ID', 'Hematoma_Expansion']], on='ID', how='left')


merged_features_all.isnull().sum().to_excel("./data/特征缺失统计.xlsx")


# 划分数据集为训练集和测试集
sub_train = [f'sub{i:03}' for i in range(1, 101)]
sub_test = [f'sub{i:03}' for i in range(101, 161)]
train_data_all = merged_features_all[merged_features_all['ID'].isin(
    sub_train)]
test_data_all = merged_features_all[merged_features_all['ID'].isin(
    sub_test)]
X_train_all = train_data_all.drop(columns=['ID', 'Hematoma_Expansion'])
y_train_all = train_data_all['Hematoma_Expansion']
X_test_all = test_data_all.drop(columns=['ID', 'Hematoma_Expansion'])


# 数据预处理流程
# 把数据分为数值型和类别型
numeric_cols = X_train_all.select_dtypes(
    include=['int64', 'float64']).columns.tolist()
categorical_cols = X_train_all.select_dtypes(
    include=['object']).columns.tolist()

# 定义两种数据的处理方式
numeric_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy='mean')),
    ('scaler', StandardScaler())
])
categorical_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy='constant', fill_value='missing')),
    ('onehot', OneHotEncoder(handle_unknown='ignore'))
])
# 定义处理器
preprocessor = ColumnTransformer(
    transformers=[
        ('num', numeric_transformer, numeric_cols),
        ('cat', categorical_transformer, categorical_cols)
    ])


X_train_all


# 使用pipeline流程
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

# 选择随机森林
rfc = Pipeline(steps=[('preprocessor', preprocessor),
                      ('classifier', RandomForestClassifier())])
# RandomForestClassifier()
# 训练分类器
rfc.fit(X_train_all, y_train_all)
# 保存模型
# joblib.dump(rfc, './data/model.pkl')


# 读取模型
rfc = joblib.load('./data/model.pkl')
# 使用分类器rfc对训练集进行预测
y_train_pred = rfc.predict(X_train_all)
y_train_pred_proba = rfc.predict_proba(X_train_all)[:, 1]
y_train_pred, y_train_pred_proba


def plot_confusion_matrix(confusion_matrix, labels):
    plt.imshow(confusion_matrix, interpolation='nearest', cmap=plt.cm.Blues)
    plt.title("Confusion Matrix")
    plt.colorbar()
    tick_marks = np.arange(len(labels))
    plt.xticks(tick_marks, labels, rotation=45)
    plt.yticks(tick_marks, labels)

    # 在热力图中显示混淆矩阵的值
    thresh = confusion_matrix.max() / 2.
    for i in range(confusion_matrix.shape[0]):
        for j in range(confusion_matrix.shape[1]):
            plt.text(j, i, format(confusion_matrix[i, j], 'd'),
                     horizontalalignment="center",
                     color="white" if confusion_matrix[i, j] > thresh else "black")

    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.tight_layout()


rfc_confusion_matrix = confusion_matrix(y_train_all, y_train_pred)
labels = ['血肿未拓张', '血肿拓张']

# 绘制混淆矩阵热力图
plot_confusion_matrix(rfc_confusion_matrix, labels)

# 显示图形
plt.show()


# 生成模型报告
rfc_report = classification_report(y_train_all, y_train_pred, labels=None,
                                   target_names=None, sample_weight=None, digits=2, output_dict=False)

print(rfc_report)


# ROC曲线
fpr, tpr, thresholds = roc_curve(y_train_all, y_train_pred_proba)
roc_auc = auc(fpr, tpr)

plt.figure(figsize=(8, 6))
plt.plot(fpr, tpr, color='red', lw=2,
         label='ROC曲线 (area = %0.4f)' % roc_auc)
plt.plot([0, 1], [0, 1], color='green', lw=2, linestyle='--')
plt.xlim([0.0, 1.1])
plt.ylim([0.0, 1.1])
plt.xlabel('假阳率False Positive Rate')
plt.ylabel('灵敏度True Positive Rate')
plt.title('ROC曲线')
plt.legend(loc="upper right")
plt.show()


# 预测训练集
predicted_probabilities_all = rfc.predict_proba(X_train_all)[:, 1]
predicted_probabilities_rounded_all_X_train_all = np.round(
    predicted_probabilities_all, 4)

predicted_probabilities_rounded_all_X_train_all


# 预测测试集
predicted_probabilities_all = rfc.predict_proba(X_test_all)[:, 1]
predicted_probabilities_rounded_all_X_test_all = np.round(
    predicted_probabilities_all, 4)

predicted_probabilities_rounded_all_X_test_all


allpredict = pd.concat([pd.DataFrame(predicted_probabilities_rounded_all_X_train_all),
                       pd.DataFrame(predicted_probabilities_rounded_all_X_test_all)])
allpredict = allpredict.reset_index(drop=True)

pd.DataFrame(allpredict).to_excel('./data/p1_b_allpredict.xlsx')
allpredict
