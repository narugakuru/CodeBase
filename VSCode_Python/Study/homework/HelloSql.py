# -* - coding: UTF-8 -* -
# ! C:\\Program Files\\Python3.9.1\\python.exe

# import py_compile py_compile.compile(‘hello.py’) //生成pyc字节文件
# python -O -m py_complie hello.py //优化代码

# 变量///////////////////
# python中一次新的赋值，将创建一个新的变量。即是变量的名称相同，变量的标识并不相同。用id()函数可以获取变量标识
# 也可以把全局变量放到一个专门的文件中，然后通过import来引用
# python中没有提供定义常量的保留字。可以自己定义一个常量类来实现常量的功能。

# 数据类型////////////////////////
# 整型、长整型、浮点型、布尔型、复数类型
# 1/2在python2.5之前会等于0.5，在python2.5之后会等于0。
# 不等于为!=或<> 等于用==表示
# 逻辑表达式中and表示逻辑与，or表示逻辑或，not表示逻辑非

# import os, sys
# import MySQLdb
# try:
#     conn MySQLdb.connect(host=’localhost’,user=’root’,passwd='root’,db=’address’
# except Exception,e:
#     print e
#     sys.exit()
# cursor=conn.cursor()
# sql=’insert into address(name, address) values(%s, %s)’
# value=((“zhangsan”,”haidian”),(“lisi”,”haidian”))
# try
#     cursor.executemany(sql,values)
# except Exception, e:
#     print e
# sql=”select * from address”
# cursor.execute(sql)
# data=cursor.fetchall()
# if data
#     for x in data:
#         print x[0],x[1]
# cursor.close()
# conn.close())
