import os
import sys

# 请在此输入您的代码
n, m = map(int, input().split())
# 创建数组n
array = [i for i in range(1, n+1)]

#操作m次
for i in range(m):
    p,q = map(int, input().split())
    if p==0:
        # 降序
        array[0:q] = sorted(array[0:q],reverse=True)
    else:
        # 升序
        array[q-1:] = sorted(array[q-1:],reverse=False)
  
for i in array:
    print(i,end=' ')      
    
# result = ' '.join(map(str, array))
# print(result)

""" import os
import sys
from collections import deque
# 请在此输入您的代码
n,m=map(int,input().split())
array = [i for i in range(1, n + 1)]
stk_=deque()
for _ in range(m):
  p,q=map(int,input().split())
  if p==0:
    while len(stk_)>0 and stk_[-1][0]==0:
      q=max(q,stk_.pop()[1])
    while len(stk_)>=2 and stk_[-2][1]<=q:
      stk_.pop()
      stk_.pop()
    stk_.append((0,q))
  elif stk_:
    while len(stk_)>0 and stk_[-1][0]==1:
      q=min(q,stk_.pop()[1])
    while len(stk_)>=2 and stk_[-2][1]>=q:
      stk_.pop()
      stk_.pop()
    stk_.append((1,q))
  #print(stk_)
for p, q in stk_:
    if p==0:
        # 降序
        array[0:q] = sorted(array[0:q],reverse=True)
    else:
        # 升序
        array[q-1:] = sorted(array[q-1:],reverse=False)
for i in array:
    print(i, end=' ') """