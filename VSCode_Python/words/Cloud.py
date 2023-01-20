import jieba
import wordcloud
import matplotlib.pyplot as plt  # 绘制图片
import numpy as np  # 机器学习算法中大部分都是调用Numpy库来完成基础数值计算的。
from PIL import Image
from wordcloud.wordcloud import (
    STOPWORDS,
)  # Python图像库PIL(Python Image Library)是python的第三方图像处理库

# 打开文件并过滤单个字
def getText(file):
    with open(file, "r", encoding="UTF-8") as words_list:
        words_list = words_list.read()
        words_list = jieba.lcut(words_list)  # list
        # 清洗一个字的词
        words_list = [word for word in words_list if len(word.strip()) > 1]
    return words_list


# 打开文件
words_list = getText("D:\\BaiduNetdiskDownload\\三体.txt")
text = " ".join(words_list)  # 使用jieba分词
mask = np.array(Image.open("D:\Code\VSCode_Python\words\pic1.jpg"))  # 使用背景图片
STOPWORDS = {"一个", "我们", "这个", "自己", "没有", "已经", "你们", "不是", "什么", "他们", "就是", "可以"}
wc = wordcloud.WordCloud(
    mask=mask,
    font_path="STHUPO.TTF",
    width=800,
    height=600,
    mode="RGBA",
    background_color=None,
    stopwords=STOPWORDS,
)  # 增加了背景图片，字体，大小等内容
wc = wc.generate(text)
# 显示词云
plt.imshow(wc, interpolation="bilinear")
plt.axis("off")
plt.show()
# 保存文件
wc.to_file("ciyun.png")
