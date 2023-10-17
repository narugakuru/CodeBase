from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys


""" 进入搜索界面
web.get(
    "https://search.51job.com/list/130200,000000,0000,00,9,99,%2B,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare="
) """


# 登录模块
def login(web):
    web.get(
        "https://login.51job.com/login.php?loginway=0&isjump=0&lang=c&from_domain=i&url="
    )

    web.maximize_window()

    web.find_element_by_xpath('//*[@id="loginname"]').send_keys("17679395638")

    web.find_element_by_xpath('//*[@id="password"]').send_keys("noesis360724")

    web.find_element_byxpath(
        "/html/body/div[3]/div[1]/div[2]/div[3]/form/div[4]/em"
    ).click()

    web.find_element_by_xpath('//*[@id="login_btn"]').click()


if __name__ == "__main__":
    web = webdriver.Chrome()
    login(web)
    # 进入搜索页面
    web.find_element_by_xpath("/html/body/div[1]/div[4]/div/p/a[2]").click()

    # 爬list
    alist = web.find_element_by_xpath(
        "/html/body/div[1]/div/div[2]/div/div[1]/div[2]/div[3]/div[1]"
    )
    print(alist)

