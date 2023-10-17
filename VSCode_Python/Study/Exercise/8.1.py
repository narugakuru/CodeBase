# -* - coding: UTF-8 -*
# 读取一个文件，显示除了#开头以外的所有行

f = open("D:\\Code\\VSCode_Python\\Exercise\\5.5.16.Jieba.py", "r", encoding="utf-8")
# print(type(f.read()))

txt = f.readline()

while txt != " ":
    if txt[0] != "#":
        print(txt)
    txt = f.readline()