import requests
from bs4 import *

req = requests.get("https://www.baidu.com", timeout=6)
print(req.status_code)
req.encoding = "utf-8"
# req.json()
# print(req.text)
req.close()
soup = BeautifulSoup(req.text, "lxml")
# 树全展开
# print(soup.prettify())
print(soup.a.string)
print(soup.head.prettify())