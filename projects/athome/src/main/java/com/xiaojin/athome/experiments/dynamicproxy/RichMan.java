package com.xiaojin.athome.experiments.dynamicproxy;

import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Proxy;

public class RichMan implements Person {
    @Override
    public void sayHello(String greetings) {
        String prefix = "我这么有钱，应该这么说你好：";
        System.out.println(prefix);
        System.out.println(greetings);
    }

    public static void main(String[] args) {
        RichMan richMan = new RichMan();
        Delegator delegator = new Delegator(new Class[]{Person.class}, new Object[]{richMan});
        Person o = (Person) Proxy.newProxyInstance(richMan.getClass().getClassLoader(), richMan.getClass().getInterfaces(), delegator);
        o.sayHello("哈哈");
    }
}
