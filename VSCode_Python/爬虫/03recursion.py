import os


def func(mypath, floors):
    dirList = os.listdir(mypath)
    # print(dirList)
    for Name in dirList:
        # 获取文件所在路径
        real_path = os.path.join(mypath, Name)
        # 判断name是否为文件夹
        if os.path.isdir(real_path):
            print("|--" * floors + Name)
            func(real_path, floors + 1)
        else:
            print("|--" * floors + Name)


if __name__ == "__main__":
    p = "D:\\Code\\VSCode_C++\\"
    func(p, 1)
