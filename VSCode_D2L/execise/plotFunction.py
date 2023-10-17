import numpy as np
from matplotlib_inline import backend_inline
from d2l import torch as d2l


def use_svg_display():  # @save
    """使用svg格式在Jupyter中显示绘图"""
    backend_inline.set_matplotlib_formats("svg")


def set_figsize(figsize=(3.5, 2.5)):  # @save
    """设置matplotlib的图表大小"""
    use_svg_display()
    d2l.plt.rcParams["figure.figsize"] = figsize


# @save
def set_axes(axes, xlabel, ylabel, xlim, ylim, xscale, yscale, legend):
    """设置matplotlib的轴"""
    # X，Y轴名称
    axes.set_xlabel(xlabel)
    axes.set_ylabel(ylabel)
    # 默认linear线性的
    axes.set_xscale(xscale)
    axes.set_yscale(yscale)
    # None
    axes.set_xlim(xlim)
    axes.set_ylim(ylim)
    # 标注
    if legend:
        axes.legend(legend)
    # 显示网格
    axes.grid()


# @save
def plot(
        X,
        Y=None,
        xlabel=None,
        ylabel=None,
        legend=None,
        xlim=None,
        ylim=None,
        xscale="linear",
        yscale="linear",
        fmts=("-", "m--", "g-.", "r:"),
        figsize=(3.5, 2.5),
        axes=None,
):
    """绘制数据点"""
    if legend is None:
        legend = []
    # 设置图像大小
    set_figsize(figsize)
    axes = axes if axes else d2l.plt.gca()

    # 错误检测 如果X有一个轴，输出True。X为单个对象或对象list
    def has_one_axis(X):
        return (hasattr(X, "ndim") and X.ndim == 1
                or isinstance(X, list) and not hasattr(X[0], "__len__"))

    # 处理X，Y轴
    if has_one_axis(X):
        X = [X]
    # y为none则令y=x，x=对应数量的空list
    if Y is None:
        X, Y = [[]] * len(X), X
    elif has_one_axis(Y):
        Y = [Y]
        print("Y轴", Y)
    if len(X) != len(Y):
        X = X * len(Y)

    # matplotlib 库的 pyplot 模块中的 cla（） 函数用于清除当前轴。
    axes.cla()

    # n个f（x）就遍历 n次，XY为二维列表，xy是一维list，fmt是次要参数
    for x, y, fmt in zip(X, Y, fmts):
        if len(x):
            axes.plot(x, y, fmt)
            print("X点数量：", len(x))
        else:
            axes.plot(y, fmt)

    # 设置matplotlib轴
    set_axes(axes, xlabel, ylabel, xlim, ylim, xscale, yscale, legend)

    # XY突然有值，指针？
    # print('X:',X,'\n')
    # print('Y:',Y,'\n')
