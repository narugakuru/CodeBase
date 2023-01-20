class Person:
    def __init__(self, name, age, gender):
        self.__name = name
        self.__age = age
        self.__gender = gender

    def personlnfo(self, end=" "):
        print(self.__name, self.__age, self.__gender, end=end)

    def get_name(self):
        return self.__name

    def get_age(self):
        return self.__age

    def get_gender(self):
        return self.__gender


class Student(Person):
    def __init__(self, name, age, gender, college, banji):
        super().__init__(name, age, gender)
        self.__college = college
        self.__banji = banji

    def personlnfo(self):
        super().personlnfo()
        print(self.__college, self.__banji)

    def study(self, teacher):
        print("我是{}，老师{}，我终于学会了!".format(super().get_name(), teacher.teacher()))


class Teacher(Person):
    def __init__(self, name, age, gender, college, professional):
        super().__init__(name, age, gender)
        self.__college = college
        self.__professional = professional

    def personlnfo(self):
        super().personlnfo()
        print(self.__college, self.__professional)

    def teacher(self):
        return "今天讲了面向对象"


p1 = Student("张三", 19, "男", "软件学院", "软件1908")
p2 = Student("李四", 19, "女", "软件学院", "软件1908")
p3 = Student("王二", 20, "男", "软件学院", "软件1908")
q = Teacher("刘老师", 30, "女", "软件学院", "计算机车软件与理论")
q.personlnfo()
p1.study(q)
ls = []
ls.append(p1)
ls.append(p2)
ls.append(p3)
for value in ls:
    value.personlnfo()
