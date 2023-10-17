import pandas as pd
import matplotlib as mpl
import matplotlib.pyplot as plt

df = pd.read_csv("彩票数据.csv", header=None, index_col=0)
# 拿出红球：1-6列，所有行
redBall = df.loc[:, 1:6]
# 蓝球，7列
bullBall = df.loc[:, 7]
# 统计红球,降维flatten()
redCount = pd.value_counts(redBall.values.flatten())
# 统计蓝球
bullCount = pd.value_counts(bullBall.values)

# print(redCount)
# print(bullCount)

# 生成图表
fig, ax = plt.subplots(2, 1)  # 创建2个图表
ax[0].pie(redCount, labels=redCount.index, radius=1, wedgeprops={"width": 0.3})
ax[0].pie(bullCount, labels=bullCount.index, radius=0.5, wedgeprops={"width": 0.2})
plt.show()