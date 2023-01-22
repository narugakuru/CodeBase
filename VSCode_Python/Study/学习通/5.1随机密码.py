import random

# 生成字母验证码图像，熟悉Image、ImageDraw、ImageFont、ImageFilter模块中常见方法的使用。
checkcode = ""

for i in range(4):
    number = random.randint(0, 100)
    number = number % 3
    if number == 0:
        temp = chr(random.randint(65, 90))
    elif number == 1:
        temp = chr(random.randint(97, 122))
    else:
        temp = random.randint(0, 9)
    checkcode += str(temp)
print("本次生成的验证码是：%s" % checkcode)
