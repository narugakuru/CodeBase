# In[1]:
import numpy as np


# In[2]:
print("�б���һά����")
data = [1, 2, 3, 4, 5]
x = np.array(data)
print(x)
print(x.dtype)

# In[3]:
print("Ԫ�鴴����ά����")
data2 = ([1, 2], [2, 3], [5, 6])
x = np.array(data2)
print(x)
print(x.ndim)  # ά��
print(x.shape)  # ��ά�ȴ�С


# In[4]:
print("����zero,ones,empyt")

print("zerosһά")
arr1 = np.zeros(4)
print(arr1)

print("zeros��ά")
arr2 = np.zeros((3, 2))
print(arr2)

print("ones")
arr3 = np.ones((2, 2))
print(arr3)

print("�ն�ά����")
arr4 = np.empty((2, 3))
print(arr4)

print("�Ȳ�����")
print(np.arange(5))
print(np.arange(0, 10, 2))


# In[5]:
print("��������������������������:")
arr5 = np.array([[1, 2, 3], [4, 5, 6]])
print(arr5 * 3)

