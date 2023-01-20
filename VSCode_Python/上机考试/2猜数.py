import random

input_count = 1
i = random.randint(0, 100)
print(i)
while True:
    try:
        temp = int(input("input please："))
        input_count += 1
        assert temp > 0 and temp < 100, "请输入1~100范围内的数"

        if temp == i:
            print("yes")
            break
        elif temp > i:
            print("很遗憾，太大了")
        elif temp < i:
            print("很遗憾，太小了")
    except ValueError:
        print("请输入数字")
    except Exception as e:
        print(e)


print("你输入了%d次" % input_count)
