# 图片压缩，PIL等比例压缩，文件小于30kb
from PIL import Image

img = Image.open("D:\Code\VSCode_Python\words\pic1.jpg")
x, y = img.size
img.thumbnail((x // 10, y // 10))
img.show()
