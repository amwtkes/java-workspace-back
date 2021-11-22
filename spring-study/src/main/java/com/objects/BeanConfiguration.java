package com.objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public Pet myPet() {
        return new Pet("hanhan", "xiaoKK", 3);
    }
}
