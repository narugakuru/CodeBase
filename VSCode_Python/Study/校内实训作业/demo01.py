with open("data721.txt", "r", encoding="utf-8") as f:
    c = f.read()
    # f.write("114514")
    print(c)


file = open(r"D:\Code\VSCode_Python\校内实训作业\temp.txt", "w+")
data = ["123", "abc", "456"]
file.writelines(data)
file.seek(0)
for row in file:
    print(row, type(row))
file.close()


# 1.
c = input("请输入一个字符串：")
f = open("data721.txt", "w")
f.write(c)
f.close()

# 2.
c = input("请输入一个字符：")
f = open("data721.txt")
cs = f.read()
print(c, "在字符串", cs, "中出现的次数为：", cs.count(c))
f.close()

# 3.
f = open("data721.txt", encoding="utf-8")
cs = f.read()
f.close()
f = open("data722.txt", "w", encoding="utf-8")
cs = f.write(cs[::-1])
f.close()

