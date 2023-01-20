package service;

import dao.TestDIDao;

public class TestDIServiceImpl implements TestDIService {
    private TestDIDao testDIDao;

    public TestDIServiceImpl(TestDIDao testDIDao) {
        super();
        this.testDIDao = testDIDao;
    }

    @Override
    public void sayHello() {
        testDIDao.sayHello();
        System.out.println("TestDIService构造方法引入say:Hello,Study Hard!");
    }
}
