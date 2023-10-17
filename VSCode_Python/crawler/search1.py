import requests

keyword = "python"
url = "http://www.baidu.com/s"
try:
    kv = {"q": keyword}

    req = requests.get(url, params=kv)
    req.encoding = "utf-8"

    req.raise_for_status()

    print(len(req.text))
except:
    print("爬取失败")