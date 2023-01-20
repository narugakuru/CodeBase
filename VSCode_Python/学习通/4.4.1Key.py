# s = input("input please：")


def count_each_char_2(string):
    res = {}
    for i in string:
        res[i] = res.get(i, 0) + 1
        # 通过key统计，res.get(i, 0) + 1增加的是key对应的值
    return res


print(count_each_char_2("aenabsascd"))

""" s = input("请输入一个字符串:\n")
letters = 0
space = 0
digit = 0
others = 0
i = 0

while i < len(s):
    c = s[i]
    if c.isalpha():
        letters += 1
    elif c.isspace():
        space += 1
    elif c.isdigit():
        digit += 1
    else:
        others += 1
    i += 1

print("letters=%d,space=%d,digit=%d,others=%d" % (letters, space, digit, others))
 """

""" def count_each_char_1(string):
    res = {}
    for i in string:
        if i not in res:
            res[i] = 1
        else:
            res[i] += 1
    return res


print(count_each_char_1("aenabsascd"))
 """
# sort

""" import operator


def count_each_char_sort_value(string):
    res = {}
    for i in string:
        res[i] = res.get(i, 0) + 1
    res = sorted(res.items(), key=operator.itemgetter(1), reverse=True)
    return res


print(count_each_char_sort_value("aenabsascd")) """
