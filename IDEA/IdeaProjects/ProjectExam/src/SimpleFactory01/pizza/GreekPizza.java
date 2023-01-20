package SimpleFactory01.pizza;

public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("希腊披萨 ");
        System.out.println("提供GreekPizza原材料");
    }
}
