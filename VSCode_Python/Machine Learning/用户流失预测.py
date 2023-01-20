# coding: utf-8


# # 电信客户流失预测


# ## 1、导入数据


# In[1]:
import numpy as np
import pandas as pd
import os

# In[2]:
# 导入相关的包
import matplotlib.pyplot as plt
import seaborn as sns
from pylab import rcParams
import matplotlib.cm as cm

import sklearn
from sklearn import preprocessing
from sklearn.preprocessing import LabelEncoder  # 编码转换
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import StratifiedShuffleSplit


from sklearn.ensemble import RandomForestClassifier  # 随机森林
from sklearn.svm import SVC, LinearSVC  # 支持向量机
from sklearn.linear_model import LogisticRegression  # 逻辑回归
from sklearn.neighbors import KNeighborsClassifier  # KNN算法
from sklearn.naive_bayes import GaussianNB  # 朴素贝叶斯
from sklearn.tree import DecisionTreeClassifier  # 决策树分类器
from xgboost import XGBClassifier
from catboost import CatBoostClassifier
from sklearn.ensemble import AdaBoostClassifier
from sklearn.ensemble import GradientBoostingClassifier

from sklearn.metrics import (
    classification_report,
    precision_score,
    recall_score,
    f1_score,
)
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import make_scorer
from sklearn.ensemble import VotingClassifier

from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_score

import warnings
import IPython

warnings.filterwarnings("ignore")
IPython.get_ipython().magic("matplotlib inline")

# In[3]:# 读取数据文件
telcom = pd.read_csv(
    r"D:/Code/VSCode_Python/Machine Learning/实训数据.csv", encoding="gb18030"
)


# ## 2、查看数据集信息


# In[4]:


telcom.head(10)


# In[5]:


# 查看数据集大小
telcom.shape


# In[6]:


# 获取数据类型列的描述统计信息
telcom.describe()


# ## 3、数据清洗


# In[7]:


# 查找缺失值
pd.isnull(telcom).sum()


# In[8]:


telcom["Churn"].value_counts()


# 数据集中有5174名用户没流失，有1869名客户流失，数据集不均衡。


# In[9]:


telcom.info()


# TotalCharges表示总费用，这里为对象类型，需要转换为float类型


# In[10]:

"""
这种写法是pandas老版本的，如果你的pandas是新版本会报错
df['col name'] = df['col name'].apply(pd.to_numeric, errors='coerce')
或者
df['col name']=df.col name .astype(float)
旧写法：
# telcom["TotalCharges"] = telcom["TotalCharges"].convert_objects(convert_numeric=True)
# convert_numeric=True表示强制转换数字(包括字符串)，不可转换的值变为NaN
"""

telcom["TotalCharges"] = telcom["TotalCharges"].apply(pd.to_numeric, errors="coerce")
telcom["TotalCharges"].dtypes


# In[11]:


# 再次查找是否存在缺失值
pd.isnull(telcom["TotalCharges"]).sum()


# 这里存在11个缺失值，由于数量不多我们可以直接删除这些行


# In[12]:


# 删除缺失值所在的行
telcom.dropna(inplace=True)
telcom.shape


# In[13]:


# 数据归一化处理
# 对Churn 列中的值 Yes和 No分别用 1和 0替换，方便后续处理
telcom["Churn"].replace(to_replace="Yes", value=1, inplace=True)
telcom["Churn"].replace(to_replace="No", value=0, inplace=True)
telcom["Churn"].head()


# In[14]:


telcom["Churn"].replace(to_replace="Yes", value=1, inplace=True)
telcom["Churn"].replace(to_replace="No", value=0, inplace=True)
telcom["Churn"].head()


# ## 4、数据可视化呈现


# In[15]:


# 查看流失客户占比
"""
画饼图参数：
labels  (每一块)饼图外侧显示的说明文字
explode  (每一块)离开中心距离
startangle  起始绘制角度,默认图是从x轴正方向逆时针画起,如设定=90则从y轴正方向画起
shadow   是否阴影
labeldistance label  绘制位置,相对于半径的比例, 如<1则绘制在饼图内侧
autopct   控制饼图内百分比设置,可以使用format字符串或者format function
     '%1.1f'指小数点前后位数(没有用空格补齐)
pctdistance 类似于labeldistance,指定autopct的位置刻度
radius   控制饼图半径
"""
churnvalue = telcom["Churn"].value_counts()
labels = telcom["Churn"].value_counts().index


rcParams["figure.figsize"] = 6, 6
plt.pie(
    churnvalue,
    labels=labels,
    colors=["whitesmoke", "yellow"],
    explode=(0.1, 0),
    autopct="%1.1f%%",
    shadow=True,
)
plt.title("Proportions of Customer Churn")
plt.show()


# In[16]:


# 性别、老年人、配偶、亲属对流客户流失率的影响
f, axes = plt.subplots(nrows=2, ncols=2, figsize=(10, 10))


plt.subplot(2, 2, 1)
gender = sns.countplot(
    x="gender", hue="Churn", data=telcom, palette="Pastel2"
)  # palette参数表示设置颜色，这里设置为主题色Pastel2
plt.xlabel("gender")
plt.title("Churn by Gender")


plt.subplot(2, 2, 2)
seniorcitizen = sns.countplot(
    x="SeniorCitizen", hue="Churn", data=telcom, palette="Pastel2"
)
plt.xlabel("senior citizen")
plt.title("Churn by Senior Citizen")


plt.subplot(2, 2, 3)
partner = sns.countplot(x="Partner", hue="Churn", data=telcom, palette="Pastel2")
plt.xlabel("partner")
plt.title("Churn by Partner")


plt.subplot(2, 2, 4)
dependents = sns.countplot(x="Dependents", hue="Churn", data=telcom, palette="Pastel2")
plt.xlabel("dependents")
plt.title("Churn by Dependents")


# In[17]:


# 提取特征
charges = telcom.iloc[:, 1:20]
# 对特征进行编码
"""
离散特征的编码分为两种情况：
1、离散特征的取值之间没有大小的意义，比如color：[red,blue],那么就使用one-hot编码
2、离散特征的取值有大小的意义，比如size:[X,XL,XXL],那么就使用数值的映射{X:1,XL:2,XXL:3}
"""
corrDf = charges.apply(lambda x: pd.factorize(x)[0])
corrDf.head()


# In[18]:


# 构造相关性矩阵
corr = corrDf.corr()
corr


# In[19]:


# 使用热地图显示相关系数
"""
heatmap    使用热地图展示系数矩阵情况
linewidths 热力图矩阵之间的间隔大小
annot      设定是否显示每个色块的系数值
"""
plt.figure(figsize=(20, 16))
ax = sns.heatmap(
    corr,
    xticklabels=corr.columns,
    yticklabels=corr.columns,
    linewidths=0.2,
    cmap="YlGnBu",
    annot=True,
)
plt.title("Correlation between variables")


# 结论：从上图可以看出，互联网服务、网络安全服务、在线备份业务、设备保护业务、技术支持服务、网络电视和网络电影之间存在较强的相关性，多线业务和电话服务之间也有很强的相关性，并且都呈强正相关关系。


# In[20]:


# 使用one-hot编码
tel_dummies = pd.get_dummies(telcom.iloc[:, 1:21])
tel_dummies.head()


# In[21]:


# 电信用户是否流失与各变量之间的相关性
plt.figure(figsize=(15, 8))
tel_dummies.corr()["Churn"].sort_values(ascending=False).plot(kind="bar")
plt.title("Correlations between Churn and variables")


# 由图上可以看出，变量gender 和 PhoneService 处于图形中间，其值接近于 0 ，这两个变量对电信客户流失预测影响非常小，可以直接舍弃。


# In[22]:


# 网络安全服务、在线备份业务、设备保护业务、技术支持服务、网络电视、网络电影和无互联网服务对客户流失率的影响
covariables = [
    "OnlineSecurity",
    "OnlineBackup",
    "DeviceProtection",
    "TechSupport",
    "StreamingTV",
    "StreamingMovies",
]
fig, axes = plt.subplots(nrows=2, ncols=3, figsize=(16, 10))
for i, item in enumerate(covariables):
    plt.subplot(2, 3, (i + 1))
    ax = sns.countplot(
        x=item,
        hue="Churn",
        data=telcom,
        palette="Pastel2",
        order=["Yes", "No", "No internet service"],
    )
    plt.xlabel(str(item))
    plt.title("Churn by " + str(item))
    i = i + 1
plt.show()


# 由上图可以看出，在网络安全服务、在线备份业务、设备保护业务、技术支持服务、网络电视和网络电影六个变量中，没有互联网服务的客户流失率值是相同的，都是相对较低。
#
# 这可能是因为以上六个因素只有在客户使用互联网服务时才会影响客户的决策，这六个因素不会对不使用互联网服务的客户决定是否流失产生推论效应。


# In[23]:


# 签订合同方式对客户流失率的影响
sns.barplot(
    x="Contract",
    y="Churn",
    data=telcom,
    palette="Pastel1",
    order=["Month-to-month", "One year", "Two year"],
)
plt.title("Churn by Contract type")


# 由图上可以看出，签订合同方式对客户流失率影响为：按月签订 > 按一年签订 > 按两年签订，这可能表明，设定长期合同对留住现有客户更有效。


# In[24]:


# 付款方式对客户流失率的影响
plt.figure(figsize=(10, 5))
sns.barplot(
    x="PaymentMethod",
    y="Churn",
    data=telcom,
    palette="Pastel1",
    order=[
        "Bank transfer (automatic)",
        "Credit card (automatic)",
        "Electronic check",
        "Mailed check",
    ],
)
plt.title("Churn by PaymentMethod type")


# 由图上可以看出，在四种支付方式中，使用Electronic check的用户流流失率最高，其他三种支付方式基本持平，因此可以推断电子账单在设计上影响用户体验。


# ## 5、数据预处理


# 由前面结果可知，CustomerID表示每个客户的随机字符，对后续建模不影响，我这里选择删除CustomerID列；gender 和 PhoneService 与流失率的相关性低，可直接忽略。


# In[26]:


telcomvar = telcom.iloc[:, 2:20]
telcomvar.drop("PhoneService", axis=1, inplace=True)


# 提取ID
telcom_id = telcom["customerID"]


telcomvar.head()


# In[27]:


# 对客户的职位、月费用和总费用进行去均值和方差缩放，对数据进行标准化
"""
标准化数据，保证每个维度的特征数据方差为1，均值为0，使得预测结果不会被某些维度过大的特征值而主导。
"""
scaler = StandardScaler(copy=False)
# fit_transform()的作用就是先拟合数据，然后转化它将其转化为标准形式
scaler.fit_transform(telcomvar[["tenure", "MonthlyCharges", "TotalCharges"]])


# In[28]:


# tranform()的作用是通过找中心和缩放等实现标准化
telcomvar[["tenure", "MonthlyCharges", "TotalCharges"]] = scaler.transform(
    telcomvar[["tenure", "MonthlyCharges", "TotalCharges"]]
)


# In[29]:


# 使用箱线图查看数据是否存在异常值
plt.figure(figsize=(8, 4))
numbox = sns.boxplot(
    data=telcomvar[["tenure", "MonthlyCharges", "TotalCharges"]], palette="Set2"
)
plt.title("Check outliers of standardized tenure, MonthlyCharges and TotalCharges")


# 由以上结果可以看出，在三个变量中不存在明显的异常值


# In[30]:


# 查看对象类型字段中存在的值
def uni(columnlabel):
    print(columnlabel, "--", telcomvar[columnlabel].unique())  # unique函数去除其中重复的元素，返回唯一值


telcomobject = telcomvar.select_dtypes(["object"])
for i in range(0, len(telcomobject.columns)):
    uni(telcomobject.columns[i])


# 综合之前的结果来看，在六个变量中存在No internet service，即无互联网服务对客户流失率影响很小，这些客户不使用任何互联网产品，因此可以将No internet service 和 No 是一样的效果，可以使用 No 替代 No internet service


# In[31]:


telcomvar.replace(to_replace="No internet service", value="No", inplace=True)
telcomvar.replace(to_replace="No phone service", value="No", inplace=True)
for i in range(0, len(telcomobject.columns)):
    uni(telcomobject.columns[i])


# In[32]:


# 使用Scikit-learn标签编码,将分类数据转换为整数编码
def labelencode(columnlabel):
    telcomvar[columnlabel] = LabelEncoder().fit_transform(telcomvar[columnlabel])


for i in range(0, len(telcomobject.columns)):
    labelencode(telcomobject.columns[i])


for i in range(0, len(telcomobject.columns)):
    uni(telcomobject.columns[i])


# ## 6、构建模型


# ### （1）建立训练数据集和测试数据集


# In[33]:


"""
我们需要将数据集拆分为训练集和测试集以进行验证。
由于我们所拥有的数据集是不平衡的，所以最好使用分层交叉验证来确保训练集和测试集都包含每个类样本的保留人数。
交叉验证函数StratifiedShuffleSplit，功能是从样本数据中随机按比例选取训练数据（train）和测试数据（test）
参数 n_splits是将训练数据分成train/test对的组数，可根据需要进行设置，默认为10
参数test_size和train_size是用来设置train/test对中train和test所占的比例
参数 random_state控制是将样本随机打乱
"""
X = telcomvar
y = telcom["Churn"].values


sss = StratifiedShuffleSplit(n_splits=5, test_size=0.2, random_state=0)
print(sss)
print("训练数据和测试数据被分成的组数：", sss.get_n_splits(X, y))


# In[34]:


# 建立训练数据和测试数据
for train_index, test_index in sss.split(X, y):
    print("train:", train_index, "test:", test_index)
    X_train, X_test = X.iloc[train_index], X.iloc[test_index]
    y_train, y_test = y[train_index], y[test_index]


# In[35]:


# 输出数据集大小
print("原始数据特征：", X.shape, "训练数据特征：", X_train.shape, "测试数据特征：", X_test.shape)


print("原始数据标签：", y.shape, "   训练数据标签：", y_train.shape, "   测试数据标签：", y_test.shape)


# ### （2）选择机器学习算法


# In[36]:

""" # 指定eval_metric
xgb = xgboost.XGBClassifier(eval_metric=["logloss", "auc", "error"]) """

# 使用分类算法，这里选用10种分类算法
Classifiers = [
    ["Random Forest", RandomForestClassifier()],
    ["Support Vector Machine", SVC()],
    ["LogisticRegression", LogisticRegression()],
    ["KNN", KNeighborsClassifier(n_neighbors=5)],
    ["Naive Bayes", GaussianNB()],
    ["Decision Tree", DecisionTreeClassifier()],
    ["AdaBoostClassifier", AdaBoostClassifier()],
    ["GradientBoostingClassifier", GradientBoostingClassifier()],
    # ["XGB", XGBClassifier()],
    ["XGB", XGBClassifier(eval_metric=["logloss", "auc", "error"])],
    ["CatBoost", CatBoostClassifier(logging_level="Silent")],
]


# ### （3）训练模型


# In[37]:

Classify_result = []
names = []
prediction = []
for name, classifier in Classifiers:
    classifier = classifier
    classifier.fit(X_train, y_train)
    y_pred = classifier.predict(X_test)
    recall = recall_score(y_test, y_pred)
    precision = precision_score(y_test, y_pred)
    class_eva = pd.DataFrame([recall, precision])
    Classify_result.append(class_eva)
    name = pd.Series(name)
    names.append(name)
    y_pred = pd.Series(y_pred)
    prediction.append(y_pred)


# ### （4）评估模型


# In[38]:


# 评估模型
"""
召回率（recall）的含义是：原本为对的当中，预测为对的比例（值越大越好，1为理想状态）
精确率、精度（precision）的含义是：预测为对的当中，原本为对的比例（值越大越好，1为理想状态）
F1分数（F1-Score）指标综合了Precision与Recall的产出的结果
F1-Score的取值范围从0到1的，1代表模型的输出最好，0代表模型的输出结果最差。
"""


names = pd.DataFrame(names)
names = names[0].tolist()
result = pd.concat(Classify_result, axis=1)
result.columns = names
result.index = ["recall", "precision", "f1score"]
# result.index = ["recall", "precision"]
result


# 综上所述，在10种分类算法中朴素贝叶斯（Naive Bayes）的F1分数最大为63.31%，所以使用朴素贝叶斯模型效果最好。


# ## 7、实施方案
# 预测数据集特征（由于没有提供预测数据集，这里选取后10行作为需要预测的数据集）
pred_X = telcomvar.tail(10)
# 提取customerID
pre_id = telcom_id.tail(10)
# 使用朴素贝叶斯方法，对预测数据集中的生存情况进行预测
model = GaussianNB()
model.fit(X_train, y_train)
pred_y = model.predict(pred_X)
# 预测结果
predDf = pd.DataFrame({"customerID": pre_id, "Churn": pred_y})
predDf
