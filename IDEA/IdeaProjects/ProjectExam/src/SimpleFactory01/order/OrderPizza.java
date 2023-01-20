package SimpleFactory01.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    public OrderPizza(PizzaFactory pizzaFactory) throws IOException {//指定披萨种类
        System.out.println("pizza sort：1.greek 2.cheess");

        while (true) {
            String OrderType = getType();
            if (OrderType.equals("exit")){
                break;
            }
            pizzaFactory.getPizza(OrderType);
        }

    }


    private String getType() throws IOException {//获取pizza类型

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type: ");
            String str = br.readLine();
            return str;
        } catch (IOException e) {//处理用户输入异常
            e.printStackTrace();
            return "";
        }

    }

}
