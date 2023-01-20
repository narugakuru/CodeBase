package Lambda.ExamSimpleness;

import Lambda.Exam03.Addable;

public class AddableImp implements Addable {
    @Override
    public int add(int x, int y) {
        return y-x;
    }

/*    public int out(int z) {
        return z;
    }*/
}
