package dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("dog")
public class Dog {
    public Dog() {
        System.out.println("wang~wang~");
    }
}
