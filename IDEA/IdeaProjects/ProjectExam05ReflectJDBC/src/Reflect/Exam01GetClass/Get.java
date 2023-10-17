package Reflect.Exam01GetClass;

//3种获取Class类的对象的方法
public class Get {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Student> studentClass = Student.class;
        System.out.println(studentClass);
        System.out.println("--------------");

        Student s = new Student();
        Class<? extends Student> sClass = s.getClass();
        System.out.println(sClass == studentClass);
        System.out.println("--------------");

        Class<?> n = Class.forName("Reflect.Exam01GetClass.Student");
        System.out.println(n == studentClass);
    }

}
