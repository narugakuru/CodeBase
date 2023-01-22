""" # x, y = 1, 3
# z = 2

# x, y = y, x
# print(x, y)
def showNnumber(numbers):

    for n in numbers:
        print(n)


showNnumber("bcesf")


class Mysort:
    # def __init__(self):
    #     pass

    def p(self):
        print("fq")


m = Mysort()
m.p()

ii = [0, 455, 6454, 556]
print(ii, "habatas")
 """

# x, y, z = 2, 5, 6
# print(n(x, y, z))
n = int(input())
for ceng in range(1, n + 1):
    for i in range(n - ceng):
        print(" ", end="")
    for j in range(2 * ceng - 1):
        print("*", end="")
    print("")
