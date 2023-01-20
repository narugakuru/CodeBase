class course:
    def __init__(self, id, cName, Teacher, place) -> None:
        self.id = id
        self.cName = cName
        self.Teacher = Teacher
        self.__place = place

    def toString(self):
        print(self.id, self.cName, self.Teacher, self.__place)


if __name__ == "__main__":
    c = course(341, "java", "dio", "e")
    c.toString()

