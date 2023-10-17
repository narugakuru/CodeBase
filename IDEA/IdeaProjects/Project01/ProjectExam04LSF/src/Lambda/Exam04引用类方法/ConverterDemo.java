package Lambda.Exam04引用类方法;

public class ConverterDemo {
    public static void main(String[] args) {
        useConverter(new Converter() {
            @Override
            public int converter(String s) {
                return Integer.parseInt(s);
            }
        });
//        useConverter(s-> Integer.parseInt(s));
//lambda被类方法引用替代时，形参自动全部传递给静态方法！！！！
        useConverter(Integer::parseInt);
    }
    private static void useConverter(Converter c){
        System.out.println(c.converter("666"));
    }

}
