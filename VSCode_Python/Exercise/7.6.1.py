import math


class Circle:
    def __init__(self, x, y, r, color=""):
        self.x = x
        self.y = y
        self.r = r
        self.coler = color

    def Perimeter(self):
        print(2 * math.pi * self.r)

    def area(self):
        print(math.pi * math.pow(self.r, 2))


def main():
    c = Circle(0, 0, 5, "red")
    c.Perimeter()
    c.area()


main()
