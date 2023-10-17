import requests
from bs4 import *
# bs4==4.2.0

header = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.0.0'}

# https://www.umei.net/meinvtupian/xingganmeinv/
URL = "https://www.umei.cc/meinvtupian/"

reqs = requests.get(URL,headers=header)
reqs.encoding = "utf-8"

# 解析html
main_page = BeautifulSoup(reqs.text, "html.parser")

print(main_page)

# find然后find_all获取所有的超链接
# alist = main_page.find("div", attrs={"class": "TypeList"}).find_all("a", attrs={"class": "TypeBigPics"})
alist = main_page.find("div", attrs={"class": "taotu-main"}).find_all(
    "a", attrs={"class": "lazy"}
)
print(alist.text)

n = 1
num = 1
# 遍历处理每个子页面的图片
for a in alist:
    # 获取子页面链接
    href = "https://www.umei.net" + a.get("href")
    reqs_a = requests.get(href)
    reqs_a.encoding = "utf-8"
    # 解析子页面
    child_page = BeautifulSoup(reqs_a.text, "html.parser")
    # 获取第一张图片路径
    src = child_page.find("div", attrs={"class": "ImageBody"}).find("img").get("src")
    # 从服务器下载图片
    image = requests.get(src).content
    # 写入图片
    with open("image\\tu_%s.jpg" % n, mode="wb") as f:  # wb以二进制写入
        f.write(image)

    # 第二张开始
    num += 1
    # 无法获取图片数，转向无限循环至break
    href = href[0:-4]  # 去出.htm尾戳
    for i in range(2, 12):
        print("第{}次执行".format(i))
        try:
            hrefs = href + "_" + str(i) + ".htm"
            # 获取到了本页面其他图片的地址
            print("获取到了本页面其他图片的地址" + hrefs)
            htm_n = requests.get(hrefs)
            # 解析
            child_page = BeautifulSoup(htm_n.text, "html.parser")
            # 获取到了图片服务器地址
            bs_src = (
                child_page.find("div", attrs={"class": "ImageBody"})
                .find("img")
                .get("src")
            )
            print("获取到了图片服务器地址" + bs_src)
            image = requests.get(bs_src).content
            localSrc = "image\\tu_" + str(n) + "_" + str(i) + ".jpg"
            print(localSrc)
            with open(localSrc, mode="wb") as f:
                f.write(image)
                print("喜加{}！！".format(num))
            num += 1
        except Exception as e:
            break
    print("本图片也爬完")
    # 输出提示
    print("喜加{}组图片".format(n))
    n += 1
