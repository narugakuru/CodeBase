import numpy as np
from sklearn.ensemble import RandomForestClassifier, VotingClassifier
from sklearn.feature_selection import SelectKBest, chi2
from sklearn.linear_model import LogisticRegression
from sklearn.neighbors import KNeighborsClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
import pandas as pd
from sklearn import model_selection
import matplotlib.pyplot as plt
import seaborn as sns

# import sklearn.linear_model import logic
from sklearn import svm
import sklearn.pipeline as pip

train_df = pd.read_csv("D:/Code/VSCode_Python/data/titanic_data/train.csv")
# print(train_df.head())

# 特征工程
clf = print(train_df.info())
train_df.loc[train_df.Embarked.isnull(), "Embarked"] = train_df.Embarked.mode()[0]
train_df.Cabin.fillna(
    "Unkonwn", inplace=True
)  # train_df.Age.fillna(train_df.Age.median(), inplace=True)
train_df.Cabin.apply(lambda x: x[0])
tg = train_df.Ticket.value_counts()  # 同行人
train_df["Ticket_group"] = train_df.Ticket.apply(lambda x: tg[x])
train_df["f_name"] = train_df.Name.apply(lambda x: x[0 : x.find(",")])
train_df["title"] = train_df.Name.apply(lambda x: x[x.find(",") + 1 : x.find(".")])
train_df["l_name"] = train_df.Name.apply(lambda x: x[x.find(".") + 1 :])

titles = train_df["title"].unique()
for title in titles:
    m_age = train_df.loc[train_df.title == title, "Age"].median()
    train_df.loc[(train_df.Age.isnull()) & (train_df.title == title), "Age"] = m_age
print("---特征工程：train_df.info----")
print(train_df.info())

# 连续数值型的转换
# ------family_size--------
train_df["family_size"] = train_df.Parch + train_df.SibSp + 1
# train_df['family_size'].plot(kind='hist')
df2 = train_df[["family_size", "Survived"]].groupby(["family_size"]).mean()
train_df["fs_type"] = "a"
train_df.loc[(train_df.family_size > 1) & (train_df.family_size < 4), "fs_type"] = "b"
train_df.loc[(train_df.family_size >= 4), "fs_type"] = "c"
# ------family_size------end

# ------Age------------
df2 = train_df[["Age", "Survived"]].groupby(["Age"])
train_df["age_type"] = "a"
train_df.loc[(train_df.Age > 16) & (train_df.Age < 40), "age_type"] = "b"
train_df.loc[(train_df.Age >= 40), "age_type"] = "c"
plt.hist(train_df.Age[train_df.Survived == 0], label="0")
plt.hist(train_df.Age[train_df.Survived == 1], label="1")
plt.legend()
# ------Age------------end

# ------Fare----------
df2 = train_df[["Fare", "Survived"]].groupby(["Fare"]).mean()
# df2.plot(kind='bar')
train_df["fare_type"] = "a"
train_df.loc[(train_df.Fare > 13) & (train_df.Fare <= 25), "fare_type"] = "b"
train_df.loc[(train_df.Fare > 25) & (train_df.Fare <= 38), "fare_type"] = "c"
train_df.loc[(train_df.Fare > 38) & (train_df.Fare <= 52), "fare_type"] = "d"
train_df.loc[(train_df.Fare > 52) & (train_df.Fare <= 102), "fare_type"] = "e"
train_df.loc[(train_df.Fare > 102), "fare_type"] = "f"
plt.hist(train_df.Fare[train_df.Survived == 0], label="0", histtype="step", bins=20)
plt.hist(train_df.Fare[train_df.Survived == 1], label="1", histtype="step", bins=20)
plt.legend()
# ------Fare----------end

# 加一个child字段 对结果好像没啥帮助
# train_df.loc[:,'child'] = 0
# train_df.loc[train_df.Age>=18, 'child']=1

# 是否有家人遇难，是否有家人获救
train_df["Family_Survival"] = 0.5
for grp, grp_df in train_df[
    [
        "Survived",
        "Name",
        "f_name",
        "Fare",
        "Ticket",
        "PassengerId",
        "SibSp",
        "Parch",
        "Age",
        "Cabin",
    ]
].groupby(["f_name", "Fare"]):
    if len(grp_df) != 1:
        for ind, row in grp_df.iterrows():
            # 删除自己遇难信息
            smax = grp_df.drop(ind)["Survived"].max()
            smin = grp_df.drop(ind)["Survived"].min()
            passID = row["PassengerId"]
            if smax == 1.0:
                train_df.loc[train_df["PassengerId"] == passID, "Family_Survival"] = 1
            elif smin == 0.0:
                train_df.loc[train_df["PassengerId"] == passID, "Family_Survival"] = 0
        # print("Number of passengers with family survival information:",
        #       train_df.loc[train_df['Family_Survival'] != 0.5].shape[0])
#
# PassengerId                                 453
# Survived                                      0
# Pclass                                        1
# Name            Foreman, Mr. Benjamin Laventall
# Sex                                        male
# Age                                        30.0
# SibSp                                         0
# Parch                                         0
# Ticket                                   113051
# Fare                                      27.75
# Cabin                                      C111
# Embarked                                      C
# Ticket_group                                  1
# f_name                                  Foreman
# title                                        Mr
# l_name                       Benjamin Laventall
# family_size                                   1

# 选择算法交叉

plt.show()
# 划分特征数据和标签数据
train_df = train_df.drop(
    ["Age", "Fare", "Parch", "SibSp", "family_size", "f_name", "l_name"], axis=1
)
X = train_df.iloc[:, 2:]
y = train_df.iloc[:, 1]
# X = X.select_dtypes(include=['int'])  # 因为字符串不能转为float，先只留下number
# X = X.dropna(axis=1) # 把有空的行删掉
X = X.drop("Name", axis=1)
X = pd.get_dummies(X)
# print(X.head())
# print(y.head())
# 定义一个算法,建立模型和验证模型

clf = DecisionTreeClassifier()  # 1 dtreep
clf1 = RandomForestClassifier()  #  2 随机森林
clf2 = SVC(kernel="linear", C=5)  # 3 支持向量积
clfk = KNeighborsClassifier(n_neighbors=3)  # 4 k临近
# 逻辑回归
clf3 = LogisticRegression(max_iter=1000)
# clf = MLPClassifier(max_iter=1000)  # 5 神经网络 设置感知器，中间是隐藏层
clf4 = MLPClassifier(
    max_iter=1000, hidden_layer_sizes=(100, 60), alpha=0.1
)  # 设置感知器，中间是隐藏层

# 特征选择
print("---------特征选择:X.info()----------")
# print(X.info())

X = SelectKBest(chi2, k=700).fit_transform(X, y)

vof = VotingClassifier([("clf2", clf2), ("clf3", clf3), ("clf4", clf4)])
clf_p = pip.Pipeline(
    [("select", SelectKBest(chi2, k=700)), ("classify", RandomForestClassifier())]
)
parm_grid = [
    {
        "classify__n_estimators": [90, 100, 110, 500],
        "classify__cirterion": ["gini"],
        "select__criterion": ["gini"],
    }
]
gscv = model_selection.GridSearchCV(clf_p, param_grid=parm_grid, scoring="accuracy")

# 训练
clf.fit(X, y)
# print(clf.score(X, y)) # 神经网络这里用
sc = model_selection.cross_val_score(vof, X, y, scoring="accuracy")
# sc = model_selection.cross_val_score(clfk, X, y, scoring="accuracy")
print(sc.mean())
print("ending")
