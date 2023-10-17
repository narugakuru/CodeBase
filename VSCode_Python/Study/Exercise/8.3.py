# 读取文本，大小写互相转换

f = open("D:\Code\VSCode_Python\english.txt", "r", encoding="utf-8")
txt = f.read()
retxt = ""
for word in txt:
    if word.isupper():
        word = word.lower()
    else:
        word = word.upper()
    retxt += word
print(retxt)