# In[1]:
import numpy as np


# In[2]:
print("列表创建一维数组")
data = [1, 2, 3, 4, 5]
x = np.array(data)
print(x)
print(x.dtype)

# In[3]:
print("元组创建二维数组")
data2 = ([1, 2], [2, 3], [5, 6])
x = np.array(data2)
print(x)
print(x.ndim)  # 维度
print(x.shape)  # 各维度大小


# In[4]:
print("创建zero,ones,empyt")

print("zeros一维")
arr1 = np.zeros(4)
print(arr1)

print("zeros二维")
arr2 = np.zeros((3, 2))
print(arr2)

print("ones")
arr3 = np.ones((2, 2))
print(arr3)

print("空二维数组")
arr4 = np.empty((2, 3))
print(arr4)

print("等差数列")
print(np.arange(5))
print(np.arange(0, 10, 2))


# In[5]:
print("数组与标量，数组与数组的运算:")
arr5 = np.array([[1, 2, 3], [4, 5, 6]])
print(arr5 * 3)

