# 薪资权限管理系统

#### 介绍
本文档主要针对工资管理系统的使用环境与功能提出具体的要求，同时它还将作为该产品设计与开发的重要参考依据。

#### 文档范围
本文档包含以下几部分：
1.产品介绍
2.产品面向的用户群体
3.产品应当遵循的标准或规范
4.产品的范围
5.产品中的角色
4.产品的功能性需求
5.产品的非功能性需求
6.需求确认	


#### 需求用例模型

![用例图](https://images.gitee.com/uploads/images/2021/0415/105230_fb695068_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105318_ce6ce747_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105257_37819360_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105327_248ea004_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105332_fd41f564_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105338_70b16646_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105343_ba626d79_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105351_8eb6a651_8840403.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0415/105357_727b9190_8840403.png "屏幕截图.png")
用例	用例描述	功能含义
1.0	用户管理	用于管理用户的基本信息
2.0	系统升级	对系统进行升级
3.0	系统维护	对系统进行维护
4.0	员工信息管理	对员工信息进行管理
5.0	人事调动管理	对人事部门的人员调动管理
6.0	发放工资	发放员工的工资
7.0	核算工资	对员工的工资进行核算
8.0	评价员工基本情况	对员工的情况进行评价
9.0	考察员工出勤情况	记录员工的出勤情况

### 数据实体描述
员工信息表：Employee Info
|字段名      |类型|            |是否为主键|        是否为空	        |备注|
|EmpId	     int		是	            否                   员工号
EmpName	     VARCHAR(50)	否	            否	                员工姓名
EmpSex	     VARCHAR(10)	否	            否	                员工性别
EmpStudy     VARCHAR(50)	否	            否	                员工学历
Empmarry     VARCHAR(50)	否	            否	                婚姻状况
EmpAge	     VARCHAR(50)	否	            否	                员工年龄
EmpNum	     VARCHAR(50)	否	            否	                岗位编号
DepartNum    VARCHAR(50)	否	            否	                部门编号

工资信息表：Salary Info
字段名	        类型	是否为主键	是否为空	备注
EmpId	        Int	是	否	员工号
SalaryDate	Date	否	否	工资年月
SalaryAble	Float	否	否	应发工资
BonusSalary	Float	否	否	奖励金额
DeductedSalary	Float	否	否	扣除工资
PaidWages	Float	否	否	实发工资

岗位信息表：Post Info
字段名	        类型	    是否为主键	是否为空	    备注
PostId	        Int	        是	  否	    岗位编号
PostName	VARCHAR(50)	否	  否	    岗位名称
PostNum	VARCHAR(50)	        否	  否	    部门编号

部门信息表：Department Info
字段名	        类型	    是否为主键	是否为空	备注
DepartNum	Int	        是	否	部门编号
DepartName	VARCHAR(50)	否	否	部门名称
DepartPrincipe	VARCHAR(50)	否	否	部门负责人
DepartMount	Int	        否	否	部门人数

考勤信息表：Attendance Record Info
字段名	        类型	        是否为主键	是否为空	备注
EmpNum	        Int	        是	否	员工号
FormAttend	Int	        否	否	正常出勤天数
ExceptionAttend	Int	        否	否	异常出勤天数
OverTime	Int	        否	否	加班天数
Evaluation	VARCHAR(50)	否	否	评价情况

### 系统环境设计
开发环境设计
操作系统：windows 系列
虚拟机：jdk-6-linux-i586 或jdk-6-win-i586
浏览器：FireFox 或IE6
Web 服务器：Apache Tomcat 6.0
数据库：MySql 5.5
设计工具：Microsoft Office Visio 2003 ，StarUML
IDE 工具：MyEclipse8.6（集成Eclipse3.3）