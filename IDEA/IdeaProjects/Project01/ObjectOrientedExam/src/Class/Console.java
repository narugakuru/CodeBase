package Class;

public class Console {
    public static void main(String args[]){
        try{
        String x="kodiodaze";

        int n=Integer.parseInt(args[0]);
        System.out.println(n);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("此段代码错误，数组越界");
        }catch(RuntimeException a){ }//父类放在子类后面
        finally{
            System.out.println("f**k y*u");
        }

        //throw new RuntimeException("");



    }

}
