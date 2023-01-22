import imageio
import wordcloud
import jieba

words = open("D:\\BaiduNetdiskDownload\\三体.txt", "r", encoding="utf-8").read()

word_list = jieba.lcut(words)
word_list = [word for word in word_list if len(word.strip()) > 1]  # 清洗一个字的词，得到列表类型

word_str = " ".join(word_list)
# print(word_str)
# print(word_list)

wc = wordcloud.WordCloud(
    font_path="simkai.ttf",  # 指定字体类型
    background_color="white",  # 指定背景颜色
    max_words=200,  # 词云显示的最大词数
    max_font_size=255,  # 指定最大字号
    mask=None,  # 指定模板)
)
wc.generate(word_str)
wc.to_file("wordC.png")