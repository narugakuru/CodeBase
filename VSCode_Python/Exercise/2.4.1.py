# 1my=6.8rmb
# -* - coding: UTF-8 -* -
#! C:\\Program Files\\Python3.9.1\\python.exe

value = float(input("输入值："))
unit = input("输入单位：")
if unit == "usd" or unit == "美元":
    print("{}美元={:.2f}人民币".format(value, value * 6.8))
elif unit == "cny" or unit == "人民币":
    print("{}人民币={:.2f}人民币".format(value, value / 6.8))

else:
    print("无效数据")