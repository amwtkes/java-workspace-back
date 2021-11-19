package com.xiaojin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /*
          可以加上classpath:/
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", "classpath:/cat-bean.xml");
        Cat myCat = (Cat) context.getBean("myCat");
        System.out.println(myCat.getAge());
        System.out.println(myCat.getNickName());

        Person person = (Person) context.getBean("myPerson");
        System.out.println(person.getAge());
        System.out.println(person.getName());

        /**
         * exception thrown
         */
//        ApplicationContext context2 = new ClassPathXmlApplicationContext("classpath:/cat-bean.xml");
//        Cat myCat2 = (Cat) context2.getBean("myCat");
//        System.out.println(myCat2.getAge());
//        System.out.println(myCat2.getNickName());
//
//        Person person2 = (Person) context2.getBean("myPerson");
//        System.out.println(person2.getAge());
//        System.out.println(person2.getName());
    }
}
