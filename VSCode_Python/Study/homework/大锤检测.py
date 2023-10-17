# AABB去掉B，AAA，去掉A
# 输入：2，helllo，wooooooow
# 输出：hello，woow

num = int(input("输入待检验字符串数量:"))
# str1 = "helllo"
# str2 = "wooooooow"
for i in range(num):
    str1 = input("输入待检验字符串:")

    for i in range(len(str1)):
        if str1[i] == str1[i + 1] & str1[i] == str1[i + 2]:
            str1.pop(i + 2)
        if str1[i] == str1[i + 1] & str1[i + 2] == str1[i + 3]:
            str1.pop(i + 3)

    print(str1)
