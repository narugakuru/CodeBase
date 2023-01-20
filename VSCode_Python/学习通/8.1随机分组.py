import math
import random

fo = open("D:\\Code\\VSCode_Python\\软件1907班名单.csv", "r", encoding="utf-8")
ls = []
for line in fo:
    line = line.replace("\n", "")
    ls.append(line.split(","))
ls.pop(0)
"""
for value in ls:
    print(value)
"""
fo.close()
num_stu = len(ls) - 1  # 学生总数
# 每组人数
num_team = eval(input("表格中一共有{}位同学,输入每组分配的同学的数量").format(num_stu))
# 计算组数
teams = math.ceil(num_stu / num_team)
result = []
print("随机分组结果构成为,一共{}组，每组{}人".format(teams, num_team))
if num_stu != num_team * teams:
    print("其中最后一组有{}位同学".format(num_stu - (num_team * (teams - 1))))
for i in range(teams - 1):
    result.append([])
    for j in range(num_team):
        index = random.randint(0, len(ls) - 1)
        result[i].append(ls.pop(index))  # 随机取出
ls1 = []
for alfa in result:
    alfa = str(alfa).replace("\n", "")
    ls1.append(alfa.split(","))
ls1.pop(0)
for value1 in ls1:
    print(value1)
