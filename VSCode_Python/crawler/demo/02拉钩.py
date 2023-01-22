from selenium.webdriver import Chrome
import time
from selenium.webdriver.common.keys import Keys

# 创建浏览器
web = Chrome()
# 打开浏览器到lagou
web.get("https://www.lagou.com")

# 关闭弹窗
web.find_element_by_xpath('//*[@id="cboxClose"]').click()

# 设置延迟,等待网页响应
time.sleep(1)
# 在搜索框输入 java
web.find_element_by_xpath('//*[@id="search_input"]').send_keys("java", Keys.ENTER)

aList = web.find_elements_by_class_name("position_link")

for a in aList:
    # 找到h3并点击
    a.find_element_by_tag_name("h3").click()
    # 窗口转换
    web.switch_to.window(web.window_handles[-1])  # -1倒数第一个
    # 获取文本
    txt = web.find_element_by_xpath('//*[@id="job_detail"]/dd[2]/div').text
    # 保存在文件中
    with open("求职.txt", mode="a+") as f:
        f.write("\n")
        f.write("=================")
        f.write(txt)

    # 关闭窗口，并调整到初始页面
    web.close()
    web.switch_to.window(web.window_handles[-1])
    time.sleep(1)
