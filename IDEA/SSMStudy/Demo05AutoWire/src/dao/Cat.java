package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("cat")
public class Cat {

    @Value("可爱的猫猫")
    private String name;


    public Cat(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Cat() {
        System.out.println("miao~~~");
    }
}
