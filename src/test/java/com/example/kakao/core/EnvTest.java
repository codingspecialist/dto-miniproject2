package com.example.kakao.core;

import org.junit.jupiter.api.Test;

public class EnvTest {
    @Test
    public void env_test1() {
        String home = System.getenv("JAVA_HOME");
        System.out.println(home);

    }
}
