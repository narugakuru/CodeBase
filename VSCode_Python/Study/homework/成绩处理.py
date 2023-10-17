from mmap import ACCESS_COPY
import pandas as pd
import matplotlib as mpl
import matplotlib.pyplot as plt


# 处理[1]行以后的[5]列，[1]列为姓名
file = open("190702utf8.csv", "r", encoding="utf-8")
lines = []  # list
# 去除"大学体育Ⅳ",
subjects = [
    "软件工程",
    "Web开发技术基础",
    "大学英语听说IV",
    "计算机网络",
    "马克思主义基本原理",
    "形势与政策Ⅳ",
    "Python语言程序设计",
    "数据存储综合实训",
]
acount = {}

for line in file:
    line = line.replace("\n", "")
    lines.append(line.split(","))  # line作为列表加入lines的元素组

# print(lines)

for line in lines:
    # 姓名
    name = line[1]
    # 成绩
    score = line[5]
    # 学科
    subject = line[2]

    if subject in subjects:
        score = int(score)
        acount[name] = acount.get(name, 0) + score

# 日语换到英语
acount["罗礼政"] += 86
print(acount)


file.close()

"""{'罗礼政': 691, '程鹏华': 615, '吴瑞亮': 629, '艾家伟': 564, '文佳妮': 655, '刘明丙': 605, '张军校': 608, '温旗花': 656, '潘卫平': 558,  '张观娣': 647, '潘东日': 619, '占军潇': 633, '王志文': 603, '尹卓斌': 629, '陈博飞': 653, '万子闻': 709, '周伯文': 658, '姚逸平': 719, '江天赐': 624, '卢普盛': 650, '吴忧': 561, '王楚棋': 611, '胡嘉伟': 590, '肖东海': 639, '肖子晨': 650, '黎一豪': 660, '付茗昱': 578, '戴晨曦': 650, '黄敏': 665, '徐茂滨': 604, '谢文浩': 631, '邓智铖': 711, '金新江': 660, '李兵': 630, '王金文': 639, '郭彦凤': 665, '万乐杰': 681, '郑欣雨': 664, '王耀昆': 662, '郭详': 532}"""
