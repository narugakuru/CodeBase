import pandas as pd
data2 = pd.read_excel("./data/表2-患者影像信息血肿及水肿的体积及位置.xlsx")
data1 = pd.read_excel("./data/表1-患者列表及临床信息.xlsx")
data1.head()


data2.head()


# 获取从Q列到W列的治疗列名列表
treatment_columns = data1.columns[16:23].tolist()


# 选择data数据框的 'ID'、'首次检查流水号' 和 'ED_volume' 列，以及data1数据框的 '入院首次影像检查流水号'、'发病到首次影像检查时间间隔' 列和治疗相关的列
merged_data_corrected = pd.merge(data2[['ID', '首次检查流水号','HM_volume', 'ED_volume']], data1[['入院首次影像检查流水号', '发病到首次影像检查时间间隔'] + treatment_columns], 
                                left_on='首次检查流水号', right_on='入院首次影像检查流水号', how='left')



index = merged_data_corrected['ID'].str[-3:].astype(int) < 100
filtered_data_corrected = merged_data_corrected.loc[index]

filtered_data_corrected


# 治疗方法和体积
filtered_data_corrected.to_excel('./data/p2_cd_data.xlsx')


import pandas as pd
import numpy as np
import seaborn as sns
from scipy.stats import spearmanr
import matplotlib.pyplot as plt
plt.rcParams['font.sans-serif'] = ['SimHei']  
plt.rcParams['axes.unicode_minus'] = False  
spearman_corr = filtered_data_corrected[['HM_volume','ED_volume', '脑室引流',
       '止血治疗', '降颅压治疗', '降压治疗', '镇静、镇痛治疗', '止吐护胃', '营养神经']].corr(method='spearman')
spearman_corr.to_excel("./data/p2_d_spearman_corr.xlsx")
plt.figure(figsize=(10, 8))
sns.heatmap(spearman_corr, annot=True, fmt=".2f", linewidths=0.5,cmap="magma")
plt.savefig('./data/p2_d_相关性矩阵.jpg') 


