package ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayStudent {

    public static class Student {
        private String name;
        private int age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Student() {

        }
        @Override
        public String toString() {
            return "ArrayTraversal{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }
    public static void AddStudent(ArrayList<Student> arrays){
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入name：");
        String name=sc.nextLine();

        System.out.println("请输入age；");
        int age=sc.nextInt();

        Student student=new Student();
        student.setName(name);
        student.setAge(age);

        arrays.add(student);

}

    public static void main(String[] args) {
        ArrayList<Student> array =new ArrayList<>();

        for(int i=0;i<3;i++){
            AddStudent(array);
        }
        for(int j=0;j<array.size();j++){
            Student s =array.get(j);
            System.out.println(s.toString());
        }
    }


}
