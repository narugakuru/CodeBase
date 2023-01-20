import jieba
import os
import wordcloud


def clearTxt(words):
    words_list = {}  # 空字典
    # 词频统计，去除单个字的词
    for word in words:
        if len(word) == 1:
            continue
        else:
            words_list[word] = words_list.get(word, 0) + 1
    # 降序排序
    words_list = list(words_list.items())
    # key为比较的参考，即以counts.value()为依据进行逆向排序
    words_list.sort(key=lambda x: x[1], reverse=True)
    return words_list


def getText(file):

    txt = open(file, "r", encoding="utf-8").read()
    txt = jieba.lcut(txt)
    # txt = clearTxt(txt)

    return txt


directoryname = os.getcwd()
filename = "ci_yun"
txt = getText("D:\\BaiduNetdiskDownload\\三体.txt")


wordclouds = wordcloud.WordCloud(width=1000, height=800, margin=2).generate(txt)
wordclouds.to_file("{}.png".format(filename))
os.system("{}.png".format(filename))
