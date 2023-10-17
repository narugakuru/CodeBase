package StudentManager;

import java.util.ArrayList;

public class ViewStudent {
    public static void View(ArrayList<Student> array){

        System.out.println("number\tname\tage");
        for(int i=0;i<array.size();i++){
            Student s = array.get(i);
            System.out.println(s.getNumber()+"\t"+s.getName()+"\t"+s.getAge());
        }

    }
}
