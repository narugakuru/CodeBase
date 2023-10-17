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

data1 = pd.read_excel("./data/��1-�����б��ٴ���Ϣ.xlsx")
data2 = pd.read_excel("./data/��2-����Ӱ����ϢѪ�׼�ˮ�׵������λ��.xlsx")
data = pd.read_excel("./data/����1-�������-��ˮ��vsʱ��.xlsx")

data1.head()


data2.head()


data.head()


# ��ȡÿ�����ߵ�����ID
sub_list = [f'sub{i:03}' for i in range(1, 101)]
# �ϲ����߸�����Ϣ�ͼ�����
merged_data = pd.merge(data1[['Unnamed: 0', '�������״�Ӱ����ʱ����', '��Ժ�״�Ӱ������ˮ��']],
                       data, how='left', left_on='��Ժ�״�Ӱ������ˮ��', right_on='��Ժ�״μ����ˮ��')
merged_data = merged_data[merged_data['Unnamed: 0_x'].isin(sub_list)]
merged_data = merged_data.dropna(axis=1, thresh=1)

merged_data


# ����ÿλ���߷������ÿ��Ӱ��������ʱ��
for i in range(1, 13):
    time_column = f'���{i}ʱ���'
    if time_column in data.columns:
        # ������õ�ʱ����
        merged_data[f'���������{i}ʱ����'] = (
            data[time_column] - data['��Ժ�״μ��ʱ���']).dt.total_seconds() / 3600 + merged_data['�������״�Ӱ����ʱ����']
# �м��кܶ�Unnamed������
data2.columns, merged_data.columns


initial_hm_volumes = data2[['ID', 'HM_volume']].set_index('ID').to_dict()[
    'HM_volume']
# ��ʼ���б��Դ洢���л��ߵĽ��
absolute_volume_increase_flag = []
relative_volume_increase_flag = []
hematoma_expansion_flag = []
expansion_time_list = []
# �������� sub001 �� sub100
for patient_id in sub_list:
    # ��ȡ��ǰ���ߵ��״�Ѫ�����
    initial_volume = data2.loc[data2['ID']
                               == patient_id, 'HM_volume'].values[0]

    # ��ȡ��ǰ���߿��õ����Ѫ�������
    available_columns_patient = [col for col in ['HM_volume', 'HM_volume.1', 'HM_volume.2', 'HM_volume.3', 'HM_volume.4', 'HM_volume.5',
                                                 'HM_volume.6', 'HM_volume.7', 'HM_volume.8', 'HM_volume.9', 'HM_volume.10'] if col in data2.columns]
    followup_volumes_patient = data2.loc[data2['ID']
                                         == patient_id, available_columns_patient].values[0]

    # ��ȡ��ǰ���ߵ�ʱ���������10����ʱ�����������ȡ���ݲ�����Unnamed
    time_intervals_patient = merged_data.loc[merged_data['Unnamed: 0_x']
                                             == patient_id].iloc[:, -12:].values[0]
    print(time_intervals_patient)

    # ��ʼ����־��ֵ
    has_expansion = False
    expansion_detected_time = None
    absolute_increase = 0
    relative_increase = 0

    # ����ÿ�����õ����Ѫ���������鵱ǰ�����Ƿ���48Сʱ�ڷ�����Ѫ�������¼�
    for followup_volume, interval in zip(followup_volumes_patient[1:], time_intervals_patient):
        # �����ʱ�����ݶ��У���ʱ����48Сʱ��
        if not pd.isna(followup_volume) and not pd.isna(interval) and 0 < interval <= 48:
            # ����������
            absolute_increase = followup_volume - initial_volume
            # ����������
            relative_increase = absolute_increase / initial_volume

            if absolute_increase >= 6000 or relative_increase >= 0.33:
                has_expansion = True
                expansion_detected_time = interval
                break
    # �������ӵ��б���
    absolute_volume_increase_flag.append(int(absolute_increase >= 6000))
    relative_volume_increase_flag.append(
        int(relative_increase >= 0.33))

    hematoma_expansion_flag.append(int(has_expansion))
    expansion_time_list.append(expansion_detected_time)


# �����ת��Ϊpandas���
results_data = pd.DataFrame({
    'ID': sub_list,
    'Absolute_Volume_Increase_��6mL': absolute_volume_increase_flag,
    'Relative_Volume_Increase_��33%': relative_volume_increase_flag,
    'Hematoma_Expansion': hematoma_expansion_flag,
    'Expansion_Time_h': expansion_time_list
})


def format_hours(value):
    # print(type(value))
    if value > 0:
        return '{:.2f}Сʱ'.format(value)
    else:
        return None


results_data['Expansion_Time_h'] = results_data['Expansion_Time_h'].apply(
    format_hours)
results_data.to_excel('./data/p1_a_results.xlsx')
results_data


count_expansion = results_data['Hematoma_Expansion'].value_counts(0, 1)
count_expansion


warnings.filterwarnings('ignore')
# 1. ��������
data1_path = "./data/��1-�����б��ٴ���Ϣ.xlsx"
data2_path = "./data/��2-����Ӱ����ϢѪ�׼�ˮ�׵������λ��.xlsx"
data3_path = "./data/��3-����Ӱ����ϢѪ�׼�ˮ�׵���״���Ҷȷֲ�.xlsx"

data1 = pd.read_excel(data1_path)
data2 = pd.read_excel(data2_path)
data3 = pd.read_excel(data3_path)
data3_2 = pd.read_excel(data3_path, sheet_name='Hemo')


# ���ߵĸ�����Ϣ�ͼ���ʷ�����Ʒ�ʽ
data1_features = data1[['Unnamed: 0'] + list(data1.columns[4:23])]
data1_features.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)
# ���ߵ��״μ����
data2_features = data2[['ID'] + list(data2.columns[3:23])]

# ������ˮ�źϲ�123
data3_first_record_all = pd.merge(
    data1[['Unnamed: 0', '��Ժ�״�Ӱ������ˮ��']], data3, left_on='��Ժ�״�Ӱ������ˮ��', right_on='��ˮ��', how='left')
data3_features_all = data3_first_record_all[[
    'Unnamed: 0'] + list(data3_first_record_all.columns[2:33])]
data3_features_all.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)

data3_2_first_record_all = pd.merge(
    data1[['Unnamed: 0', '��Ժ�״�Ӱ������ˮ��']], data3_2, left_on='��Ժ�״�Ӱ������ˮ��', right_on='��ˮ��', how='left')
data3_2_features_all = data3_2_first_record_all[[
    'Unnamed: 0'] + list(data3_2_first_record_all.columns[2:33])]
data3_2_features_all.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)


data3_2_features_all


results_df_1a = pd.read_excel('./data/p1_a_results.xlsx')
results_df_1a


# �ϲ���123�е�����
merged_features_all = pd.merge(
    data1_features, data2_features, on='ID', how='inner')
merged_features_all = pd.merge(
    merged_features_all, data3_features_all, on='ID', how='inner')
merged_features_all = pd.merge(
    merged_features_all, data3_2_features_all, on='ID', how='inner')
# Ѫ������
merged_features_all = pd.merge(merged_features_all, results_df_1a[[
                               'ID', 'Hematoma_Expansion']], on='ID', how='left')


merged_features_all.isnull().sum().to_excel("./data/����ȱʧͳ��.xlsx")


# �������ݼ�Ϊѵ�����Ͳ��Լ�
sub_train = [f'sub{i:03}' for i in range(1, 101)]
sub_test = [f'sub{i:03}' for i in range(101, 161)]
train_data_all = merged_features_all[merged_features_all['ID'].isin(
    sub_train)]
test_data_all = merged_features_all[merged_features_all['ID'].isin(
    sub_test)]
X_train_all = train_data_all.drop(columns=['ID', 'Hematoma_Expansion'])
y_train_all = train_data_all['Hematoma_Expansion']
X_test_all = test_data_all.drop(columns=['ID', 'Hematoma_Expansion'])


# ����Ԥ��������
# �����ݷ�Ϊ��ֵ�ͺ������
numeric_cols = X_train_all.select_dtypes(
    include=['int64', 'float64']).columns.tolist()
categorical_cols = X_train_all.select_dtypes(
    include=['object']).columns.tolist()

# �����������ݵĴ���ʽ
numeric_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy='mean')),
    ('scaler', StandardScaler())
])
categorical_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy='constant', fill_value='missing')),
    ('onehot', OneHotEncoder(handle_unknown='ignore'))
])
# ���崦����
preprocessor = ColumnTransformer(
    transformers=[
        ('num', numeric_transformer, numeric_cols),
        ('cat', categorical_transformer, categorical_cols)
    ])


X_train_all


# ʹ��pipeline����
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

# ѡ�����ɭ��
rfc = Pipeline(steps=[('preprocessor', preprocessor),
                      ('classifier', RandomForestClassifier())])
# RandomForestClassifier()
# ѵ��������
rfc.fit(X_train_all, y_train_all)
# ����ģ��
# joblib.dump(rfc, './data/model.pkl')


# ��ȡģ��
rfc = joblib.load('./data/model.pkl')
# ʹ�÷�����rfc��ѵ��������Ԥ��
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

    # ������ͼ����ʾ���������ֵ
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
labels = ['Ѫ��δ����', 'Ѫ������']

# ���ƻ�����������ͼ
plot_confusion_matrix(rfc_confusion_matrix, labels)

# ��ʾͼ��
plt.show()


# ����ģ�ͱ���
rfc_report = classification_report(y_train_all, y_train_pred, labels=None,
                                   target_names=None, sample_weight=None, digits=2, output_dict=False)

print(rfc_report)


# ROC����
fpr, tpr, thresholds = roc_curve(y_train_all, y_train_pred_proba)
roc_auc = auc(fpr, tpr)

plt.figure(figsize=(8, 6))
plt.plot(fpr, tpr, color='red', lw=2,
         label='ROC���� (area = %0.4f)' % roc_auc)
plt.plot([0, 1], [0, 1], color='green', lw=2, linestyle='--')
plt.xlim([0.0, 1.1])
plt.ylim([0.0, 1.1])
plt.xlabel('������False Positive Rate')
plt.ylabel('������True Positive Rate')
plt.title('ROC����')
plt.legend(loc="upper right")
plt.show()


# Ԥ��ѵ����
predicted_probabilities_all = rfc.predict_proba(X_train_all)[:, 1]
predicted_probabilities_rounded_all_X_train_all = np.round(
    predicted_probabilities_all, 4)

predicted_probabilities_rounded_all_X_train_all


# Ԥ����Լ�
predicted_probabilities_all = rfc.predict_proba(X_test_all)[:, 1]
predicted_probabilities_rounded_all_X_test_all = np.round(
    predicted_probabilities_all, 4)

predicted_probabilities_rounded_all_X_test_all


allpredict = pd.concat([pd.DataFrame(predicted_probabilities_rounded_all_X_train_all),
                       pd.DataFrame(predicted_probabilities_rounded_all_X_test_all)])
allpredict = allpredict.reset_index(drop=True)

pd.DataFrame(allpredict).to_excel('./data/p1_b_allpredict.xlsx')
allpredict
