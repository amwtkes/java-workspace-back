package com.xiaojin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", "classpath:/cat-bean.xml");
        Cat myCat = (Cat) context.getBean("myCat");
        System.out.println(myCat.getAge());
    }
}
