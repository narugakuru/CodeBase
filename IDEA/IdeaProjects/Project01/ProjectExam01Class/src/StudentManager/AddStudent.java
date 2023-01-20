package StudentManager;

import java.util.ArrayList;
import java.util.Scanner;

public class AddStudent {
    public static void Add(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
//input
        System.out.println("请输入number:");
        String number = sc.nextLine();
        System.out.println("请输入name:");
        String name = sc.nextLine();
        System.out.println("请输入age；");
        String age = sc.nextLine();
//填入学生信息
        Student s = new Student();
        s.setNumber(number);
        s.setName(name);
        s.setAge(age);
//数组添加到集合
        array.add(s);
//提示
        System.out.println("添加学生信息成功");
    }
}
