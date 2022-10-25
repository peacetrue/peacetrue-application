package com.github.peacetrue.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebFlux 应用。
 *
 * @author peace
 */
@SpringBootApplication
public class PeacetrueWebFluxApplication {

    /**
     * 程序启动入口。
     *
     * @param args 应用参数
     */
    public static void main(String[] args) {
        SpringApplication.run(PeacetrueWebFluxApplication.class, args);
    }


}
