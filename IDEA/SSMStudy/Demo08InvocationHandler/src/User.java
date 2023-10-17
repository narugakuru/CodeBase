public class User implements UserInterface {
    public void add() {
        System.out.println("增加了一个数据");
    }

    public void delete() {
        System.out.println("删除了一个数据");
    }

    public void upData() {
        System.out.println("修改了一个数据");
    }

    public void check() {
        System.out.println("查询了一个数据");
    }

    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
