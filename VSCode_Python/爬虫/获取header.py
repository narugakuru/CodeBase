import requests

URL = "http://localhost:8080/basic"
web = requests.get(URL)
web.status_code
print(web.text)

""" GET /basic# HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0
Accept: image/webp,*/*
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Connection: keep-alive
Referer: http://localhost:8080/basic
Cookie: Idea-a5fccd23=aaa376b5-04df-48eb-b25a-dc6d219dbb60; JSESSIONID=C85FE07EE6FB19E220B26A724DD08A0B """

