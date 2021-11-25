package com.xiaojin;

import com.factorybean.MyObjectConfiguration;
import com.objects.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /*
          可以加上classpath:/
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", "classpath:/cat-bean.xml");
        Cat myCat = (Cat) context.getBean("myCat");
        Cat myCat2 = (Cat) context.getBean("myCat");
        if (myCat2.equals(myCat2)) {
            System.out.println("ok");
        }
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
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.objects");
        Pet myPet = (Pet) annotationConfigApplicationContext.getBean("myPet");
        Pet myPet2 = (Pet) annotationConfigApplicationContext.getBean("myPet");
        if (myPet == myPet2) {
            System.out.println("two pets are equal!");
        } else {
            System.out.println("two pets are not equal!");
        }
        System.out.println(myPet.getName() + " " + myPet.getAge() + " " + myPet.getNickName());

        //FactoryBean Example
        AnnotationConfigApplicationContext anoContext = new AnnotationConfigApplicationContext(MyObjectConfiguration.class);
        //获取bean
        System.out.println(anoContext.getBean("getMyObjectFactoryBean"));
        //获取bean instance。https://juejin.cn/post/6960304586124967973
        System.out.println(anoContext.getBean("&getMyObjectFactoryBean"));

        //application context aware接口
        System.out.println(myPet.getApplicationContext());
        System.out.println(myPet.getEnvironment());
    }
}
