package oracle_test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("oracle_test.demo.mapper")
public class OracleDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OracleDemoApplication.class, args);
    }

}
