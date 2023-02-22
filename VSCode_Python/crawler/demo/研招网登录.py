from selenium.webdriver import Chrome
import selenium
import time
from selenium.webdriver.common.keys import Keys
from sympy import im
import random


def login():
    url = "https://yz.chsi.com.cn/"

    web = Chrome()

    web.get(url)

    web.find_element_by_xpath("/html/body/div[1]/div[2]/div/div/a[1]").click()

    web.find_element_by_xpath(
        "/html/body/div[1]/div/div[2]/div/form/div[2]/input"
    ).send_key("17679395638")

    web.find_element_by_xpath(
        "/html/body/div[1]/div/div[2]/div/form/div[3]/input"
    ).send_key("noesis360724")

    web.find_element_by_xpath(
        "/html/body/div[1]/div/div[2]/div/form/div[4]/input"
    ).click()

    return web


if __name__ == "main":
    web = login()

