import turtle


def draw_branch(branch_length):
    if branch_length > 5:
        # 右侧树枝
        turtle.forward(branch_length)
        print("前进", branch_length)
        turtle.right(20)
        print("右转20°")
        draw_branch(branch_length - 15)
        # 左侧树枝
        turtle.left(40)
        print("左转40°")
        draw_branch(branch_length - 15)
        # 返回之前的树枝
        turtle.right(20)
        print("右转20°")
        turtle.backward(branch_length)
        print("后退", branch_length)


# 一直前进右转直到树枝末梢，回退至长度30，


def draw_init():
    turtle.left(90)
    turtle.penup()
    turtle.backward(200)
    turtle.pendown()
    turtle.speed(10)


if __name__ == "__main__":
    draw_init()
    draw_branch(90)
    # turtle.right(180)
    # draw_branch(90)
    turtle.done()

