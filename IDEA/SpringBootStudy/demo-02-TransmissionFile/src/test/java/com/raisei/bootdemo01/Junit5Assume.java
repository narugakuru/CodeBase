package com.raisei.bootdemo01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assumptions.*;

public class Junit5Assume {
    private final String environment = "DE";
//assume不满足则会停止测试
    @Test
    @DisplayName("simple")
    public void simpleAssume() {
        assumeTrue(Objects.equals(this.environment, "DEV"));
        assumeFalse(() -> Objects.equals(this.environment, "PROD"));
    }

    @Test
    @DisplayName("assume then do")
    public void assumeThenDo() {
        assumingThat(
                Objects.equals(this.environment, "DEV"),
                () -> System.out.println("In DEV")
        );
    }

    String re(){
        return "junit";
    }
}
