import math

# In[1]:
list1 = [1, 3, 2, 4, 1, 2, 3, 5, 4]
resulet = 0
for val in list1:
    resulet = resulet ^ val
    print(resulet)
print("{}".format(resulet))
print(resulet)
