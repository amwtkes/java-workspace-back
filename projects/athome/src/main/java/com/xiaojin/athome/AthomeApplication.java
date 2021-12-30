package com.xiaojin.athome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaojin.algorithm", "com.xiaojin.athome.controllers"})
public class AthomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthomeApplication.class, args);
    }

}
