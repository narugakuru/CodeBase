package StudentManager;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ChangeStudent {

    public static void Change(ArrayList<Student> array){
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入要修改的学生的学号：");
        String number=sc.nextLine();

        System.out.println("请输入学生的新name：");
        String name =sc.nextLine();
        System.out.println("请输入学生的新age：");
        String age =sc.nextLine();

        Student s=new Student();
        s.setNumber(number);
        s.setName(name);
        s.setAge(age);

        for(int i=0;i< array.size();i++){
            Student s1= array.get(i);
            if(s1.getNumber().equals(number)){
                array.set(i,s);
            }
        }
    }

}
