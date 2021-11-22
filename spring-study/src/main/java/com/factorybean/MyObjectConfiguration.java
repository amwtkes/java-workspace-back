package com.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyObjectConfiguration {
    @Bean
    public MyObjectFactoryBean getMyObjectFactoryBean() {
        return new MyObjectFactoryBean();
    }
}
