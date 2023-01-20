import time
import turtle


def drawGap():
    turtle.penup()
    turtle.fd(5)


def drawLine(draw):
    drawGap()
    if draw:
        turtle.pendown()
    else:
        turtle.penup()
    # 每段为40，画完右转90°
    turtle.fd(30)
    turtle.penup()
    turtle.fd(10)
    turtle.pendown()
    turtle.right(90)


def drawDigin(digit):
    # 七段数码管的绘制
    drawLine(True) if digit in [2, 3, 4, 5, 6, 8, 9] else drawLine(False)
    drawLine(True) if digit in [0, 1, 3, 4, 5, 6, 7, 8, 9] else drawLine(False)
    drawLine(True) if digit in [0, 2, 3, 5, 6, 8, 9] else drawLine(False)
    drawLine(True) if digit in [0, 2, 6, 8] else drawLine(False)
    turtle.left(90)
    drawLine(True) if digit in [0, 4, 5, 6, 8, 9] else drawLine(False)
    drawLine(True) if digit in [0, 2, 3, 5, 6, 7, 8, 9] else drawLine(False)
    drawLine(True) if digit in [0, 1, 2, 3, 4, 7, 8, 9] else drawLine(False)
    # 回归初始位置
    turtle.left(180)
    turtle.penup()
    turtle.fd(20)


def drawTime(time):
    turtle.pensize(5)
    turtle.speed(10)
    turtle.pencolor("red")
    for i in time:
        if i == "-":
            turtle.write("年", font=("Arial", 18, "normal"))
            turtle.pencolor("green")
            turtle.fd(40)
        elif i == "=":
            turtle.write("月", font=("Arial", 18, "normal"))
            turtle.pencolor("blue")
            turtle.fd(40)
        elif i == "+":
            turtle.write("日", font=("Arial", 18, "normal"))
            turtle.fd(40)
        else:
            drawDigin(eval(i))


if __name__ == "__main__":
    turtle.penup()
    # turtle.hideturtle()
    turtle.fd(-300)

    with open(
        "D:\\Code\\VSCode_Python\\上机考试\\time.csv", mode="r", encoding="utf-8"
    ) as f:
        timeList = f.readlines()
        tmp = timeList[1].replace("\n", "").split(",")
        h = tmp[0]
        m = tmp[1]
        s = tmp[2]
    currentTime = time.strftime("{}-{}={}+".format(h, m, s))
    drawTime(currentTime)
    turtle.hideturtle()
    turtle.done()