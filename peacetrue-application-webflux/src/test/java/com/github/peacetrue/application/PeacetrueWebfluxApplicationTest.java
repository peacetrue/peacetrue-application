package com.github.peacetrue.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 测试 Spring Boot Webflux 应用。
 *
 * @author peace
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PeacetrueWebfluxApplicationTest {

    @Autowired
    private PeacetrueWebfluxApplication peacetrueWebfluxApplication;

    @Test
    void basic() {
        Assertions.assertNotNull(peacetrueWebfluxApplication);
    }
}
