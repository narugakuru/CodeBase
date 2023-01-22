import requests
from bs4 import *
import re, string

url = "http://datachart.500.com/ssq/history/history.shtml"

web = requests.get(url)
web.encoding = "utf-8"
# 获取到了html树
web = BeautifulSoup(web.text, "html.parser")
bodyList = web.find("tbody", attrs={"id": "tdata"}).find_all(
    "tr", attrs={"class": "t_tr1"}
)

# bodyList = bodyList[0]
# 期号  	红球号码 	蓝球 	快乐星期天 	奖池奖金(元) 	一等奖 	二等奖 	总投注额(元) 	开奖日期
# 21062	12	13	15	20	22	25	10	727,417,696	6 8,804,175	67	425,840	405,763,536	2021-06-06
# 2106212131520222510 727,417,69668,804,17567425,840405,763,5362021-06-06
lists = []

for tr in bodyList:
    tds = tr.find_all("td")
    for td in tds:
        text = td.text
        text = re.sub(",", "", text)
        lists.append(text.split(","))
    lists.append("\n")

with open("D:\Code\VSCode_Python\彩票数据.csv", mode="w+", encoding="utf-8") as f:
    for l in lists:
        l = str(l)
        l = l.replace(r"['", "")
        l = l.replace(r"']", ",")
        l = l.replace("\\xa0,", "")
        f.write(l)