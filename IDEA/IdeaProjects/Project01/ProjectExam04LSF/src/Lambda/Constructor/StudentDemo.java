package Lambda.Constructor;

//在接口中调用类构造器然后赋值给类构造器？？
public class StudentDemo {
    public static void main(String[] args) {
//        引用构造器
        useStudentBuilder(Student::new);
//        引用方法
        useStudentBuilder(((name, age) -> new Student(name,age)));
    }

    private static void useStudentBuilder(StudentsBuilder sb){
        Student s=sb.builder("啊伟",17);
        System.out.println(s.toString());
    }
}
