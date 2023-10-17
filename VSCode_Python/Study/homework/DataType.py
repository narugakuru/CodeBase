# -* - coding: UTF-8 -
""" 
数字类型：整型，浮点型1000=1e3，布尔型01，复数类型a+bj
组合数据类型：
    序列类型：
        字符串""
        列表[,]
        元组(,)，（不可修改的列表）
    集合类型：
        集合{,}
    映射类型：
        字典{Key:Value,},dict([(,),(,)])

"""

# 数据类型
# isinstance 和 type 的区别在于：
# type()不会认为子类是一种父类类型。
# isinstance()会认为子类是一种父类类型。

# 列表，可修改，可看作一个类
list1 = [1, 3, 2, 5, 4]
list1.append(6)  # 在末尾加上int 6
list1.pop(0)  # 删除下标为0得数据
print(list1)
# list1.insert(2, 99)
# list1.sort()  #从小到大
# list1.reverse()  #反向排列
list1.remove(3)  # 移除某个值
print(list1)

# 元组即不可修改的列表,可拼接
tuple1 = (1, 2, 3, 4, 5)


# 集合，集合内没有重复的值，没有顺序的概念，会自动排序

set1 = {3, 2, 4, 1, 5}
set2 = {2, 4, 6, 7, 8}
set1.discard(3)
print(set1.intersection(set2))
print(set1.difference(set2))
print(set2.difference(set1))
print(set1.issubset(set2))  # 子集

# 字典类型，以key搜索值
dict1 = {"name": "alice", "height": "165", "weight": "90"}
print(len(dict1))

dict1["gender"] = "女"
dict1.pop("name")
# print(dict1.keys())
# print(dict1.values())
# print(dict1.items())
print(dict1.get("gender", 0))  # 如果存在则直接调用，不存在则创建值为0的key
# dict1.clear() 清空