import pandas as pd
data2 = pd.read_excel("./data/��2-����Ӱ����ϢѪ�׼�ˮ�׵������λ��.xlsx")
data1 = pd.read_excel("./data/��1-�����б��ٴ���Ϣ.xlsx")
data1.head()


data2.head()


# ��ȡ��Q�е�W�е����������б�
treatment_columns = data1.columns[16:23].tolist()


# ѡ��data���ݿ�� 'ID'��'�״μ����ˮ��' �� 'ED_volume' �У��Լ�data1���ݿ�� '��Ժ�״�Ӱ������ˮ��'��'�������״�Ӱ����ʱ����' �к�������ص���
merged_data_corrected = pd.merge(data2[['ID', '�״μ����ˮ��','HM_volume', 'ED_volume']], data1[['��Ժ�״�Ӱ������ˮ��', '�������״�Ӱ����ʱ����'] + treatment_columns], 
                                left_on='�״μ����ˮ��', right_on='��Ժ�״�Ӱ������ˮ��', how='left')



index = merged_data_corrected['ID'].str[-3:].astype(int) < 100
filtered_data_corrected = merged_data_corrected.loc[index]

filtered_data_corrected


# ���Ʒ��������
filtered_data_corrected.to_excel('./data/p2_cd_data.xlsx')


import pandas as pd
import numpy as np
import seaborn as sns
from scipy.stats import spearmanr
import matplotlib.pyplot as plt
plt.rcParams['font.sans-serif'] = ['SimHei']  
plt.rcParams['axes.unicode_minus'] = False  
spearman_corr = filtered_data_corrected[['HM_volume','ED_volume', '��������',
       'ֹѪ����', '��­ѹ����', '��ѹ����', '�򾲡���ʹ����', 'ֹ�»�θ', 'Ӫ����']].corr(method='spearman')
spearman_corr.to_excel("./data/p2_d_spearman_corr.xlsx")
plt.figure(figsize=(10, 8))
sns.heatmap(spearman_corr, annot=True, fmt=".2f", linewidths=0.5,cmap="magma")
plt.savefig('./data/p2_d_����Ծ���.jpg') 


