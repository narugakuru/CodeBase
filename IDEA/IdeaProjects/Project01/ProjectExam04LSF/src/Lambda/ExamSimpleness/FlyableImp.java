package Lambda.ExamSimpleness;

import Lambda.Exam02.Flyable;

public class FlyableImp implements Flyable {
    @Override
    public void fly(String s) {
        System.out.println(s);
        System.out.println(s+"is ??");
    }

}
