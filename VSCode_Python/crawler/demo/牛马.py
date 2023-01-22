# In[1]
import requests  # import 为导包操作
import os

url = "http://img0.dili360.com/ga/M00/4A/77/wKgBzFsfM-2ADQ9iAC7rBKt1uIE377.tub.jpg"  # 指定网址
root = "C:/Users/Raisei/Pictures//"  # 指定本地存储目录
path = root + url.split("/")[-1]  # 指定存储路径
try:  # 防止出现异常
    if not os.path.exists(root):  # 如果该目录不存在，就创建该目录
        os.mkdir(root)
    if not os.path.exists(path):  # 如果该路径不存在
        r = requests.get(url)  # 发送请求
        with open(path, "wb") as f:  # 二进制形式打开文件
            f.write(r.content)  # 写入文件
            f.close()  # 关闭
            print("文件保存成功")
    else:
        print("文件已存在")
except:
    print("爬取失败")

# In[2]

import pandas as pd  # 为导包操作
import re
import requests
from bs4 import BeautifulSoup


def return_soup(url):  # 静态获取网页的源码，返回 lxml 解析格式
    request = requests.get(url)  # 构造一个向服务器请求资源的 url 对象
    request.encoding = request.apparent_encoding  # 解决中文乱码问题
    soup = BeautifulSoup(
        request.text, "html.parser"
    )  # 构造bs对象解析html页面lxml也是解析器，其作为解释器比html更快
    return soup  # 返回该 bs 对象


def get_url(url="https://www.cas.cn/kxyj/cb/qk/index.shtml"):  # 获得每期的 url
    soup = return_soup(url)  # 得到这个 bs 对象
    journal_list = soup.find("ul", class_="gl_list2 gl_list_qk").find_all(
        "li"
    )  # 通过目录树最终找到 li 标签
    journalpd = pd.DataFrame()  # 构建 DataFrame 对象
    i = 0
    # 返回一个支持迭代的枚举对象，枚举对象生成包含一个计数(start默认为0)和一个由iterable参数生成的值的对。
    for m, iss in enumerate(journal_list):
        # 获取数据并保存到 DataFrame
        journalpd.loc[i, " 期 刊 名 称 "] = (
            journal_list[m].find("a").attrs["title"]
        )  # 获 取title 的内容
        journalpd.loc[i, "url"] = journal_list[m].find("a").attrs["href"]  # 获取 href 的内容
        i = i + 1
    return journalpd


journalpd = get_url(url="https://www.cas.cn/kxyj/cb/qk/index.shtml")
journalpd.to_excel("journal.xlsx")  # 调用此函数 保存成 excel

# In[3]

import requests  # 均为导包操作
from lxml import etree
import json
import openpyxl

url = "https://voice.baidu.com/act/newpneumonia/newpneumonia"  # 指定 url
response = requests.get(url)  # 发送请求
# print(response.text)
# 生成 HTML 对象
html = etree.HTML(response.text)

result = html.xpath(
    '//script[@type="application/json"]/text()'
)  # 使用 xpath 定位到类型为”application/json”的 script 下面的文本内容
result = result[0]  # 返回列表的第一个

# json.load()方法可以将字符串转化为 python 数据类型
result = json.loads(result)  # 转成 json 字符串

# 创建工作簿
wb = openpyxl.Workbook()
# 创建工作表
ws = wb.active
ws.title = "国内疫情"  # 指明标题
ws.append(
    ["省份", "累计确诊", "死亡", "治愈", "现有确诊", "累计确诊增量", "死亡增量", "治愈增量", "现有确诊增量"]
)  # 指明列名
result_in = result["component"][0]["caseList"]  # 通过 json 字符串层层查找需要的数据
data_out = result["component"][0]["globalList"]

""" area --> 大多为省份
city --> 城市
confirmed --> 累计
crued --> 值域
relativeTime -->
confirmedRelative --> 累计的增量
curedRelative --> 值域的增量
curConfirm --> 现有确镇
curConfirmRelative --> 现有确镇的增量
"""
for each in result_in:  # 将每一个查找到的数据，封装到一个列表里面
    temp_list = [
        each["area"],
        each["confirmed"],
        each["died"],
        each["crued"],
        each["curConfirm"],
        each["confirmedRelative"],
        each["diedRelative"],
        each["curedRelative"],
        each["curConfirmRelative"],
    ]
    for i in range(len(temp_list)):  # 迭代
        if temp_list[i] == "":  # 如果数据不存在，赋值为 0
            temp_list[i] = "0"
    ws.append(temp_list)  # 追加到列表之中

# 获取国外疫情数据
for each in data_out:
    sheet_title = each["area"]  # 指定标题
    # 创建一个新的工作表
    ws_out = wb.create_sheet(sheet_title)
    ws_out.append(["国家", "累计确诊", "死亡", "治愈", "现有确诊", "累计确诊增量"])
    for country in each["subList"]:  # 封装数据到一个列表里面
        list_temp = [
            country["country"],
            country["confirmed"],
            country["died"],
            country["crued"],
            country["curConfirm"],
            country["confirmedRelative"],
        ]
        for i in range(len(list_temp)):
            if list_temp[i] == "":  # 如果数据不存在，就赋值 0
                list_temp[i] = "0"
        ws_out.append(list_temp)
wb.save("./data.xlsx")  # 保存到当前目录的 exce

