package diy10;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect//标注这是一个切面类
public class DiyLog {
    //    定义切入点pointCut,
    //    execution指定要切入的方法
    @Before("execution(* service10.*.*(..))")
    public void BeforeLog() {
        System.out.println("=====方法执行前=====");
    }

    @After("execution(* service10.*.*(..))")
    public void AfterLog() {
        System.out.println("=====方法执行后=====");
    }

    @AfterReturning("execution(* service10.*.*(..))")
    public void afterReturningLog() {
        System.out.println("返回值时");
    }


    //    环绕增强，给定一个参数，代表要获取处理切入的点
    @Around("execution(* service10.*.*(..))")
    public void aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
//
        Object proceed = joinPoint.proceed();


    }


}
