from selenium.webdriver import Chrome
import time
from selenium.webdriver.common.keys import Keys
from sympy import im
import random

url = "https://yz.chsi.com.cn/zsml/queryAction.do"

web = Chrome()

web.get(url)

# 选择查询：省，门类，学科类别，学习方式
# random.random()  # 0，1之间的浮点数

time.sleep(random.random())
web.find_element_by_xpath('//*[@id="ssdm"]/option[15]').click()  # 省份
time.sleep(random.random())
web.find_element_by_xpath(
    '//*[@id="mldm"]/optgroup[2]/option[8]').click()  # 类别
time.sleep(random.random())
web.find_element_by_xpath('//*[@id="yjxkdm"]/option[13]').click()  # 学科
time.sleep(random.random())
web.find_element_by_xpath('//*[@id="xxfs"]/option[2]').click()  # 默认全日制

time.sleep(1)
# 查询跳转
web.find_element_by_xpath(
    '//*[@id="form2"]/table/tbody/tr[4]/td/input').click()
time.sleep(1)

# 获取所有大学的a链接列表 类型WebElement
aList = web.find_element_by_class_name(
    "ch-table").find_elements_by_name("form3")

f = open("研究生招生信息.txt", mode="a+")

for a in aList:
    # 选择一个大学
    a.find_element_by_tag_name("a").click()
    web.switch_to.window(web.window_handles[-1])

    # 获取所有专业方向链接,tbody,tr,td[4],a
    subject_list = web.find_element_by_xpath(
        "/html/body/div[2]/div[3]/div/div[2]/table/tbody"
    ).find_elements_by_tag_name("tr")

    # 获取单个专业的信息
    for subject in subject_list:
        subject.find_elements_by_tag_name("a")[1].click()
        web.switch_to.window(web.window_handles[-1])
        subject_info = web.find_element_by_class_name("zsml-wrapper").text
        print(subject_info)
        f.write("\n")
        f.write("===========================================")
        f.write("\n")
        f.write(subject_info)
        web.close()
        web.switch_to.window(web.window_handles[-1])

    web.close()
    web.switch_to.window(web.window_handles[-1])
    # 爬完返回上一个页面

f.close()
