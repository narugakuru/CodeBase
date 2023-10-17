file = open("1907伪.cvs", "r", encoding="utf-8")
lines = []  # list

for line in file:
    line = line.replace("\n", "")
    lines.append(line.split(","))  # line作为列表加入lines的元素组

# print(lines)

file_new = open("1907伪总.cvs", "w+", encoding="utf-8")

# 表头添加总分字段
lines[1].append("总分")
# 添加总分
for i in range(len(lines) - 2):
    # 处理第二行以后的数据
    idx = i + 2
    sumScore = 0
    # 处理idx行的列数据
    for j in range(len(lines[idx]) - 1):
        j += 1  # 从第二列数据开始读
        if lines[idx][j].isnumeric():
            # 若为数字，则累加
            sumScore += int(lines[idx][j])
    # 总分加入列尾
    lines[idx].append(str(sumScore))

# 写入处理后数据
for line in lines:
    file_new.write(",".join(line) + "\n")

file.close()
file_new.close()