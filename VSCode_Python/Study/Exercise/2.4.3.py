# -* - coding: UTF-8 -* -
#! C:\\Program Files\\Python3.9.1\\python.exe
import math

value = int(input("输入一个三位数"))
# value_str = str(value)

# if value == math.pow(int(value_str[0]), 3) + math.pow(int(
#         value_str[1]), 3) + math.pow(int(value_str[2]), 3):

if value == math.pow(int(value / 100), 3) + math.pow(
    (value % 100 / 10), 3) + math.pow(value % 100, 3):
    print("yes")
else:
    print("no")
