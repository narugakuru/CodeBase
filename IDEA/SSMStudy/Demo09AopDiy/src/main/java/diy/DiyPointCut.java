package diy;

public class DiyPointCut {
    public void beforeLog(){
        System.out.println("=======方法执行前=========");
    }

    public void afterLog() {
        System.out.println("=======方法执行后=========");
    }
}
