import turtle


def a():
    # turtle.fd(50)
    turtle.lt(90)


def d():
    turtle.right(90)
    # turtle.fd(50)


def w():
    turtle.forward(60)


# 绑定键盘
def use_key():
    turtle.onkey(w, "Up")
    turtle.onkey(d, "Right")
    turtle.onkey(a, "Left")
    turtle.listen()


# 鼠标点击
def getPos(x, y):
    print("(", x, ", ", y, ")")
    return


if __name__ == "__main__":
    turtle.setup(100, 100, 500, 50)
    turtle.onscreenclick(getPos, btn=1, add=None)
    use_key()
    turtle.done()
