import sys
from PyQt5.QtWidgets import QApplication, QWidget, QPushButton, QTextEdit,QGraphicsDropShadowEffect,QLabel,QMessageBox,QDialog,QComboBox,QVBoxLayout
from PyQt5.QtCore import Qt, QThread, pyqtSlot, pyqtSignal,QTimer
from PyQt5.QtGui import QMouseEvent,QFont, QColor,QPainter, QBrush, QPixmap
from PyQt5 import QtCore

import ctypes
from ctypes import windll
import winsound

from utils import *

windll.winmm.timeBeginPeriod(1)
User32 = ctypes.windll.user32
GetKeyState = User32.GetKeyState
GetKeyState.argtypes = (ctypes.c_int,)

_, _, _, _, _, f, g, h, i, j, k, l = read_args_from_file()

offset = f # 6
fov = g # 7
sens = h # 8

speed = i # 9
ki = j # 10
Noliner = k # 11
range = l


class Dialog(QDialog):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("热键设置")
        self.setFixedSize(300, 200)
        self.initUI()


    def initUI(self):
        layout = QVBoxLayout()

        # 创建第一个下拉单选框
        self.combo_box1 = QComboBox()
        self.combo_box1.addItems(['鼠标左键',
                                   '鼠标右键',
                                     '鼠标前侧键',
                                       '鼠标后侧键',
                                         '键盘Shift键',
                                  '键盘大写锁定键',
                                    '键盘Tab键',
                                      '键盘Ctrl键',
                                        '键盘E键',
                                          '键盘Q键',
                                  '键盘Z键',
                                    '键盘X键',
                                      '键盘C键',
                                        '键盘F键',
                                          '键盘Alt键']
                                          )
        layout.addWidget(QLabel("热键1"))
        layout.addWidget(self.combo_box1)

        # 创建第二个下拉单选框
        self.combo_box2 = QComboBox()
        self.combo_box2.addItems(['鼠标左键',
                                   '鼠标右键',
                                     '鼠标前侧键',
                                       '鼠标后侧键',
                                         '键盘Shift键',
                                  '键盘大写锁定键',
                                    '键盘Tab键',
                                      '键盘Ctrl键',
                                        '键盘E键',
                                          '键盘Q键',
                                  '键盘Z键',
                                    '键盘X键',
                                      '键盘C键',
                                        '键盘F键',
                                          '键盘Alt键']
                                          )
        layout.addWidget(QLabel("热键2"))
        layout.addWidget(self.combo_box2)

        # 创建第三个下拉单选框
        self.combo_box3 = QComboBox()
        self.combo_box3.addItems(['鼠标左键',
                                   '鼠标右键',
                                     '鼠标前侧键',
                                       '鼠标后侧键',
                                         '键盘Shift键',
                                  '键盘大写锁定键',
                                    '键盘Tab键',
                                      '键盘Ctrl键',
                                        '键盘E键',
                                          '键盘Q键',
                                  '键盘Z键',
                                    '键盘X键',
                                      '键盘C键',
                                        '键盘F键',
                                          '键盘Alt键',
                                  'F1',
                                  'F2',
                                  'F3',
                                  'F4',
                                  'F5',
                                  'F6',
                                  'F7',
                                  'F8',
                                  'F9',
                                  'F10',
                                  'F11',
                                  'F12']
                                  )
        
        
        layout.addWidget(QLabel("启停键"))
        layout.addWidget(self.combo_box3)

        ak1,ak2,pk = read_keysArgs()

        self.combo_box1.setCurrentText(ak1)
        self.combo_box2.setCurrentText(ak2)
        self.combo_box3.setCurrentText(pk)

        # 创建按钮
        self.button = QPushButton("确认")
        self.button.clicked.connect(self.printSelection)
        layout.addWidget(self.button)
        self.setLayout(layout)

    def printSelection(self):
        selected_option1 = self.combo_box1.currentText()
        selected_option2 = self.combo_box2.currentText()
        selected_option3 = self.combo_box3.currentText()
        write_keysArgs(selected_option1, selected_option2, selected_option3)

class quitButton(QPushButton):
    clicked_custom = pyqtSignal()  # 自定义信号

    def __init__(self, parent=None):
        super().__init__(parent)
        self.setFixedSize(20, 20)
        self.pressed_color = QColor(154, 50, 50)
        self.hover_color = QColor(233, 80, 80)

    def paintEvent(self, event):
        painter = QPainter(self)
        painter.setRenderHint(QPainter.Antialiasing)  # 抗锯齿
        painter.setPen(Qt.NoPen)  # 无边框
        if self.underMouse() and self.isDown():
            painter.setBrush(self.pressed_color)
        elif self.underMouse():
            painter.setBrush(self.hover_color)
        else:
            painter.setBrush(QColor(225, 115, 115))
        painter.drawEllipse(0, 0, self.width(), self.height())

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.clicked_custom.emit()
        super().mousePressEvent(event)

class minButton(QPushButton):
    clicked_custom = pyqtSignal()  # 自定义信号

    def __init__(self, parent=None):
        super().__init__(parent)
        self.setFixedSize(20, 20)
        self.pressed_color = QColor(193, 129, 0)
        self.hover_color = QColor(255, 189, 72)

    def paintEvent(self, event):
        painter = QPainter(self)
        painter.setRenderHint(QPainter.Antialiasing)
        painter.setPen(Qt.NoPen)  # 无边框
        if self.underMouse() and self.isDown():
            painter.setBrush(self.pressed_color)
        elif self.underMouse():
            painter.setBrush(self.hover_color)
        else:
            painter.setBrush(QColor(239, 207, 117))
        painter.drawEllipse(0, 0, self.width(), self.height())

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.clicked_custom.emit()
        super().mousePressEvent(event)

class DraggableLabel(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None
        
    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x()
            y = self.y() + delta.y()
            y = max(60, min(y, 240))

            global offset
            offset = int((y - 60) / (240 - 60) * 100)
            self.move(x, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None

class DraggableLabel_sens(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x() + delta.x()
            y = self.y()
            a = int(max(35, min(x, 220)))
            sens_ = (a - 35) / (220 - 35) * (20 - 0.2) + 0.2

            global sens
            sens = round(sens_, 1)
            self.move(a, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None

class DraggableLabel_fov(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x() + delta.x()
            y = self.y()
            a = max(35, min(x, 220))

            global fov
            fov = int((a - 35) * ((110 - 70) / (220 - 35)) + 70)
            self.move(a, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None

class DraggableLabel_range(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x() + delta.x()
            y = self.y()
            a = max(385, min(x, 575))

            global range
            range = int((a - 385) * ((416 - 30) / (575 - 385)) + 30)
            self.move(a, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None


class DraggableLabel_speed(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x() + delta.x()
            y = self.y()
            a = max(385, min(x, 575))

            global speed
            speed = round(float((a - 385) / (575 - 385) * (200 - 10) + 10) / 100, 2)
            self.move(a, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None




class DraggableLabel_ki(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x() + delta.x()
            y = self.y()
            a = max(385, min(x, 575))

            global ki
            ki = round(float((a - 385) / (575 - 385) * (50 - 10) + 10) / 100, 2)
            self.move(a, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None


class DraggableLabel_Noliner(QLabel):
    def __init__(self, parent):
        super().__init__(parent)
        self.setMouseTracking(True)
        self.drag_start_position = None

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = event.pos()

    def mouseMoveEvent(self, event):
        if event.buttons() == Qt.LeftButton and self.drag_start_position is not None:
            delta = event.pos() - self.drag_start_position
            x = self.x() + delta.x()
            y = self.y()
            a = max(385, min(x, 575))

            global Noliner
            Noliner_ = float((a - 385) / (575 - 385) * (20 - 10) + 10) / 10
            Noliner = round(Noliner_, 2)
            self.move(a, y)

    def mouseReleaseEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.drag_start_position = None

class MyWindow(QWidget):
    def __init__(self):
        super().__init__()


        global isruning

        isruning = False

        awareness = ctypes.c_void_p(0)
        ctypes.windll.shcore.SetProcessDpiAwareness(2)
        scale_factor = ctypes.windll.shcore.GetScaleFactorForDevice(0)
        a = True if scale_factor == 100 else False

        if a is True:

                if go is True:
                    msg_box = QMessageBox()
                    msg_box.setWindowTitle("登陆成功")
                    msg_box.setText(announcement)
                    msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
                    msg_box.exec_()

                    msg_box = QMessageBox()
                    msg_box.setWindowTitle("卡密有效期")
                    msg_box.setText(remindtime)
                    msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
                    msg_box.exec_()

                    pass


                elif go is False:
                    msg_box = QMessageBox()
                    msg_box.setWindowTitle("登陆失败")
                    msg_box.setText(announcement)
                    msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
                    msg_box.exec_()
                    CloseSoftApp()    

            else:
                msg_box = QMessageBox()
                msg_box.setWindowTitle("登陆失败")
                msg_box.setText("未找到密钥文件")
                msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
                msg_box.exec_()
                CloseSoftApp()    



        else:
            msg_box = QMessageBox()
            msg_box.setWindowTitle("提示")
            msg_box.setText("请将显示设置中的缩放与布局比例设置为100%   ")
            msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
            msg_box.exec_()
            CloseSoftApp()

        self.draggable = False
        self.mousePos = None
        self.thread = None  # 添加线程实例变量
        self.init_ui()

        self.setFixedSize(895, 500)
        self.setStyleSheet("background-color: rgb(245, 245, 245);")
        self.setWindowFlags(self.windowFlags() | Qt.FramelessWindowHint)
        self.setAttribute(Qt.WA_TranslucentBackground)

    def init_ui(self):

        
        a, b, c, d, e, _, _, _, _, _, _, _ = read_args_from_file()

        self.grab_selected = a # 1
        self.infer_selected = b # 2
        self.weights_selected = c # 3
        self.MoveMode_selected = d # 4
        self.runMode_selected = e # 5

        
        global offset,fov,sens,speed,range

        self.offset = offset # 6
        self.fov = fov # 7
        self.sens = sens # 8

        self.speed = speed # 9
        self.ki = ki # 9
        self.Noliner = Noliner # 9
        self.range = range

        # 卡密信息卡片
        self.BaseSettingBoard = QWidget(self)
        self.BaseSettingBoard.setGeometry(630, 15, 250, 40)
        self.BaseSettingBoard.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 15px;
        """)
        shadow = QGraphicsDropShadowEffect(self.BaseSettingBoard)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.BaseSettingBoard.setGraphicsEffect(shadow)

        # 启停按钮卡片
        self.BaseSettingBoard = QWidget(self)
        self.BaseSettingBoard.setGeometry(630, 435, 250, 50)
        self.BaseSettingBoard.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 15px;
        """)
        shadow = QGraphicsDropShadowEffect(self.BaseSettingBoard)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.BaseSettingBoard.setGraphicsEffect(shadow)

        # 创建启动按钮
        self.start_button = QPushButton("Im Hacker!", self)
        self.start_button.setGeometry(755, 442, 105, 35)
        self.start_button.clicked.connect(self.start_test_fn)
        self.start_button.setStyleSheet(
            "background-color: rgb(192, 221, 155); color: rgb(112, 137, 72); font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;")
        self.start_button.enterEvent = lambda event: self.start_button_entered(event)
        self.start_button.leaveEvent = lambda event: self.start_button_left(event)
        self.start_button.pressed.connect(self.start_button_pressed)
        self.start_button.released.connect(self.start_button_released)
        # 启动按钮-投影
        shadow = QGraphicsDropShadowEffect(self.start_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(161, 197, 140, 130))
        self.start_button.setGraphicsEffect(shadow)

        # 创建停止按钮
        self.stop_button = QPushButton("Sorry!", self)
        self.stop_button.setGeometry(650, 442, 85, 35)
        self.stop_button.clicked.connect(self.stop_test_fn)
        self.stop_button.setStyleSheet(
            "background-color: rgb(227, 187, 189); color: rgb(175, 108, 118); font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;")
        self.stop_button.enterEvent = lambda event: self.stop_button_entered(event)
        self.stop_button.leaveEvent = lambda event: self.stop_button_left(event)
        self.stop_button.pressed.connect(self.stop_button_pressed)
        self.stop_button.released.connect(self.stop_button_released)
        # 停止按钮-投影
        shadow = QGraphicsDropShadowEffect(self.stop_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(188, 74, 74, 70))
        self.stop_button.setGraphicsEffect(shadow)






        # 创建退出按钮
        quit_button = quitButton(self)
        quit_button.setGeometry(850, 24, 15, 15)  # 设置按钮位置和大小
        quit_button.clicked_custom.connect(self.quit_button_handle_button_click)  # 对接自定义信号
        # 创建退出按钮-投影
        shadow = QGraphicsDropShadowEffect(quit_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(188, 74, 74, 170))
        quit_button.setGraphicsEffect(shadow)








        # 创建最小化按钮
        min_button = minButton(self)
        min_button.setGeometry(820, 24, 15, 15)  # 设置按钮位置和大小
        min_button.clicked_custom.connect(self.min_button_handle_button_click)  # 对接自定义信号
        # 创建最小化按钮-投影
        shadow = QGraphicsDropShadowEffect(min_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(188, 174, 74, 170))
        min_button.setGraphicsEffect(shadow)

        # versionLabel文本
        versionLabel = QLabel(self)
        versionLabel.setText("kjCHEAT v2.0")
        versionLabel.setAlignment(Qt.AlignCenter)
        versionLabel.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);"
            )
        versionLabel.setFont(QFont("微软雅黑", 11))
        versionLabel.setGeometry(650, 20, 100, 30)
        versionLabel.setWindowFlags(Qt.WindowStaysOnTopHint)


        # SPOT热调节卡片
        self.Aimspot = QWidget(self)
        self.Aimspot.setGeometry(365, 15, 250, 470)
        self.Aimspot.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 15px;
        """)
        shadow = QGraphicsDropShadowEffect(self.Aimspot)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.Aimspot.setGraphicsEffect(shadow)
        # SPOT卡片指示文本
        label_aim = QLabel(self)
        label_aim.setText("ARG")
        label_aim.setAlignment(Qt.AlignCenter)
        label_aim.setStyleSheet("background-color: rgba(255, 255, 255, 0);color: rgb(131, 131, 131);")
        label_aim.setFont(QFont("微软雅黑", 18))
        label_aim.setGeometry(360, 25, 100, 30)
        label_aim.setWindowFlags(Qt.WindowStaysOnTopHint)


        # 打印文本重定向显示组件
        self.textedit = QTextEdit(self)
        self.textedit.setGeometry(630, 70, 250, 350)
        self.textedit.setStyleSheet("""
            background-color: rgb(248, 248, 248);
            border-radius: 15px;
            border: 8px solid rgb(255, 255, 255);
            color: rgb(200, 200, 200);
        """)
        font = self.textedit.font()
        font.setFamily("微软雅黑")
        font.setPointSize(9)
        self.textedit.setFont(font)
        self.textedit.setReadOnly(True)
        self.textedit.setContextMenuPolicy(Qt.NoContextMenu)
        self.textedit.setVerticalScrollBarPolicy(Qt.ScrollBarAlwaysOff)
        self.textedit.setHorizontalScrollBarPolicy(Qt.ScrollBarAlwaysOff)
        shadow = QGraphicsDropShadowEffect(self.textedit)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.textedit.setGraphicsEffect(shadow)

        new_text = "欢迎使用kjC 目标检测神经网络辅助" + " \n" + "kjCheat v2" + " \n" + " \n" + "若需要个人定制请联系您的卖家, 老实人老佛爷从不骗人 : D"
        self.textedit.setText(new_text)


        # BASE卡片
        self.BaseSettingBoard = QWidget(self)
        self.BaseSettingBoard.setGeometry(15, 15, 335, 470)
        self.BaseSettingBoard.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 15px;
        """)
        shadow = QGraphicsDropShadowEffect(self.BaseSettingBoard)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.BaseSettingBoard.setGraphicsEffect(shadow)
        # BASE卡片指示文本
        label_base = QLabel(self)
        label_base.setText("BASE")
        label_base.setAlignment(Qt.AlignCenter)
        label_base.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(131, 131, 131);"
            )
        label_base.setFont(QFont("微软雅黑", 20))
        label_base.setGeometry(12, 25, 100, 30)
        label_base.setWindowFlags(Qt.WindowStaysOnTopHint)

        # 创建停止按钮
        self.conffer_button = QPushButton("Confirm", self)
        self.conffer_button.setGeometry(235, 23, 100, 35)
        self.conffer_button.clicked.connect(self.conffer_button_fn)
        self.conffer_button.setStyleSheet(
            "background-color: rgb(201, 227, 252); color: rgb(89, 156, 211);"
            "font-family: '微软雅黑'; font-size: 12pt; border-radius: 10px;"
            )
        self.conffer_button.enterEvent = lambda event: self.conffer_button_entered(event)
        self.conffer_button.leaveEvent = lambda event: self.conffer_button_left(event)
        self.conffer_button.pressed.connect(self.conffer_button_pressed)
        self.conffer_button.released.connect(self.conffer_button_released)
        shadow = QGraphicsDropShadowEffect(self.stop_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(130, 130, 188, 70))
        self.conffer_button.setGraphicsEffect(shadow)

        # base面板分割线
        self.baseMidLine = QWidget(self)
        self.baseMidLine.setGeometry(40, 340, 275, 2)
        self.baseMidLine.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 1px;
        """)

        # Grab组件
        self.grabtt = QLabel(self)
        self.grabtt.setText("Grab Mode")
        self.grabtt.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.grabtt.setFont(QFont("微软雅黑", 10))
        self.grabtt.setGeometry(25, 60, 80, 25)
        self.grabtt.setAlignment(Qt.AlignCenter)
        self.grabtt.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.Moveroptions = QWidget(self)
        self.Moveroptions.setGeometry(30, 85, 305, 35)
        self.Moveroptions.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 15px;
        """)

        # Grab滑块
        self.grab_rect_label = QWidget(self)
        self.grab_rect_label.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 12px;
        """)
        shadow = QGraphicsDropShadowEffect(self.grab_rect_label)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.grab_rect_label.setGraphicsEffect(shadow)
        self.grabttLine = QWidget(self)
        self.grabttLine.setGeometry(138, 92, 4, 20)
        self.grabttLine.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)
        self.grabttLine2 = QWidget(self)
        self.grabttLine2.setGeometry(240, 92, 4, 20)
        self.grabttLine2.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)
        # DXCAM 字样
        self.Moveroptions1 = QLabel(self)
        self.Moveroptions1.setText("Dxcam")
        self.Moveroptions1.setFont(QFont("微软雅黑", 13))
        self.Moveroptions1.setGeometry(40, 90, 80, 25)
        self.Moveroptions1.setAlignment(Qt.AlignCenter)
        self.Moveroptions1.setWindowFlags(Qt.WindowStaysOnTopHint)

        # MSS 字样
        self.Moveroptions2 = QLabel(self)
        self.Moveroptions2.setText("Mss")
        self.Moveroptions2.setFont(QFont("微软雅黑", 13))
        self.Moveroptions2.setGeometry(150, 90, 80, 25)
        self.Moveroptions2.setAlignment(Qt.AlignCenter)
        self.Moveroptions2.setWindowFlags(Qt.WindowStaysOnTopHint)

        # WIN32 字样
        self.Moveroptions3 = QLabel(self)
        self.Moveroptions3.setText("Win32")
        self.Moveroptions3.setFont(QFont("微软雅黑", 13))
        self.Moveroptions3.setGeometry(250, 90, 85, 25)
        self.Moveroptions3.setAlignment(Qt.AlignCenter)
        self.Moveroptions3.setWindowFlags(Qt.WindowStaysOnTopHint)

        if self.grab_selected == 0:
            self.grab_rect_label.setGeometry(40, 90, 80, 25)
            self.Moveroptions1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Moveroptions2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            
        elif self.grab_selected == 1:
            self.grab_rect_label.setGeometry(150, 90, 80, 25)
            self.Moveroptions1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Moveroptions3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            
        elif self.grab_selected == 2:
            self.grab_rect_label.setGeometry(250, 90, 80, 25)
            self.Moveroptions1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")

        # 将文本信息标签与槽函数关联
        self.Moveroptions1.mousePressEvent = lambda event: self.move_rect_label(self.Moveroptions1)
        self.Moveroptions2.mousePressEvent = lambda event: self.move_rect_label(self.Moveroptions2)
        self.Moveroptions3.mousePressEvent = lambda event: self.move_rect_label(self.Moveroptions3)
        # 创建动画
        self.animation = QtCore.QPropertyAnimation(self.grab_rect_label, b"pos")
        self.animation.setDuration(100)

        # Infer组件
        self.Infertt = QLabel(self)
        self.Infertt.setText("Infer Mode")
        self.Infertt.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.Infertt.setFont(QFont("微软雅黑", 10))
        self.Infertt.setGeometry(25, 130, 80, 25)
        self.Infertt.setAlignment(Qt.AlignCenter)
        self.Infertt.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.Inferoptions = QWidget(self)
        self.Inferoptions.setGeometry(30, 155, 305, 35)
        self.Inferoptions.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 15px;
        """)

        self.Inferoptions_rect_label = QWidget(self)
        self.Inferoptions_rect_label.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 12px;
        """)
        shadow = QGraphicsDropShadowEffect(self.Inferoptions_rect_label)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.Inferoptions_rect_label.setGraphicsEffect(shadow)

        self.InferttLine = QWidget(self)
        self.InferttLine.setGeometry(138, 162, 4, 20)
        self.InferttLine.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)
        self.InferttLine2 = QWidget(self)
        self.InferttLine2.setGeometry(240, 162, 4, 20)
        self.InferttLine2.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)

        self.Inferoption1 = QLabel(self)
        self.Inferoption1.setText("GPU")
        self.Inferoption1.setFont(QFont("微软雅黑", 13))
        self.Inferoption1.setGeometry(40, 160, 78, 25)
        self.Inferoption1.setAlignment(Qt.AlignCenter)
        self.Inferoption1.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.Inferoption2 = QLabel(self)
        self.Inferoption2.setText("CPU")
        self.Inferoption2.setFont(QFont("微软雅黑", 13))
        self.Inferoption2.setGeometry(150, 160, 78, 25)
        self.Inferoption2.setAlignment(Qt.AlignCenter)
        self.Inferoption2.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.Inferoption3 = QLabel(self)
        self.Inferoption3.setText("MIX")
        self.Inferoption3.setFont(QFont("微软雅黑", 13))
        self.Inferoption3.setGeometry(250, 160, 80, 25)
        self.Inferoption3.setAlignment(Qt.AlignCenter)
        self.Inferoption3.setWindowFlags(Qt.WindowStaysOnTopHint)

        if self.infer_selected == 0:
            self.Inferoptions_rect_label.setGeometry(40, 160, 80, 25)
            self.Inferoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Inferoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        elif self.infer_selected == 1:
            self.Inferoptions_rect_label.setGeometry(150, 160, 80, 25)
            self.Inferoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Inferoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        elif self.infer_selected == 2:
            self.Inferoptions_rect_label.setGeometry(250, 160, 80, 25)
            self.Inferoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")

        self.Inferoption1.mousePressEvent = lambda event: self.infer_rect_label(self.Inferoption1)
        self.Inferoption2.mousePressEvent = lambda event: self.infer_rect_label(self.Inferoption2)
        self.Inferoption3.mousePressEvent = lambda event: self.infer_rect_label(self.Inferoption3)

        self.animation1 = QtCore.QPropertyAnimation(self.Inferoptions_rect_label, b"pos")
        self.animation1.setDuration(100)

        # 权重组件
        self.weightstt = QLabel(self)
        self.weightstt.setText("Precision")
        self.weightstt.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.weightstt.setFont(QFont("微软雅黑", 10))
        self.weightstt.setGeometry(25, 200, 70, 25)
        self.weightstt.setAlignment(Qt.AlignCenter)
        self.weightstt.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.weightsoptions = QWidget(self)
        self.weightsoptions.setGeometry(30, 225, 305, 35)
        self.weightsoptions.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 15px;
        """)

        self.weightsoptions_rect_label = QWidget(self)
        self.weightsoptions_rect_label.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 12px;
        """)

        shadow = QGraphicsDropShadowEffect(self.weightsoptions_rect_label)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.weightsoptions_rect_label.setGraphicsEffect(shadow)

        self.weightsLine = QWidget(self)
        self.weightsLine.setGeometry(138, 233, 4, 20)
        self.weightsLine.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)
        self.weightsLine = QWidget(self)
        self.weightsLine.setGeometry(240, 233, 4, 20)
        self.weightsLine.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)

        self.weightsoption1 = QLabel(self)
        self.weightsoption1.setText("LARGE")

        self.weightsoption1.setFont(QFont("微软雅黑", 13))
        self.weightsoption1.setGeometry(40, 230, 80, 25)
        self.weightsoption1.setAlignment(Qt.AlignCenter)
        self.weightsoption1.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.weightsoption2 = QLabel(self)
        self.weightsoption2.setText("MID")

        self.weightsoption2.setFont(QFont("微软雅黑", 13))
        self.weightsoption2.setGeometry(150, 230, 78, 25)
        self.weightsoption2.setAlignment(Qt.AlignCenter)
        self.weightsoption2.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.weightsoption3 = QLabel(self)
        self.weightsoption3.setText("SMALL")
        self.weightsoption3.setFont(QFont("微软雅黑", 13))
        self.weightsoption3.setGeometry(250, 230, 83, 25)
        self.weightsoption3.setAlignment(Qt.AlignCenter)
        self.weightsoption3.setWindowFlags(Qt.WindowStaysOnTopHint)

        if self.weights_selected == 0:
            self.weightsoptions_rect_label.setGeometry(40, 230, 80, 25)
            self.weightsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.weightsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            
        elif self.weights_selected == 1:
            self.weightsoptions_rect_label.setGeometry(150, 230, 80, 25)
            self.weightsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.weightsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            
        elif self.weights_selected == 2:
            self.weightsoptions_rect_label.setGeometry(250, 230, 80, 25)
            self.weightsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")

        self.weightsoption1.mousePressEvent = lambda event: self.weights_rect_label(self.weightsoption1)
        self.weightsoption2.mousePressEvent = lambda event: self.weights_rect_label(self.weightsoption2)
        self.weightsoption3.mousePressEvent = lambda event: self.weights_rect_label(self.weightsoption3)

        self.animation2 = QtCore.QPropertyAnimation(self.weightsoptions_rect_label, b"pos")
        self.animation2.setDuration(100)


        # 移动模式组件
        self.weightstt = QLabel(self)
        self.weightstt.setText("Move Mode")
        self.weightstt.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.weightstt.setFont(QFont("微软雅黑", 10))
        self.weightstt.setGeometry(22, 270, 95, 25)
        self.weightstt.setAlignment(Qt.AlignCenter)
        self.weightstt.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.weightsoptions = QWidget(self)
        self.weightsoptions.setGeometry(30, 295, 305, 35)
        self.weightsoptions.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 15px;
        """)

        self.moveFsoptions_rect_label = QWidget(self)
        shadow = QGraphicsDropShadowEffect(self.moveFsoptions_rect_label)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.moveFsoptions_rect_label.setGraphicsEffect(shadow)


        self.moveFLine = QWidget(self)
        self.moveFLine.setGeometry(138, 302, 4, 20)
        self.moveFLine.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)
        self.moveFLine = QWidget(self)
        self.moveFLine.setGeometry(240, 302, 4, 20)
        self.moveFLine.setStyleSheet("""
            background-color: rgb(235, 235, 235);
            border-radius: 2px;
        """)

        self.moveFNsoption1 = QLabel(self)
        self.moveFNsoption1.setText("LOGIT")
        self.moveFNsoption1.setFont(QFont("微软雅黑", 13))
        self.moveFNsoption1.setGeometry(40, 300, 80, 25)
        self.moveFNsoption1.setAlignment(Qt.AlignCenter)
        self.moveFNsoption1.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.moveFNsoption2 = QLabel(self)
        self.moveFNsoption2.setText("Event")

        self.moveFNsoption2.setFont(QFont("微软雅黑", 13))
        self.moveFNsoption2.setGeometry(150, 300, 78, 25)
        self.moveFNsoption2.setAlignment(Qt.AlignCenter)
        self.moveFNsoption2.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.moveFNsoption3 = QLabel(self)
        self.moveFNsoption3.setText("Kmbox")
        self.moveFNsoption3.setFont(QFont("微软雅黑", 13))
        self.moveFNsoption3.setGeometry(250, 300, 83, 25)
        self.moveFNsoption3.setAlignment(Qt.AlignCenter)
        self.moveFNsoption3.setWindowFlags(Qt.WindowStaysOnTopHint)

        if self.MoveMode_selected == 0:
            self.moveFsoptions_rect_label.setGeometry(40, 300, 80, 25)
            self.moveFsoptions_rect_label.setStyleSheet("""
                background-color: rgb(255, 255, 255);
                border-radius: 12px;
            """)
            self.moveFNsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.moveFNsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        elif self.MoveMode_selected == 1:
            self.moveFsoptions_rect_label.setGeometry(150, 300, 80, 25)
            self.moveFsoptions_rect_label.setStyleSheet("""
                background-color: rgb(255, 255, 255);
                border-radius: 12px;
            """)
            self.moveFNsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.moveFNsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")


        elif self.MoveMode_selected == 2:
            self.moveFsoptions_rect_label.setGeometry(250, 300, 80, 25)
            self.moveFsoptions_rect_label.setStyleSheet("""
                background-color: rgb(255, 255, 255);
                border-radius: 12px;
            """)
            self.moveFNsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")

        self.moveFNsoption1.mousePressEvent = lambda event: self.moveFN_rect_label(self.moveFNsoption1)
        self.moveFNsoption2.mousePressEvent = lambda event: self.moveFN_rect_label(self.moveFNsoption2)
        self.moveFNsoption3.mousePressEvent = lambda event: self.moveFN_rect_label(self.moveFNsoption3)

        self.animation3 = QtCore.QPropertyAnimation(self.moveFsoptions_rect_label, b"pos")
        self.animation3.setDuration(100)


        self.runmode = QWidget(self)
        self.runmode.setGeometry(250, 385, 85, 80)
        self.runmode.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 15px;
        """)

        self.runmodeSelector = QWidget(self)
        self.runmodeSelector.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 12px;
        """)
        shadow = QGraphicsDropShadowEffect(self.runmodeSelector)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 20))
        self.runmodeSelector.setGraphicsEffect(shadow)

        self.runmodeSelectorLine = QWidget(self)
        self.runmodeSelectorLine.setGeometry(260, 423, 65, 4)
        self.runmodeSelectorLine.setStyleSheet("""
            background-color: rgb(225, 225, 225);
            border-radius: 2px;
        """)

        self.runmode1 = QLabel(self)
        self.runmode1.setText("NORMAL")
        self.runmode1.setFont(QFont("微软雅黑", 9))
        self.runmode1.setGeometry(255, 390, 76, 28)
        self.runmode1.setAlignment(Qt.AlignCenter)
        self.runmode1.setWindowFlags(Qt.WindowStaysOnTopHint)


        self.runmode2 = QLabel(self)
        self.runmode2.setText("DEBUG")
        self.runmode2.setFont(QFont("微软雅黑", 11))
        self.runmode2.setGeometry(255, 430, 70, 30)
        self.runmode2.setAlignment(Qt.AlignCenter)
        self.runmode2.setWindowFlags(Qt.WindowStaysOnTopHint)


        if self.runMode_selected == 0:
            self.runmodeSelector.setGeometry(255, 390, 75, 30)
            self.runmode1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(90, 90, 90);")
            self.runmode2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        elif self.runMode_selected == 1:
            self.runmodeSelector.setGeometry(255, 430, 75, 30)
            self.runmode1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.runmode2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(90, 90, 90);")

        self.runmode1.mousePressEvent = lambda event: self.runmode_rect_label(self.runmode1)
        self.runmode2.mousePressEvent = lambda event: self.runmode_rect_label(self.runmode2)

        self.animation4 = QtCore.QPropertyAnimation(self.runmodeSelector, b"pos")
        self.animation4.setDuration(100)
        
        # 人物图
        manlabel = QLabel(self)
        pixmap = QPixmap("man.png")
        manlabel.setPixmap(pixmap)
        manlabel.setStyleSheet('background-color: transparent;')
        manlabel.resize(pixmap.width(), pixmap.height())
        manlabel.move(455, 60)


        self.spot_button_line_ = QWidget(self)
        self.spot_button_line_.setGeometry(395, 65, 15, 200)
        self.spot_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 7px;
        """)
        self.conf_button_line_ = QWidget(self)
        self.conf_button_line_.setGeometry(35, 386, 200, 10)
        self.conf_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 5px;
        """)



        # sens组件
        self.sensText = QLabel(self)
        self.sensText.setText("Sens")
        self.sensText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.sensText.setFont(QFont("微软雅黑", 12))
        self.sensText.setGeometry(32, 403, 40, 28)
        self.sensText.setAlignment(Qt.AlignCenter)
        self.sensText.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.sens_button_line_ = QWidget(self)
        self.sens_button_line_.setGeometry(35, 440, 200, 10)
        self.sens_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 5px;
        """)
        self.sens_button = DraggableLabel_sens(self)
        self.sens_button.setGeometry(
            int(9.343 * sens + 33.1314)
            ,435
            , 20, 20)
    
        self.sens_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 10px;
        """)
        shadow = QGraphicsDropShadowEffect(self.sens_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.sens_button.setGraphicsEffect(shadow)









        # fov组件
        self.fovText = QLabel(self)
        self.fovText.setText("Fov")
        self.fovText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.fovText.setFont(QFont("微软雅黑", 12))
        self.fovText.setGeometry(28, 350, 40, 28)
        self.fovText.setAlignment(Qt.AlignCenter)
        self.fovText.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.fov_button = DraggableLabel_fov(self)
        self.fov_button.setGeometry(
            int(4.625*self.fov-288.75)
            ,380
            , 20, 20)
    
        self.fov_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 10px;
        """)
        shadow = QGraphicsDropShadowEffect(self.fov_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.fov_button.setGraphicsEffect(shadow)

        self.fov_label = QLabel(self)
        self.fov_label.setText(str(fov))
        self.fov_label.setGeometry(165, 350, 30, 30)
        self.fov_label.setStyleSheet("background-color: transparent;")
        self.fov_label.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(130, 130, 130);")
        self.fov_label.setFont(QFont("微软雅黑", 12))

        self.sens_label = QLabel(self)
        self.sens_label.setText(str(sens))
        self.sens_label.setGeometry(165, 402, 30, 30)
        self.sens_label.setStyleSheet("background-color: transparent;")
        self.sens_label.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(130, 130, 130);")
        self.sens_label.setFont(QFont("微软雅黑", 12))


        self.spot_button = DraggableLabel(self)
        self.spot_button.setGeometry(390,
                                    int((self.offset / 100) * (240 - 60) + 60)
                                    , 26, 26)
        self.spot_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 13px;
        """)
        shadow = QGraphicsDropShadowEffect(self.spot_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.spot_button.setGraphicsEffect(shadow)

        # speed组件
        self.speedText = QLabel(self)
        self.speedText.setText("Speed")
        self.speedText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.speedText.setFont(QFont("微软雅黑", 10))
        self.speedText.setGeometry(378, 335, 40, 28)
        self.speedText.setAlignment(Qt.AlignCenter)
        self.speedText.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.speed_button_line_ = QWidget(self)
        self.speed_button_line_.setGeometry(390, 365, 200, 10)
        self.speed_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 5px;
        """)
        self.speed_button = DraggableLabel_speed(self)    
        self.speed_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 10px;
        """)
        shadow = QGraphicsDropShadowEffect(self.speed_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.speed_button.setGraphicsEffect(shadow)


        self.speed_label = QLabel(self)
        self.speed_label.setText(str(sens))
        self.speed_label.setGeometry(570, 333, 30, 30)
        self.speed_label.setStyleSheet("background-color: transparent;")
        self.speed_label.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(130, 130, 130);")
        self.speed_label.setFont(QFont("微软雅黑", 12))


        # ki组件
        self.kiText = QLabel(self)
        self.kiText.setText("Ki")
        self.kiText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.kiText.setFont(QFont("微软雅黑", 10))
        self.kiText.setGeometry(368, 375, 40, 28)
        self.kiText.setAlignment(Qt.AlignCenter)
        self.kiText.setWindowFlags(Qt.WindowStaysOnTopHint)
        self.ki_button_line_ = QWidget(self)
        self.ki_button_line_.setGeometry(390, 405, 200, 10)
        self.ki_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 5px;
        """)
        self.ki_button = DraggableLabel_ki(self)
        self.ki_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 10px;
        """)
        shadow = QGraphicsDropShadowEffect(self.ki_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.ki_button.setGraphicsEffect(shadow)

        self.ki_label = QLabel(self)
        self.ki_label.setText(str(ki))
        self.ki_label.setGeometry(570, 373, 30, 30)
        self.ki_label.setStyleSheet("background-color: transparent;")
        self.ki_label.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(130, 130, 130);")
        self.ki_label.setFont(QFont("微软雅黑", 12))




        # Noliner组件
        self.NolinerText = QLabel(self)
        self.NolinerText.setText("Noliner")
        self.NolinerText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.NolinerText.setFont(QFont("微软雅黑", 10))
        self.NolinerText.setGeometry(365, 415, 80, 35)
        self.NolinerText.setAlignment(Qt.AlignCenter)
        self.NolinerText.setWindowFlags(Qt.WindowStaysOnTopHint)

        self.Noliner_button_line_ = QWidget(self)
        self.Noliner_button_line_.setGeometry(390, 445, 200, 10)
        self.Noliner_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 5px;
        """)
        self.Noliner_button = DraggableLabel_Noliner(self)
        self.Noliner_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 10px;
        """)
        shadow = QGraphicsDropShadowEffect(self.Noliner_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.Noliner_button.setGraphicsEffect(shadow)

        self.Noliner_label = QLabel(self)
        self.Noliner_label.setText(str(sens))
        self.Noliner_label.setGeometry(570, 413, 30, 30)
        self.Noliner_label.setStyleSheet("background-color: transparent;")
        self.Noliner_label.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(130, 130, 130);")
        self.Noliner_label.setFont(QFont("微软雅黑", 12))


        # range组件 
        self.rangeText = QLabel(self)
        self.rangeText.setText("Range")
        self.rangeText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.rangeText.setFont(QFont("微软雅黑", 10))
        self.rangeText.setGeometry(378, 295, 40, 28)
        self.rangeText.setAlignment(Qt.AlignCenter)
        self.rangeText.setWindowFlags(Qt.WindowStaysOnTopHint)


        self.range_button_line_ = QWidget(self)
        self.range_button_line_.setGeometry(390, 325, 200, 10)
        self.range_button_line_.setStyleSheet("""
            background-color: rgb(245, 245, 245);
            border-radius: 5px;
        """)


        self.range_button = DraggableLabel_range(self)
        self.range_button.setGeometry(
            int((self.range - 30) * ((575 - 385) / (416 - 30)) + 385)
            , 319, 20, 20)
        
        self.range_button.setStyleSheet("""
            background-color: rgb(255, 255, 255);
            border-radius: 10px;
        """)
        shadow = QGraphicsDropShadowEffect(self.range_button)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(35, 35, 35, 50))
        self.range_button.setGraphicsEffect(shadow)


        self.range_label = QLabel(self)
        self.range_label.setGeometry(570, 292, 30, 30)
        self.range_label.setStyleSheet("background-color: transparent;")
        self.range_label.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(130, 130, 130);")
        self.range_label.setFont(QFont("微软雅黑", 12))
        self.range_label.setText(str(self.range))





        self.speed_label.setText(str(self.speed))
        self.ki_label.setText(str(self.ki))
        self.Noliner_label.setText(str(self.Noliner))

        
        self.speed_button.setGeometry(
            int(100 * (self.speed - 0.1) + 385)
            ,358, 20, 20)
        

        self.ki_button.setGeometry(
            int(475 * (self.ki - 0.1) + 385)
            ,399, 20, 20)
        

        self.Noliner_button.setGeometry(
            int(190 * (self.Noliner - 1) + 385)
            ,440, 20, 20)




        # fov微调按钮
        self.del_fov = QLabel(self)
        self.del_fov.setGeometry(150, 361, 10, 10)
        self.del_fov.setStyleSheet("""
            background-color: rgb(245, 180, 180);
            border-radius: 5px;
        """)

        self.add_fov = QLabel(self)
        self.add_fov.setGeometry(200, 361, 20, 10)
        self.add_fov.setStyleSheet("""
            background-color: rgb(180, 245, 180);
            border-radius: 5px;
        """)

        self.del_fov.mousePressEvent = lambda event: self.decrease_fov()
        self.add_fov.mousePressEvent = lambda event: self.increase_fov()


        # sens微调按钮
        self.del_sens = QLabel(self)
        self.del_sens.setGeometry(150, 413, 10, 10)
        self.del_sens.setStyleSheet("""
            background-color: rgb(245, 180, 180);
            border-radius: 5px;
        """)

        self.add_sens = QLabel(self)
        self.add_sens.setGeometry(200, 413, 20, 10)
        self.add_sens.setStyleSheet("""
            background-color: rgb(180, 245, 180);
            border-radius: 5px;
        """)

        self.del_sens.mousePressEvent = lambda event: self.decrease_sens()
        self.add_sens.mousePressEvent = lambda event: self.increase_sens()

        # 准星
        up = 183/100 * self.offset + 17
        self.crosshairUp = QLabel(self)
        self.crosshairUp.setGeometry(523, int(up)+15, 10, 30)
        shadow = QGraphicsDropShadowEffect(self.crosshairUp)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(130, 130, 188, 205))
        self.crosshairUp.setGraphicsEffect(shadow)


        self.crosshairDown = QLabel(self)
        self.crosshairDown.setGeometry(523, int(up+65), 10, 30)
        shadow = QGraphicsDropShadowEffect(self.crosshairDown)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(130, 130, 188, 205))
        self.crosshairDown.setGraphicsEffect(shadow)

        self.crosshairLeft = QLabel(self)
        self.crosshairLeft.setGeometry(485, int(up+50), 30, 10)
        shadow = QGraphicsDropShadowEffect(self.crosshairLeft)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(130, 130, 188, 205))
        self.crosshairLeft.setGraphicsEffect(shadow)


        self.crosshairRight = QLabel(self)
        self.crosshairRight.setGeometry(540, int(up+50), 30, 10)
        shadow = QGraphicsDropShadowEffect(self.crosshairRight)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(130, 130, 188, 205))
        self.crosshairRight.setGraphicsEffect(shadow)

        self.crosshairUp.setStyleSheet("""
            background-color: rgb(225, 140, 140);
            border-radius: 5px;
        """)
        self.crosshairDown.setStyleSheet("""
            background-color: rgb(225, 140, 140);
            border-radius: 5px;
        """)
        self.crosshairLeft.setStyleSheet("""
            background-color: rgb(225, 140, 140);
            border-radius: 5px;
        """)
        self.crosshairRight.setStyleSheet("""
            background-color: rgb(225, 140, 140);
            border-radius: 5px;
        """)

        # 刷新标签类位置
        self.timer = QTimer()
        self.timer.timeout.connect(self.updateLabel)
        self.timer.start(1)


        self.runmodeText = QLabel(self)
        self.runmodeText.setText("RunMode")
        self.runmodeText.setStyleSheet(
            "background-color: rgba(255, 255, 255, 0);color: rgb(220, 220, 220);")
        self.runmodeText.setFont(QFont("微软雅黑", 10))
        self.runmodeText.setGeometry(245, 355, 70, 28)
        self.runmodeText.setAlignment(Qt.AlignCenter)
        self.runmodeText.setWindowFlags(Qt.WindowStaysOnTopHint)



        # 创建自定义热键按钮
        self.costomKeysBoard = QPushButton("HOTKEYS", self)
        self.costomKeysBoard.setGeometry(130, 23, 85, 35)
        
        self.costomKeysBoard.setStyleSheet(
            "background-color: rgb(250, 250, 250); color: rgb(180, 180, 180); font-family: '微软雅黑'; font-size: 10pt; border-radius: 10px;")
        self.costomKeysBoard.enterEvent = lambda event: self.costomKeysBoard_entered(event)
        self.costomKeysBoard.leaveEvent = lambda event: self.costomKeysBoard_left(event)
        self.costomKeysBoard.pressed.connect(self.costomKeysBoard_pressed)
        self.costomKeysBoard.released.connect(self.costomKeysBoard_released)
        self.costomKeysBoard.clicked.connect(self.costomKeysBoard_fn)
        
        shadow = QGraphicsDropShadowEffect(self.costomKeysBoard)
        shadow.setBlurRadius(25)
        shadow.setXOffset(5)
        shadow.setYOffset(5)
        shadow.setColor(QColor(188, 188, 188, 50))
        self.costomKeysBoard.setGraphicsEffect(shadow)


    def decrease_sens(self):
        global sens
        if sens > 0.2:
            sens -= 0.1


    def increase_sens(self):
        global sens
        if sens < 20:
            sens += 0.1


    def decrease_fov(self):
        global fov
        if fov > 70:
            fov -= 1

    def increase_fov(self):
        global fov
        if fov < 110:
            fov += 1


    def updateLabel(self):

        global fov , sens , offset , speed , ki , Noliner , range

        # 更新arg滑块显示文本
        self.speed_label.setText(str(speed))
        self.ki_label.setText(str(ki))
        self.Noliner_label.setText(str(Noliner))
        self.range_label.setText(str(int(range)))

        # 更新准星位置
        up = int(183/100 * offset + 17)
        self.crosshairUp.setGeometry(523, int(up)+15, 10, 30)
        self.crosshairDown.setGeometry(523, int(up+65), 10, 30)
        self.crosshairLeft.setGeometry(485, int(up+50), 30, 10)
        self.crosshairRight.setGeometry(540, int(up+50), 30, 10)

        # 更新游戏内设置的滑块组件当前参数
        self.sens_label.setText(str(round(sens, 1)))
        self.fov_label.setText(str(int(fov)))


    def runmode_rect_label(self, target_label):
        target_position = target_label.pos()
        self.animation4.setStartValue(self.runmodeSelector.pos())
        self.animation4.setEndValue(target_position)
        self.animation4.start()

        if int(target_position.y()) == 390:
            self.runMode_selected = 0
            self.runmode1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(90, 90, 90);")
            self.runmode2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        if int(target_position.y()) == 430:
            self.runMode_selected = 1
            self.runmode1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.runmode2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(90, 90, 90);")

    def moveFN_rect_label(self, target_label):
        target_position = target_label.pos()
        self.animation3.setStartValue(self.moveFsoptions_rect_label.pos())
        self.animation3.setEndValue(target_position)
        self.animation3.start()

        if int(target_position.x()) == 40:
            self.MoveMode_selected = 0
            
            self.moveFNsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.moveFNsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        if int(target_position.x()) == 150:
            self.MoveMode_selected = 1
            self.moveFNsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.moveFNsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            
        if int(target_position.x()) == 250:
            self.MoveMode_selected = 2

            self.moveFNsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.moveFNsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")

    def weights_rect_label(self, target_label):
        target_position = target_label.pos()
        self.animation2.setStartValue(self.weightsoptions_rect_label.pos())
        self.animation2.setEndValue(target_position)
        self.animation2.start()


        if int(target_position.x()) == 40:
            self.weights_selected = 0

            self.weightsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.weightsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        if int(target_position.x()) == 150:
            self.weights_selected = 1

            self.weightsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.weightsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            

        if int(target_position.x()) == 250:
            self.weights_selected = 2

            self.weightsoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.weightsoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")

    def infer_rect_label(self, target_label):
        target_position = target_label.pos()
        self.animation1.setStartValue(self.Inferoptions_rect_label.pos())
        self.animation1.setEndValue(target_position)
        self.animation1.start()
        if int(target_position.x()) == 40:
            self.infer_selected = 0

            self.Inferoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Inferoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            
        if int(target_position.x()) == 150:
            self.infer_selected = 1

            self.Inferoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Inferoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")

        if int(target_position.x()) == 250:
            self.infer_selected = 2

            self.Inferoption1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Inferoption3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")


    def move_rect_label(self, target_label):
        target_position = target_label.pos()
        self.animation.setStartValue(self.grab_rect_label.pos())
        self.animation.setEndValue(target_position)
        self.animation.start()

        if int(target_position.x()) == 40:
            self.grab_selected = 0

            self.Moveroptions1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Moveroptions2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);") 

        if int(target_position.x()) == 150:
            self.grab_selected = 1

            self.Moveroptions1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);")
            self.Moveroptions3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);") 
        if int(target_position.x()) == 250:
            self.grab_selected = 2

            self.Moveroptions1.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions2.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(185, 185, 185);")
            self.Moveroptions3.setStyleSheet(
                "background-color: rgba(255, 255, 255, 0);color: rgb(100, 100, 100);") 




    # 自定义热键按钮的状态与触发
    def costomKeysBoard_pressed(self):
        self.costomKeysBoard.setStyleSheet(
            "background-color: rgb(230, 230, 230); color: rgb(170, 170, 170);"
                                        "font-family: '微软雅黑'; font-size: 10pt; border-radius: 10px;"
                                        )

    def costomKeysBoard_released(self):
        self.costomKeysBoard.setStyleSheet(
            "background-color: rgb(245, 245, 245); color: rgb(175, 175, 175);"
                                        "font-family: '微软雅黑'; font-size: 10pt; border-radius: 10px;"
                                        )
        

    def costomKeysBoard_entered(self, event):
        self.costomKeysBoard.setStyleSheet(
            "background-color: rgb(245, 245, 245); color: rgb(175, 175, 175); font-family: '微软雅黑'; font-size: 10pt; border-radius: 10px;"
            )

    def costomKeysBoard_left(self, event):
        self.costomKeysBoard.setStyleSheet(
            "background-color: rgb(250, 250, 250); color: rgb(180, 180, 180);"
            "font-family: '微软雅黑'; font-size: 10pt; border-radius: 10px;"
            )

    def costomKeysBoard_fn(self, event):
        dialog = Dialog()
        dialog.exec_()

    # 启动按住
    def start_button_pressed(self):
        self.start_button.setStyleSheet(
            "background-color: rgb(97, 138, 51); color: rgb(43, 70, 35);"
                                        "font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
                                        )
    # 启动松开
    def start_button_released(self):
        self.start_button.setStyleSheet(
            "background-color: rgb(192, 221, 155); color: rgb(112, 137, 72);"
                                        "font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
                                        )
    # 启动光标指着
    def start_button_entered(self, event):
        self.start_button.setStyleSheet(
            "background-color: rgb(162, 207, 117); color: rgb(83, 110, 49); font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
            )
    # 启动光标离开
    def start_button_left(self, event):
        self.start_button.setStyleSheet(
            "background-color: rgb(192, 221, 155); color: rgb(112, 137, 72);"
            "font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
            )
        

    def conffer_button_pressed(self):
        self.conffer_button.setStyleSheet(
            "background-color: rgb(7, 84, 147); color: rgb(0, 51, 86);"
                                        "font-family: '微软雅黑'; font-size: 12pt; border-radius: 10px;"
                                        )

    def conffer_button_released(self):
        self.conffer_button.setStyleSheet(
            "background-color: rgb(82, 177, 255); color: rgb(0, 118, 183);"
                                        "font-family: '微软雅黑'; font-size: 12pt; border-radius: 10px;"
                                        )
        

    def conffer_button_entered(self, event):
        self.conffer_button.setStyleSheet(
            "background-color: rgb(82, 177, 255); color: rgb(0, 118, 183); font-family: '微软雅黑'; font-size: 12pt; border-radius: 10px;"
            )

    def conffer_button_left(self, event):
        self.conffer_button.setStyleSheet(
            "background-color: rgb(201, 227, 252); color: rgb(89, 156, 211);"
            "font-family: '微软雅黑'; font-size: 12pt; border-radius: 10px;"
            )

    # 停止按住
    def stop_button_pressed(self):
        self.stop_button.setStyleSheet(
            "background-color: rgb(153, 43, 58); color: rgb(69, 4, 22);"
                                        "font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
                                        )
    # 停止松开
    def stop_button_released(self):
        self.stop_button.setStyleSheet(
            "background-color: rgb(225, 115, 115); color: rgb(170, 52, 72);"
                                        "font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
                                        )
        
    # 停止光标指着
    def stop_button_entered(self, event):
        self.stop_button.setStyleSheet(
            "background-color: rgb(236, 110, 122); color: rgb(170, 52, 72); font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
            )
    # 停止光标离开
    def stop_button_left(self, event):
        self.stop_button.setStyleSheet(
            "background-color: rgb(227, 187, 189); color: rgb(175, 108, 118);"
            "font-family: '微软雅黑'; font-size: 13pt; border-radius: 10px;"
            )

    # 点击退出按钮-链接功能函数
    def quit_button_handle_button_click(self):
        CloseSoftApp()

    # 点击最小化按钮-链接功能函数
    def min_button_handle_button_click(self):
        self.showMinimized()


    def paintEvent(self, event):
        painter = QPainter(self)
        painter.setRenderHint(QPainter.Antialiasing)
        rect = self.rect()
        color = QColor(245, 245, 245, 255)
        painter.setPen(Qt.NoPen)
        painter.setBrush(QBrush(color))
        painter.drawRoundedRect(rect, 25, 25)

    def mousePressEvent(self, event: QMouseEvent):
        if event.button() == Qt.LeftButton:
            self.draggable = True
            self.mousePos = event.pos()

    def mouseMoveEvent(self, event: QMouseEvent):
        if self.draggable and (event.buttons() & Qt.LeftButton):
            self.move(self.pos() + event.pos() - self.mousePos)

    def mouseReleaseEvent(self, event: QMouseEvent):
        if event.button() == Qt.LeftButton:
            self.draggable = False
            self.mousePos = None

    def minimize_window(self):
        self.showMinimized()



    def conffer_button_fn(self):
        global offset,fov,sens,speed,ki,Noliner,range

        write_args_to_file(int(self.grab_selected),
                           int(self.infer_selected), 
                           int(self.weights_selected), 
                           int(self.MoveMode_selected), 
                           int(self.runMode_selected), 
                           int(offset), 
                           int(fov), 
                           round(sens, 2), 
                           round(speed, 2), 
                           round(ki,2),
                           round(Noliner,2),
                           int(range)
                           )

    # 运行按钮触发方法
    @pyqtSlot()
    def start_test_fn(self):

            global range,isruning

            if isruning is False:

                grab_mode, inferMode, weights, moveMode, runMode, offset, fov, sens, speed, ki, Noliner,_ = read_args_from_file()
                isruning = True
                if runMode == 0:
                    self.thread = TestFNThread(weights,
                                            grab_mode,
                                            inferMode,
                                            moveMode,
                                            offset,
                                            fov,
                                            sens,
                                            speed,
                                            ki,
                                            Noliner,
                                            range
                                            )
                self.textedit.clear()
                self.thread.print_signal.connect(self.print_text) # 链接主逻辑线程的打印内容
                self.thread.start()  # 启动线程

            elif isruning is True:
                msg_box = QMessageBox()
                msg_box.setWindowTitle("错误")
                msg_box.setText("软件正在运行中")
                msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
                msg_box.exec_()


    # 停止按钮触发方法
    @pyqtSlot()
    def stop_test_fn(self):
        global isruning

        if isruning is False:
            msg_box = QMessageBox()
            msg_box.setWindowTitle("错误")
            msg_box.setText("软件未在运行中")
            msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
            msg_box.exec_()

        elif isruning is True:

            if self.thread is not None and self.thread.isRunning():  # 线程存在并且在运行中

                isruning = False
                
                self.thread.terminate()  # 终止线程的执行
                self.thread.wait()  # 等待线程执行完毕
                winsound.Beep(400, 200)
                winsound.Beep(300, 200)
                winsound.Beep(200, 200)
                self.textedit.clear()
                
                new_text = "欢迎使用kjCheat 目标检测神经网络辅助" + " \n" + "kjCheat v2 for Apex" + " \n" + " \n" + "若需要个人定制请联系您的卖家, 老实人老佛爷从不骗人 : D"
                self.textedit.setText(new_text)

            else:
                msg_box = QMessageBox()
                msg_box.setWindowTitle("错误")
                msg_box.setText("软件未在运行中")
                msg_box.setWindowFlags(msg_box.windowFlags() | QtCore.Qt.CustomizeWindowHint)
                msg_box.exec_()



    # 将打印内容添加到QTextEdit组件中
    @pyqtSlot(str)
    def print_text(self, text):
        self.textedit.append(text)



def RunMainUi():
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    sys.exit(app.exec())


if __name__ == '__main__':

    RunMainUi()