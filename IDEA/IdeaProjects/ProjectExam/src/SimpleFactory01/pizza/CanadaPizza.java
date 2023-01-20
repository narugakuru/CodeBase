package SimpleFactory01.pizza;

public class CanadaPizza extends Pizza{
    @Override
    public void prepare() {

        setName("加拿大披萨 ");
        System.out.println("提供CanadaPizza原材料");
    }
}
