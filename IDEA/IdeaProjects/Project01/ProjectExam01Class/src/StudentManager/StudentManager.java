package StudentManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    public static void MainInterface(){
        ArrayList<Student> ArrayStudent=new ArrayList<Student>();
        while (true) {
            System.out.println("---欢迎使用学生管理系统---");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查看所有学生");
            System.out.println("5.退出");
            System.out.println("请选择操作：");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();

            switch (line) {
                case "1":
                    System.out.println("添加学生");
                    AddStudent.Add(ArrayStudent);
                    break;
                case "2":
                    System.out.println("删除学生");
                    DeleteStudent.Delete(ArrayStudent);
                    break;
                case "3":
                    System.out.println("修改学生");
                    ChangeStudent.Change(ArrayStudent);
                    break;
                case "4":
                    System.out.println("查看所有学生");
                    ViewStudent.View(ArrayStudent);
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    System.exit(0);

            }
        }
    }

    public static void main(String[] args){
        MainInterface();

    }

}
