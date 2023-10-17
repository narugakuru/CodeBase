import math
import threading
import time

import cv2
import pynput.mouse

from wind_mouse import wind_mouse
from ScreenShot import screenshot, area, half_area, ScreenX, ScreenY
from SendInput import *
from pynput.mouse import Listener
from detect import Detector, visualize

is_x2_pressed = False

# 位置变量0~0.5直接调整，越高越接近头 头:0.45(容易封号) 胸0.2
head = 0.2


def mouse_listener():
    with Listener(on_click=mouse_click) as listener:
        listener.join()


def mouse_click(x, y, button, pressed):
    global is_x2_pressed
    global head
    if pressed and button == pynput.mouse.Button.x2:
        head = 0.2
        is_x2_pressed = True
    elif not pressed and button == pynput.mouse.Button.x2:
        is_x2_pressed = False
    if pressed and button == pynput.mouse.Button.x1:
        head = 0.47
        is_x2_pressed = True
    elif not pressed and button == pynput.mouse.Button.x1:
        is_x2_pressed = False


def run():
    global is_x2_pressed
    # Load model
    detect = Detector(model_path=b"./yolov8n_fp16.trt",
                      dll_path="./yolov8.dll")
    # 读取图片
    while True:
        im = screenshot()
        im0 = im
        # 推理
        # A = time.time()
        result = detect.predict(im)
        # B = time.time()
        # print(B-A)
        # 处理推理内容
        distance_list = []
        target_list = []
        for temp in result:
            # 画框
            im0 = visualize(im, result)
            # 将xyxy(左上角+右下角bwb)格式转为xywh(中心点+宽长)格式，并除上w，h做归一化，转化为列表再保存
            # 坐标是相对与识别框左上角为原点的坐标
            # xywh = (xyxy2xywh(torch.tensor(xyxy).view(1, 4))).view(-1).tolist()  # normalized xywh
            *xywh, cls, conf = temp
            X = xywh[0] - half_area
            Y = xywh[1] - half_area

            distance = math.sqrt(X ** 2 + Y ** 2)
            xywh.append(distance)
            distance_list.append(distance)
            target_list.append(xywh)
            # print("xywh:", xywh)
            # print(target_list)
        if result.any():
            target_info = target_list[distance_list.index(min(distance_list))]
            # target_X = int(target_info[0] - half_area)
            # print(half_area)
            target_X = int(target_info[0] - half_area + target_info[2]//2)
            # target_Y = int(target_info[1] - half_area - target_info[3] * head)
            target_Y = int(target_info[1] - half_area +
                           target_info[3]//2 - target_info[3] * head)
            # print(target_X, target_Y)
            # 加个距离非零判断 防止抖动
            # and (abs(target_X) > 3 or abs(target_Y) > 3)
            if is_x2_pressed and (abs(target_X) > 3 or abs(target_Y) > 3):
                points = []
                # 局内灵敏度步枪8，狙击枪12ww
                # 局内灵敏度5，步长15 步枪效果不错
                wind_mouse(0, 0, target_X, target_Y, G_0=9, W_0=3,
                           M_0=15, D_0=25,
                           move_mouse=lambda x, y: points.append([x, y]))  # 提高M_0可以提高锁头速度
                pre_x = 0
                pre_y = 0
                # A = time.time()
                for x, y in points:
                    move_x = x - pre_x
                    move_y = y - pre_y
                    mouse_xy(move_x, move_y)
                    pre_x = x
                    pre_y = y
                # B = time.time()
                # print(B - A)
                # time.sleep(0.02)  # 主动睡眠，防止推理过快,鼠标移动相同的两次
        cv2.imshow('asdqw', im0)
        cv2.waitKey(1)


if __name__ == "__main__":
    threading.Thread(target=mouse_listener).start()
    run()
