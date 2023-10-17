public class Client {
    public static void main(String[] args) {
        //真实角色
        User user = new User();
        //代理角色：现在没有
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //传入需要调用的类,类实现了接口
        pih.setTarget(user);

        //获取自动生成的代理类
        UserInterface proxy = (UserInterface) pih.getProxy();
        //代理类通过invoke方法调用
        proxy.add();
    }
}
