package Lambda.Exam05对象方法;

public class PrintString implements Printer {
    public void PrintUpper(String s){
        System.out.println(s.toUpperCase());
    }

    @Override
    public void PrintUpperCase(String s) {
        System.out.println(s.toUpperCase()+"zz");
    }
}
