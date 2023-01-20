for i in range(1, 1000):
    bai = i % 1000 // 100
    shi = i % 100 // 10
    ge = i % 10
    tmp = shi * shi * shi + bai * bai * bai + ge * ge * ge
    if tmp == i:
        print(i,"其三位数：", bai, shi, ge)
