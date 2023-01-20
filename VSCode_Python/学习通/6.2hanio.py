# A通过B移动到C
def hanio(n, A, B, C):
    if n == 1:
        print("{}:{}->{}".format(1, A, C))

    else:
        hanio(n - 1, A, C, B)
        print("{}:{}->{}".format(n, A, C))
        hanio(n - 1, B, A, C)


hanio(3, "A", "B", "C")
