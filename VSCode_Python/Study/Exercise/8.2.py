# 学生成绩的字典保存为二进制文件，然后读取显示

from typing import Text
import jieba

f1 = open(r"D:\Code\VSCode_Python\text1.txt", "r", encoding="utf-8").read()
txt = jieba.lcut(f1)
counts = {}

for word in txt:
    counts[word] = counts.get(word, 0) + 1

# print(counts.items())
f2 = open(r"D:\Code\VSCode_Python\text2.text", "wb+")
# 由于wb+模式只能将二进制数据写入，故需调用encode转换编码
f2.write(f1.encode("utf-8"))
