import os
import platform
import sys
import threading
import time
from multiprocessing import Process
from pathlib import Path

import cv2
import numpy as np
import onnx
import onnxruntime as ort
import torch
import win32api
import win32con
import win32process
import winsound
from pynput import mouse

import dictMap
from PID import PID_GPT_PLUS
from grab import grab_gpt, dx_cap
from otherFeatures.automatic_armor_change import automatic_armor_change_func
from otherFeatures.shake_the_gun import shake_gan_main
from rw import write_key_value, _write_file, read_file, convert
from tools.logitech import Logitech

# 取当前py文件运行目录
FILE = Path(__file__).resolve()
ROOT = FILE.parents[0]  # YOLOv5 root directory
if str(ROOT) not in sys.path:
    sys.path.append(str(ROOT))  # add ROOT to PATH
if platform.system() != "Windows":
    ROOT = Path(os.path.relpath(ROOT, Path.cwd()))  # relative

CUDA_INFO = "YES" if torch.cuda.is_available() else "No"
print("CUDA available : " + CUDA_INFO)

# Select GPU or CPU
screen_width = 2560  # 分辨率x
screen_height = 1600080  # 分辨率y
imgsz = 416  # imgsz
conf_thres = 0.3  # 置信度
iou_thres = 0.25  # 交并集
grab_window_title = "Apex Legends"
grab_width = 416  # 截图range_x
grab_height = 416  # 截图range_y
re_x, re_y = 320, 320  # 显示框大小
lock_mode = True  # 是否开锁
show_win = False  # 是否显示 顶部识别框
half = False  # 是否开启半精度
aim_mod = 0  # 瞄准模式 0 左键 1 右 2 左右
rect = (
    int(screen_width / 2 - grab_width / 2),
    int(screen_height / 2 - grab_height / 2),
    grab_width,
    grab_height,
)  # 截图区域xywh坐标
weights = "apex_416_82.52w.onnx"
input_name = None
input_shape = None
output_names = None
session = None
# PID参数
pid_x_p = 0.3
pid_x_i = 0.03
pid_x_d = 0.01
pid_y_p = 0.3
pid_y_i = 0
pid_y_d = 0
# 偏移比例 胸到头
offset = 0.2

instantiation_pid_x = None
instantiation_pid_y = None


def init_model():
    global weights, input_name, input_shape, output_names, session, rect

    print("onnxruntime version:", ort.__version__)
    print("Available Providers:", ort.get_available_providers())
    rect = (
        int(screen_width / 2 - grab_width / 2),
        int(screen_height / 2 - grab_height / 2),
        grab_width,
        grab_height,
    )  # 截图区域xywh坐标
    print(grab_width, "grab_width")
    # onnx dml——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    weights = str(ROOT / "weights" / weights)  # 模型地址
    # 加载ONNX模型
    options = ort.SessionOptions()
    options.enable_profiling = False
    options.intra_op_num_threads = 4  # 启用所有线程推理

    onnx_model = onnx.load(weights)
    DML = ["DmlExecutionProvider"]
    CPU = ["CPUExecutionProvider"]
    session = ort.InferenceSession(
        onnx_model.SerializeToString(), sess_options=options, providers=DML
    )
    input_name = session.get_inputs()[0].name
    input_shape = session.get_inputs()[0].shape
    output_names = [output.name for output in session.get_outputs()]

    print(input_name, "input_name")
    print(output_names, "output_names")

    # onnx dml end ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————


def getInter(box1, box2):
    """
    获取两个矩形框的交集区域
    :param box1: 矩形框1，格式为[x1, y1, x2, y2]
    :param box2: 矩形框2，格式为[x1, y1, x2, y2]
    :return: 交集区域的面积
    """
    x1 = max(box1[0], box2[0])
    y1 = max(box1[1], box2[1])
    x2 = min(box1[2], box2[2])
    y2 = min(box1[3], box2[3])
    if x1 >= x2 or y1 >= y2:
        return 0.0
    else:
        return (x2 - x1) * (y2 - y1)


def getIou(box1, box2, interArea=None):
    """
    计算两个矩形框的 IoU，即交并比
    :param box1: 矩形框1，格式为[x1, y1, x2, y2, cls, conf]
    :param box2: 矩形框2，格式为[x1, y1, x2, y2, cls, conf]
    :param interArea: 交集区域的面积，可提前计算以减少重复计算
    :return: 交并比
    """
    # 计算并集面积
    box1_area = (box1[2] - box1[0]) * (box1[3] - box1[1])
    box2_area = (box2[2] - box2[0]) * (box2[3] - box2[1])
    unionArea = box1_area + box2_area - interArea
    # 计算交并比
    iou = interArea / unionArea
    return iou


def onnx_nms(pred):
    # 置信度抑制，小于置信度阈值则删除
    conf = pred[..., 4] > conf_thres
    box = pred[conf == True]
    # 类别获取
    cls_conf = box[..., 5:]
    cls = []
    for i in range(len(cls_conf)):
        cls.append(int(np.argmax(cls_conf[i])))
    # 获取类别
    total_cls = list(set(cls))  # 删除重复项，获取出现的类别标签列表,example=[0, 17]
    output_box = []  # 最终输出的预测框
    # 不同分类候选框置信度
    for i in range(len(total_cls)):
        clss = total_cls[i]  # 当前类别标签
        # 从所有候选框中取出当前类别对应的所有候选框
        cls_box = []
        for j in range(len(cls)):
            if cls[j] == clss:
                box[j][5] = clss
                cls_box.append(box[j][:6])
        cls_box = np.array(cls_box)
        box_conf = cls_box[..., 4]  # 取出候选框置信度
        box_conf_sort = np.argsort(box_conf)  # 获取排序后索引
        max_conf_box = cls_box[box_conf_sort[len(box_conf) - 1]]
        output_box.append(max_conf_box)  # 将置信度最高的候选框输出为第一个预测框
        cls_box = np.delete(cls_box, 0, 0)  # 删除置信度最高的候选框
        while len(cls_box) > 0:
            # 将输出预测框列表最后一个作为当前最大置信度候选框
            max_conf_box = output_box[len(output_box) - 1]
            del_index = []
            for j in range(len(cls_box)):
                current_box = cls_box[j]  # 当前预测框
                interArea = getInter(max_conf_box, current_box)  # 当前预测框与最大预测框交集
                iou = getIou(max_conf_box, current_box, interArea)  # 计算交并比
                if iou > iou_thres:
                    del_index.append(j)  # 根据交并比确定需要移出的索引
            cls_box = np.delete(cls_box, del_index, 0)  # 删除此轮需要移出的候选框
            if len(cls_box) > 0:
                output_box.append(cls_box[0])
                cls_box = np.delete(cls_box, 0, 0)

    return output_box if len(output_box) > 0 else None


def SAVE_THE_CONFIGURATION():
    for key, value in dictMap.keysMap.items():
        print(f"参数名：{key} 参数值：{value} 写入成功")
        write_key_value(key, value)
    # 先将值写入到文件对象 最终将文件对象写入配置文件中
    _write_file()  # 写入文件


def READ_THE_CONFIGURATION_FILE():
    global instantiation_pid_x, instantiation_pid_y
    fileMap = read_file()
    print(fileMap, "fileMap")
    # 遍历所有键值对，输出它们的键和值都是字符串类型
    for key, value in fileMap:
        format_value = convert(value)
        # print(f"转换的key：{key} | 转换后的值：{format_value} | 值类型：{type(format_value)}")
        globals()[key] = format_value
    # pid 初始化
    instantiation_pid_x = PID_GPT_PLUS(0, pid_x_p, pid_x_i, pid_x_d)
    instantiation_pid_y = PID_GPT_PLUS(0, pid_y_p, pid_y_i, pid_y_d)


def on_click(x, y, button, pressed):
    global lock_mode
    if not pressed:
        if button == mouse.Button.x1:
            lock_mode = True
            winsound.PlaySound("otherFeatures/music/ok.wav", flags=1)
            print("锁定功能：开")
        if button == mouse.Button.x2:
            winsound.PlaySound("otherFeatures/music/close.wav", flags=1)
            lock_mode = False
            print("锁定功能：关")


def getTime(t):
    return "{:.2f}".format((time.time() - t) * 1000)


def draw_box(img, _box):
    if _box is None:
        return img
    x_center, y_center, w, h, conf, label = _box
    conf_rounded_str = "{:.2f}".format(conf)  # 使用 format 方法将浮点数转换为字符串
    x1, y1 = int(x_center - w / 2), int(y_center - h / 2)
    x2, y2 = int(x_center + w / 2), int(y_center + h / 2)
    cv2.rectangle(img, (x1, y1), (x2, y2), color=(0, 255, 0), thickness=4)
    cv2.putText(
        img,
        f"{label}_{conf_rounded_str}%",
        (x1, y1),
        cv2.FONT_HERSHEY_COMPLEX,
        0.8,
        (0, 0, 255),
        2,
    )  # box[0] 是类别名

    return img


def minTag(pred):
    min_move_x = float("inf")
    min_target = None
    if pred is None:
        return None
    for box in pred:
        lx, lt, w, h, conf, cls = box.tolist()  # 将 numpy 数组转换为 Python 列表，并解构出各个变量

        if cls == 1:  # 过滤队友
            continue

        x1 = lx - w / 2
        y1 = lt - h / 2
        x2 = lx + w / 2
        y2 = lt + h / 2

        x_center, y_center = (x1 + x2) / 2, (y1 + y2) / 2  # 计算目标框的中心坐标

        # _move_x = x_center - grab_width / 2
        # _move_y = y_center - grab_height / 2

        _move_x = x_center - imgsz / 2
        _move_y = y_center - imgsz / 2

        if _move_x < min_move_x:
            min_move_x = _move_x
            min_target = ((_move_x, _move_y), (x1, x2, y1, y2, w, h, conf, cls))

    if min_target is not None:
        return min_target
    else:
        return None


def imageProcessing(img0):
    # image = img0.astype(np.float16) / 255.0  # 归一化到0到1的范围

    # image = cv2.resize(image, (imgsz, imgsz), interpolation=cv2.INTER_LINEAR)  # 调整图像尺寸为模型期望的尺寸
    image = img0.astype(np.float32) / 255.0  # 归一化到0到1的范围

    image = image[:, :, ::-1].transpose((2, 0, 1))  # HWC转CHW

    image = np.expand_dims(image, axis=0)  # 添加批处理维度

    return image


def apex_run():
    while True:
        t1 = time.time()

        # img0 = grab_gpt(window_title=grab_window_title, grab_rect=rect)
        img0 = dx_cap()

        if img0 is None:
            continue

        gr_time = round(((time.time() - t1) * 1000), 2)

        t2 = time.time()

        # 前处理
        image = imageProcessing(img0)  # 前处理

        # 推理
        outputs = session.run(output_names, {input_name: image})[0]

        # nms
        pred = onnx_nms(outputs)

        # 得到x轴距离最近的目标
        min_tag = minTag(pred)

        pr_time = round(((time.time() - t2) * 1000), 2)

        print(
            f"截图时间：{gr_time} || 推理时间：{pr_time:.2f} || 总耗时：{gr_time + pr_time:.2f} || 帧率：{1000 / (gr_time + pr_time):.2f}"
        )

        if min_tag is not None:
            _move_x, _move_y = min_tag[0]  # 拿到相对移动坐标xy

            x1, x2, y1, y2, w, h, conf, cls = min_tag[1]  # auth
            # 只按下了左键没按下右键
            _offset = (
                0
                if (
                    win32api.GetAsyncKeyState(win32con.VK_LBUTTON) < 0
                    and win32api.GetAsyncKeyState(win32con.VK_RBUTTON) > 0
                )
                else int(h * offset)
            )
            _range = (
                1.5 if win32api.GetAsyncKeyState(win32con.VK_LBUTTON) < 0 else 1
            )  # 0.5 - 1  值越小范围越小 越大距离越远越锁
            _range_y = 1
            # 在规定范围内
            in_range = abs(_move_x) <= (w * _range) and abs(_move_y) <= (h * _range_y)

            if aim_mod == 0:
                press = win32api.GetAsyncKeyState(win32con.VK_LBUTTON) < 0
            elif aim_mod == 1:
                press = win32api.GetAsyncKeyState(win32con.VK_RBUTTON) < 0
            elif aim_mod == 2:
                press = (
                    win32api.GetAsyncKeyState(win32con.VK_LBUTTON) < 0
                    or win32api.GetAsyncKeyState(win32con.VK_RBUTTON) < 0
                )
            else:
                return print("瞄准模式异常")

            if lock_mode and press and in_range:
                _pid_x = int(instantiation_pid_x.getMove(_move_x, 6))
                _pid_y = int(instantiation_pid_y.getMove((_move_y - _offset)))
                Logitech.mouse.move(_pid_x, _pid_y)


def create_ui():
    pass


def INCREASE_PROCESS_PRIORITY():
    # 获取当前进程的 ID
    pid = win32api.GetCurrentProcessId()
    handle = win32api.OpenProcess(win32con.PROCESS_ALL_ACCESS, False, pid)
    win32process.SetPriorityClass(handle, win32process.REALTIME_PRIORITY_CLASS)
    win32api.CloseHandle(handle)
    print(f"当前进程PID:{pid} 成功已设置当前进程最高优先级 提高响应速度")


def main():
    READ_THE_CONFIGURATION_FILE()  # 读取配置赋值给全局变量
    INCREASE_PROCESS_PRIORITY()  # 提高优先级
    init_model()
    mouse.Listener(on_click=on_click).start()  # radio
    Process(target=shake_gan_main).start()  # 抖枪宏 通用宏
    Process(target=automatic_armor_change_func).start()  # 自动换甲
    # threading.Thread(target=create_ui).start()  # create ui
    threading.Thread(target=apex_run).start()  # lock


if __name__ == "__main__":
    # freeze_support()  # 解决多线程 pyinstall打包bug  #打包时取消注释
    main()  # 主程序
