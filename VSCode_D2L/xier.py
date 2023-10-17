def shell_sort(arr):
    n = len(arr)  # 获取数组的长度
    gap = n // 2  # 设置初始间隔为数组长度的一半

    while gap > 0:  # 当间隔大于0时，进行循环
        for i in range(gap, n):  # 从间隔位置开始遍历数组
            temp = arr[i]  # 将当前位置的元素存储在临时变量中
            j = i  # 将当前位置赋值给变量j

            # 在当前位置之前的元素中，每隔gap个位置进行比较
            # 如果前一个元素大于当前元素，则将前一个元素后移gap个位置
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap

            arr[j] = temp  # 将临时变量中存储的元素放置到正确的位置
        gap //= 2  # 缩小间隔

    return arr  # 返回排序后的数组


# 测试集
test_set = [87, 34, 56, 12, 98, 45, 23, 67, 89, 10,
            55, 78, 32, 21, 43, 65, 87, 99, 76, 54,
            32, 12, 43, 65, 87, 34, 56, 12, 98, 45,
            23, 67, 89, 10, 55, 78, 32, 21, 43, 65,
            87, 99, 76, 54, 32, 12, 43, 65, 87, 34,
            56, 12, 98, 45, 23, 67, 89, 10, 55, 78,
            32, 21, 43, 65, 87, 99, 76, 54, 32, 12,
            43, 65, 87, 34, 56, 12, 98, 45, 23, 67,
            89, 10, 55, 78, 32, 21, 43, 65, 87, 99,
            76, 54, 32, 12, 43, 65, 87, 34, 56, 12,
            98, 45, 23, 67, 89, 10, 55, 78, 32, 21,
            43, 65, 87, 99, 76, 54, 32, 12, 43, 65]

sorted_set = shell_sort(test_set)
print(sorted_set)