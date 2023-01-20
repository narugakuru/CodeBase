import time

print(time.ctime())
times = time.localtime()
print(times)
print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))

""" time_left = 10
while time_left > 0:
    print("倒计时：(s)", time_left)
    time.sleep(1)
    time_left -= 1 """

scale = 50
strat = time.perf_counter()
for i in range(scale + 1):
    a = "*" * i
    b = "." * (scale - i)
    c = (i / scale) * 100
    dur = time.perf_counter() - strat
    print("\r{:.3f}%[{}->{}]{:.2f}s".format(c, a, b, dur), end="")
    time.sleep(0.03)
