package StudentManager;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteStudent {
    public static void Delete(ArrayList<Student> array){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要删除的学生学号");
        String number=sc.nextLine();

        for(int i=0;i<array.size();i++){
            Student s= array.get(i);
            if(s.getNumber().equals(number)){
                array.remove(i);
            }
        }
    }
}
