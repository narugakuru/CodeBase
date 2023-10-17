import win32api
import time
import win32con
import pythoncom


def win_hc():
    # 操作回车键
    win32api.keybd_event(13, 0, 0, 0)
    win32api.keybd_event(13, 0, win32con.KEYEVENTF_KEYUP, 0)
    time.sleep(1)
