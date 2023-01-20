import pyautogui
import win32api
import win32con
import time
import pyautogui as pag
import random


def win_hc():
    # 操作回车键
    win32api.keybd_event(13, 0, 0, 0)
    win32api.keybd_event(13, 0, win32con.KEYEVENTF_KEYUP, 0)
    time.sleep(1)


def win_sc():
    #  操作删除键，删除定位到信息
    for a in range(10):
        win32api.keybd_event(8, 0, 0, 0)  # Backspace 删除
        win32api.keybd_event(8, 0, win32con.KEYEVENTF_KEYUP, 0)  # 释放按键
        time.sleep(0.5)


def win_ym_gd():
    #  移动鼠标到指定位置，  并滚动鼠标所在位置页面到底部
    x, y = pag.position()  #  获取当前鼠标位置
    pos_str = "当前鼠标位置:" + str(x).rjust(4) + "," + str(y).rjust(4)
    # print(pos_str)
    ym_gd = 1
    if x == "905" and y == "272":
        while ym_gd <= 10:
            win32api.mouse_event(win32con.MOUSEEVENTF_WHEEL, 0, 0, -800)  #  滚动鼠标
            ym_gd += 1
            time.sleep(0.2)
    else:
        for i in range(10):
            pyautogui.moveTo(905, 272, duration=0.25)  # 移动鼠标到指定位置
        while ym_gd <= 10:
            win32api.mouse_event(win32con.MOUSEEVENTF_WHEEL, 0, 0, -800)  #  滚动鼠标
            ym_gd += 1
            time.sleep(0.2)


def win_jt_sang():
    #  箭头键 上
    pag.press("up")


def win_jt_xia():
    #  箭头键 下
    pag.press("down")


def win_jt_zuo():
    #  箭头键 左
    pag.press("left")


def win_jt_you():
    #  箭头键 右
    pag.press("right")


def win_sjs():
    #  生成随机的手机号
    sjs = random.randint(18900000000, 18999999999)
    return sjs


def win_dj(x, y):
    #   鼠标左键单击一个位置
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTDOWN, x, y, 0, 0)  # 按下
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTUP, x, y, 0, 0)  # 抬起


def win_sj():
    #  鼠标左键双击一个位置
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0)
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTUP, 0, 0, 0, 0)
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0)
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTUP, 0, 0, 0, 0)
    time.sleep(0.2)
