# raise KeyError

""" f = open("D:\Code\VSCode_Python\text1.txt", "r")

try:
    data = f.read()
finally:
    f.close() """

with open("D:\Code\VSCode_Python\text1.txt", "r") as f:
    data = f.read()