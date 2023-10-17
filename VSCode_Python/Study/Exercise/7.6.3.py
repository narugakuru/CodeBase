class Student:
    def __init__(self, name, age, score=[70, 90, 100]) -> None:
        self.__name = name
        self.age = age
        self.socre = score

    def get_name(self):
        return self.__name

    def set_name(self, name):
        self.__name = name

    def get_age(self):
        return self.age

    def get_course(self):
        return max(self.socre)


def main():
    stu = Student("dio", "100")
    print(stu.get_course())
    print(stu.get_name())


main()
