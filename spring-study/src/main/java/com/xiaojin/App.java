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
    }
}
