from PIL import Image, ImageDraw, ImageFont, ImageFilter
import random

# 随机字母
def randChar():
    return chr(random.randint(65, 90))


# 随机颜色
def randColor():
    return (random.randint(32, 127), random.randint(32, 127), random.randint(32, 127))


# 随机颜色
def randColor2():
    return (random.randint(64, 255), random.randint(64, 255), random.randint(64, 255))


# 240*60
width = 60 * 4
height = 60
image = Image.new("RGB", (width, height), (255, 255, 255))
# Font
font = ImageFont.truetype("arial.ttf", 36)
# Draw
draw = ImageDraw.Draw(image)
# 填充像素
for x in range(width):
    for y in range(height):
        draw.point((x, y), fill=randColor())
# 输出文字
for t in range(4):
    draw.text((60 * t + 10, 10), randChar(), font=font, fill=randColor2())
# 模糊
# img = ImageFilter.BLUR(img)
image = image.filter(ImageFilter.BLUR)
image.save("verCode.png", "jpeg")
