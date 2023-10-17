import requests

# 自定义请求头
headers = {
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36 Edg/89.0.774.76"
}
# 指向ajax的请求的url（抓包获取）
url = "http://www.kfc.com.cn/kfccda/aspx/GetStoreList.ashx?op=keyword"

city = "南昌"
for page in range(10):
    data = {
        "cname": "",
        "pid": "",
        "keyword": city,
        "pageIndex": page,
        "pageSize": "10",
    }
    # 发起基于ajax的post请求
    res_json = requests.post(url=url, data=data, headers=headers).json()

for res in res_json["Table1"]:
    detail_dict = {"stroName": res["storeName"], "addrs": res["addressDeatail"]}
    print(detail_dict)
