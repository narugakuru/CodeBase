package SimpleFactory01.order;

import java.io.IOException;

public class PizzaStore {
    public static void main(String[] args) throws IOException {

        new OrderPizza(new PizzaFactory());
        System.out.println("退出程序");
    }

}
