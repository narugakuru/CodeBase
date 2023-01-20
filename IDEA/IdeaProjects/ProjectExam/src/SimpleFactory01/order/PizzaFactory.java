package SimpleFactory01.order;

import SimpleFactory01.pizza.CanadaPizza;
import SimpleFactory01.pizza.CheesePizza;
import SimpleFactory01.pizza.GreekPizza;
import SimpleFactory01.pizza.Pizza;

public class PizzaFactory {

    public void getPizza(String orderType) {
        Pizza pizza = null;

        //判断披萨类型

        if (orderType.equals("cheese")) {
            pizza = new CheesePizza();

        } else if (orderType.equals("greek")) {
            pizza = new GreekPizza();

        } else if (orderType.equals("canada")) {
            pizza = new CanadaPizza();

        } else {
            System.out.println("无此种类，请重新输入");
        }
        //输出pizza制作过程
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        System.out.println("----------------");

    }

}
