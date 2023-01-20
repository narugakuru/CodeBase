package SimpleFactory01.pizza;

public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("奶酪披萨");
        System.out.println("提供CheessPizza原材料");
    }
}
