package com.raisei.test;

import org.junit.jupiter.api.Test;

public final class  lali {
    private final double pai;
    {
        pai = 3.14;
        System.out.println("初始化");
    }
    @Test
    public void test() {
        System.out.println(getAre(5.0));
    }
    public  static double getAre(Double radius){
        lali lali = new lali();
        return lali.pai*radius*radius;
    }
}
