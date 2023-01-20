import requests
import math
import time
from PIL import Image
import matplotlib.pyplot as plt
import numpy
from wordcloud import WordCloud


def get_json(url, num, search):  # 获取JSON，获取第几页数据

    headers = {
        "User-Agent": "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Mobile Safari/537.36",
        "Host": "www.lagou.com",
        "Referer": "https://www.lagou.com/jobs/list_",
    }  # 请求头，URL中设置了城市名
    form_data = {"first": "true", "pn": num, "kd": search}  # 网页From Data参数
    cookie_url = "https://www.lagou.com/jobs/list_" + search

    session = requests.Session()
    session.get(url=cookie_url, headers=headers)
    cookies_new = session.cookies  # 获取网站cookies
    try:
        r = requests.post(
            url, headers=headers, data=form_data, cookies=cookies_new
        )  # 抓取职位信息的JOIN文件
        r.raise_for_status()
        r.encoding = "utf-8"
        # print(r.json())
        # with open(r"C:\Users\xxp\.spyder-py3\testcode\tmp.txt", "w") as fp:
        # fp.write(json.dumps(r.json(),indent=4,ensure_ascii=False)) # txt测试是否成功获取网页
        return r.json()
    except:
        return "false"


def get_a_page(result):  # 获取一页的信息
    field = ["city"]  # 要获取哪些字段信息
    page_info = []
    try:
        for i in result:
            info = []
            [info.append(i[j]) for j in field]
            page_info.append(info)
        return page_info
    except:
        return ""


def get_pages(count):
    num_max = math.ceil(count / 15)  # 向上取整 16个职位就是2 该网页一页15个职位数据
    if num_max >= 0:
        return num_max  # 爬取页数
    else:
        return num_max


def main(search, num):
    url = "https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false"
    page_1 = get_json(url, 1, search)  # 总职位数在每页的json字典中都有显示，这里随便选了第一页
    total_count = page_1["content"]["positionResult"]["totalCount"]
    num_max = get_pages(total_count)
    if num <= num_max and num > 0:
        total_info = []
        print("职位总数:{},总页数:{}".format(total_count, num_max))
        start = time.time()
        time.sleep(20)
        for n in range(1, num + 1):  # 获取每页的json,汇总数据，转成一个嵌套列表,每个子列表为一个职位的所有信息
            page = get_json(url, n, search)
            result = page["content"]["positionResult"]["result"]
            a_page_info = get_a_page(result)
            total_info += a_page_info
            print(
                "已经抓取第{}页,职位总数:{}，当前总耗时{:.2f}秒".format(
                    n, str(len(total_info)), time.time() - start
                )
            )
            time.sleep(10)  # 暂停10秒，防止被服务器拉黑

        # print(type(total_info))
        info_new = [str(i) for i in total_info]  # 转成str类型 这部分不转后续join部分会报错
        # print(type(info_new))
        contents_str = " ".join(info_new).replace("'", "")  # join生成字符串
        # print(type(contents_str))
        # 制作词云图，collocations避免词云图中词的重复，mask定义词云图的形状，图片要有背景色
        wc = WordCloud(
            # scale=4这个数值越大，产生的图片分辨率越高，字迹越清晰，最好不要超过64，运行很慢
            # stopwords=STOPWORDS.add(""),
            collocations=False,  # 避免重复值出现
            background_color="white",
            font_path="simsun.ttf",
            width=1000,
            height=860,
            random_state=50,  # 每个词返回一个PIL颜色
            mask=None,
        )
        wc.generate(contents_str)
        # wc.to_file(r"C:\Users\xxp\.spyder-py3\testcode\test\ciyun.png")#保存本地时候会清晰和绘图里面保存不一样
        plt.axis("off")
        plt.imshow(wc)
    else:
        return print("页码输入不在或超过范围" + str(num_max))  # int类型转为str以免超过时候报错


if __name__ == "__main__":
    search = input("输入搜索职位:")
    num = int(input("输入页码:"))  # 输入整数
    main(search, num)
