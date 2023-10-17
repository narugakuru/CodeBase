import win32ui
import win32gui
import win32con
import numpy as np
import cv2
import sys
import os
ROOT = os.getcwd()
sys.path.append(os.path.join(ROOT, "dxshot.pyd"))
print(sys.path)
import dxshot


shape = 640
left, top = (2560 - shape) // 2, (1600 - shape) // 2
right, bottom = left + shape, top + shape
region = (left, top, right, bottom)

cam = dxshot.create(output_color="BGR")


def grab_gpt(window_title, grab_rect=None):
    hwnd = win32gui.FindWindow(None, window_title)
    hwndDC = win32gui.GetWindowDC(hwnd)
    mfcDC = win32ui.CreateDCFromHandle(hwndDC)
    saveDC = mfcDC.CreateCompatibleDC()
    x, y, w, h = grab_rect
    saveBitMap = win32ui.CreateBitmap()
    saveBitMap.CreateCompatibleBitmap(mfcDC, w, h)
    saveDC.SelectObject(saveBitMap)

    saveDC.BitBlt((0, 0), (w, h), mfcDC, (x, y), win32con.SRCCOPY)

    signed_ints_array = saveBitMap.GetBitmapBits(True)
    img = np.frombuffer(signed_ints_array, dtype="uint8")
    img.shape = (h, w, 4)
    win32gui.DeleteObject(saveBitMap.GetHandle())
    saveDC.DeleteDC()
    mfcDC.DeleteDC()
    win32gui.ReleaseDC(hwnd, hwndDC)  # 释放 DC 资源
    img = cv2.cvtColor(img, cv2.COLOR_RGBA2RGB)
    return img


def dx_cap():
    img = cam.grab(region=region)
    if img is not None:
        return img
