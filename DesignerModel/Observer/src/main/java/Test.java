import observers.Observer1;
import observers.Observer2;
import subjects.MySubject;
import subjects.Subject;

public class Test {
    public static void main(String[] args) {
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }
}
