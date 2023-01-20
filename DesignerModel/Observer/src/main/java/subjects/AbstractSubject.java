package subjects;

import observers.Observer;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();//相当于同步的ArrayList
    //方法的参数接受类型只要用observer接口就可以接收所有的实现类

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {//使用接口可以将所有Observer衍生类封装到一起
        Enumeration<Observer> enumo = vector.elements();
        while (enumo.hasMoreElements()) {
            enumo.nextElement().update();
        }
    }

    @Override
    public void operation() {

    }
}


