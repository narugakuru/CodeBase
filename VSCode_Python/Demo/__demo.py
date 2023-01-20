# python是世界上最好的语言
import math

n = 2021041820210418
ans = 0
ls = []

for i in range(1, int(math.sqrt(n))):
    if n % i == 0:
        ls.append(int(i))
        ls.append(int(n / i))

size = len(ls)
for i in range(size):
    for j in range(size):
        for p in range(size):
            if ls[i] * ls[j] * ls[p] == n:
                ans = ans + 1
print(ans)

