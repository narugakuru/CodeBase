package com.raisei.bootdemo01;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
/*

@Disabled
//@SpringBootTest
@DisplayName("Junit5功能测试")
public class Junit5TestDemo {

//    @Autowired
    JdbcTemplate jdbcTemplate;

    @DisplayName("简单断言")
    @Test
    void testSimple01(){
        int actual = 4;
        Assertions.assertEquals(4,actual,"值不相同");
        Object o = new Object();
        Assertions.assertSame(o,o,"两个对象不一样");
        Assertions.assertTrue(true,"值不为true");
    }
    @DisplayName("数组断言")
    @Test
    void testSame(){
        int[] ints = new int[]{1,3};
        Assertions.assertArrayEquals(ints,ints);
    }

    @DisplayName("组合断言")
    @Test
    void combinationAssert(){
        //必须所有条件都符合
        Assertions.assertAll("testMessage",
                () -> Assertions.assertEquals(2, 1 + 1),
                () -> Assertions.assertTrue(1 < 0)
        );
    }

    @DisplayName("异常断言")
    @Test
    void ExceptionAssert01(){
        Assertions.assertThrows(
                //捕获异常，如果没有异常则断言失败报错
                ArithmeticException.class, () -> System.out.println(1 / 1),"居然正常"
        );
    }

    @DisplayName("快速失败")
    @Test
    void fail01(){
        Assertions.fail();
        System.out.println("hello");
    }

    @Test
    void timeout02(){
        //超时则断言失败
        Assertions.assertTimeout(
                Duration.ofMillis(500),
                ()->Thread.sleep(1000)
        );
    }

//    @RepeatedTest(5)
    @DisplayName("第一次junit测试")
    @Test
    void test01(){
        System.out.println("hello,world!");
    }

    @Tag("first")
    @Disabled
    @DisplayName("第二次测试")
    @Test
    void test06(){
        System.out.println("方法2");
    }

    @BeforeEach
    void test02(){
        System.out.println("BeforeEach测试要开始了");
    }

    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    @Test
    void timeoutT() throws InterruptedException {
        Thread.sleep(600);
    }




    @AfterEach
    void test03(){
        System.out.println("AfterEach测试结束了");
    }

    @AfterAll
    static void test04(){
        System.out.println("=====AfterAll====");

    }

    @BeforeAll
    static void test05(){
        System.out.println("====BeforeAll====");
    }
}
*/
