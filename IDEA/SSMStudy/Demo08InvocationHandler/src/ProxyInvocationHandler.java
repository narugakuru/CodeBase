import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    //传入需要代理的接口
    public void setTarget(Object target) {
        this.target = target;
    }

    //动态生成代理类
    public Object getProxy() {
        //生成一个代理实例
        //参数 ： 获取这个类的位置 获取需要代理的接口，获取InvocationHandler
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    //处理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是反射机制
        log(method.getName());
        Object result = method.invoke(target, args);
        return null;
    }

    private void log(String name) {
        System.out.println("方法"+name+"前置日志");
    }
}
