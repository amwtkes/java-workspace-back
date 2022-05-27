package com.xiaojin.athome.experiments.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;

public class GclibProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "ffffff");
        PersonService proxy = (PersonService) enhancer.create();

        String res = proxy.sayHello(null);
        System.out.println(res);
    }
}
