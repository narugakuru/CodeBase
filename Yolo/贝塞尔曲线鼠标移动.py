import math
import win32api
import win32con


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y


# 计算组合数
def combination(n, k):
    if k == 0 or k == n:
        return 1.0
    elif k == 1:
        return float(n)
    else:
        result = 1.0
        for i in range(1, k+1):
            result *= float(n - i + 1) / i
        return result


# 计算贝塞尔曲线上一点的坐标
def bezier(pts, t):
    n = len(pts) - 1
    x = 0.0
    y = 0.0
    for i in range(n + 1):
        b = combination(n, i) * math.pow(t, i) * math.pow(1.0 - t, n - i)
        x += b * pts[i].x
        y += b * pts[i].y
    return Point(int(x), int(y))


# 移动鼠标指定的相对位移
def move_mouse_relative(dx, dy):
    win32api.mouse_event(win32con.MOUSEEVENTF_MOVE, dx, dy, 0, 0)


# 设置移动距离
dx = 200
dy = 150

# 设置控制点，可以根据需要自行调整
control_points = [
    Point(0, 0),
    Point(dx // 2, dy),
    Point(dx, dy // 2),
    Point(dx, dy),
]

# 计算移动轨迹
num_points = 100  # 移动轨迹上点的数量
path = []

for i in range(num_points + 1):
    t = i / float(num_points)
    p = bezier(control_points, t)
    path.append(p)
    for j in range(1, len(path)):
        x = path[i].x - path[i - 1].x
        y = path[i].y - path[i - 1].y
        move_mouse_relative(x, y)
        break


def main():
    # 设置移动距离
    dx = 500
    dy = 200

    # 设置控制点，可以根据需要自行调整
    control_points = [Point(0, 0), Point(dx, dy/2), Point(dx, dy)]

    # 计算移动轨迹
    num_points = 50  # 移动轨迹上点的数量
    path = []
    for i in range(num_points + 1):
        t = i / float(num_points)
        p = bezier(control_points, t)
        path.append(p)

    # 执行移动操作
    for i in range(1, len(path)):
        x = path[i].x - path[i - 1].x
        y = path[i].y - path[i - 1].y
        moveMouseRelative(x, y)
        time.sleep(0.01)  # 控制移动的速度，可以根据需要自行调整
