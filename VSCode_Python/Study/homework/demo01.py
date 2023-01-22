""" # string
string = "python"
print(string[0:6:2])
print(string[:])
print(string[::])
print(string[::-1])
# 取奇数
print(string[1::2]) """

# list，可修改，可嵌套
list1 = ["html", "css", "javascript"]
for v in list1:
    print(v)

del list1[0]  # 删除指定位置
list1.pop()  # 删除最后一个元素
list1.remove("css")  # 删除指定值
list1.sort()  # 排序

i = 0
while i < len(list1):
    print(list1[i])
    i += 1

# d[l1[index]]=l2[index]
