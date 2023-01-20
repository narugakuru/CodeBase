""" 生日悖论分析。生日悖论是指如果一个房间里有23人或以上，则至少有两个人生日相同的概率大于50%。
编写程序，输出在不同随机样本数量下，23人中至少两个人生日相同的概率。（提示：解题思路有点像之前课上讲的羊车门问题） 
生成23个1~366的随机数，至少两个数相同的概率
"""

from random import *

brithday = []  # 生成1000人的总样本
for i in range(1000):
    n = randint(1, 365)
    brithday.append(n)


def sam():
    res = []  # 存储23人的生日数据,从brithday中随机抽取样本

    for j in range(23):
        k = brithday[randint(0, 999)]
        res.append(k)

    for i in range(1, 23):
        # for j in range(1,23):
        for j in range(i):
            if res[i] == res[j]:
                return True
    return False


def main():
    counts = 0
    for i in range(100):
        if sam():
            counts += 1
        else:
            continue
    print("至少两人相同的概率是{}%".format(counts))


for i in range(10):
    main()