package Lambda.Exam05对象方法;

public class PrintDemo {
    public static void main(String[] args) {
/*        usePrint((String s)->{
            System.out.println(s.toUpperCase());
        });*/

//        usePrint(new PrintString());
//        usePrint(s-> System.out.println(s.toUpperCase()));

        Printer ps=new PrintString();
//        调用PrinterString的方法，形参自动传递
        usePrint(ps::PrintUpperCase);
    }

    private static void usePrint(Printer p){
        p.PrintUpperCase("hello world");
    }

}
