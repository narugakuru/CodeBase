# 输出未出现的字母
string = input("enter a sentence:")
strLower = string.lower()
list1 = []
alphabetList = [
    "a",
    "b",
    "c",
    "d",
    "e",
    "f",
    "g",
    "h",
    "i",
    "j",
    "k",
    "l",
    "m",
    "n",
    "o",
    "p",
    "q",
    "r",
    "s",
    "t",
    "u",
    "v",
    "w",
    "x",
    "y",
    "z",
]
# 提取字母

for i in alphabetList:
    if i in strLower:  # 是否在字母表
        if i not in list1:  # 是否在list,否则加入
            list1.append(i)

resultStr = ""

for i in alphabetList:
    if i not in list1:  # 如果不在list中则加入resultStr
        resultStr += i

print("not in the sentence:" + resultStr.upper())
