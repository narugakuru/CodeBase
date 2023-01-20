# -*- coding:utf-8 -*-
from os import path
import matplotlib.pyplot as plt  # 绘制图片
from scipy.misc import imread  # 读取图片
from wordcloud import WordCloud, ImageColorGenerator
import jieba  # 结巴分词

text_address = path.abspath("test1.txt")
text = open(text_address).read()  # 读取文本
text_cut = jieba.cut(text)  # 分词
new_textlist = " ".join(text_cut)  # list组合

pic = imread("D:\\Code\\VSCode_Python\\words\\pic1.jpg")  # 读取图片
pic_color = ImageColorGenerator(pic)  # 根据图片生成颜色函数
wc = WordCloud(
    background_color="white",  # 构造wordcloud类
    mask=pic,
    max_font_size=40,
    random_state=30,
    font_path="font.ttf",
    max_words=500,
    min_font_size=2,
    color_func=pic_color,
)
wc.generate(new_textlist)  # 生成词云图
plt.figure()  # 画图
plt.imshow(wc)
plt.axis("off")
plt.show()
wc.to_file(path.join(path.dirname("test.txt"), "zjw5.png"))  # 保存图片
