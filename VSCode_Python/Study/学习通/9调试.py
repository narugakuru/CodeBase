class mydate:
    def __init__(self, year, month, day):
        # if (
        #     isinstance(year, int) & isinstance(month, int) & isinstance(day, int)
        # ) == False:
        #     raise Exception("无效日期")
        assert (
            isinstance(year, int) & isinstance(month, int) & isinstance(day, int)
        ), "非法输入"

        # 判断是否为整数类型
        self.year = year
        self.month = month
        self.day = day


# date1 = mydate(2019, 11, 23)
# print(date1.__dict__.items())
try:
    date = mydate("?", 1, 3)
    print(date)
    print(date.__dict__.items())
except AssertionError as e:
    print(e)
