package com.xiaojin.athome.myspring.objects;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {
    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Pet myPet() {
        Pet pet =  new Pet();
        pet.setAge(3);
        pet.setName("xiaoKK");
        pet.setNickName("hanhan");
        return pet;
    }
}
