import jieba

# 导入文本
txt = open("D:\\BaiduNetdiskDownload\\三体.txt", "r", encoding="utf-8").read()
words = jieba.lcut(txt)  # words是{，，，}格式列表
counts = {}  # 空字典

# 词频统计，去除单个字的词
for word in words:
    if len(word) > 1:
        counts[word] = counts.get(word, 0) + 1


""" # 删除excludes无意义数据
def del_excludes():
    excludes = {}  # 设置需排除词
    for word in excludes:
        del counts[word] """


# 降序排序
List_counts = list(counts.items())
# key为比较的参考，即以counts.value()为依据进行逆向排序
List_counts.sort(key=lambda x: x[1], reverse=True)

# 采用固定格式输出
for i in range(10):
    word, count = List_counts[i]
    pstr = str(i + 1) + ":"
    print(pstr, end=" ")
    print(word, count)
