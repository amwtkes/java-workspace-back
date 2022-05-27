package com.xiaojin.athome.experiments.dynamicproxy;

public class RichMan implements Person {
    @Override
    public void sayHello(String greetings) {
        String prefix = "我这么有钱，应该这么说你好：";
        System.out.println(prefix);
        System.out.println(greetings);
    }
}
