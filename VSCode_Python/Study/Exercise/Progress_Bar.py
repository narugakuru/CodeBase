import time

print("=" * 10 + "开始" + "=" * 10)
incomplete_sign = 50

for i in range(incomplete_sign + 1):
    completed = "*" * i  #完成的部分
    incomplete = "." * (incomplete_sign - i)  #未完成的部分
    percentage = (i / incomplete_sign) * 100  #百分比
    print("\r{:.0f}%[{}{}]".format(percentage, completed, incomplete), end="")
    time.sleep(0.02)

print()
print("=" * 10 + "结束" + "=" * 10)
