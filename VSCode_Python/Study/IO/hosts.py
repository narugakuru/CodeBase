# 防止命令行显示中文乱码
import sys
import os

path = "C:\\Windows\\System32\\drivers\\etc\\hosts"


def add(ip, domain):  # 在文件下增加内容
    try:
        with open(path, "a+") as text:
            for line in text.readlines():
                if ip + " " + domain in line:
                    print("已经存在")
                    break

            text.write("\n" + ip + " " + domain)
            print("添加成功")
    except:
        print("添加出错")


def delete(ip, domain):
    with open(path) as text, open("2.txt", "w") as new_text:
        try:
            for line in text.readlines():
                new_line = line.replace("\n" + ip + " " + domain, "")
                new_text.write(new_line)
        except:
            print("删除出错")
    os.remove(path)
    os.rename("2.txt", "文件名")
    print("删除成功")


if __name__ == "__main__":

    command = sys.argv[1].lower()  # 参数一
    ip = sys.argv[2]  # 参数二
    domain = sys.argv[3]  # 参数三

    if command == "add":
        add(ip, domain)
    elif command == "del":
        delete(ip, domain)
    else:
        print("没有此命令")
