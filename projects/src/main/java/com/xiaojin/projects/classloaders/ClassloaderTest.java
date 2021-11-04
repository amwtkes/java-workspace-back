package com.xiaojin.projects.classloaders;

import java.lang.reflect.InvocationTargetException;

public class ClassloaderTest {

    public static final String COM_COMPANY_TESTS_MY_SPEAKER = "com.company.tests.MySpeaker";

    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName(COM_COMPANY_TESTS_MY_SPEAKER);
            System.out.println("app classloader get "+aClass.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("app classloader haven't get "+COM_COMPANY_TESTS_MY_SPEAKER);
        }
        MyClassloader myClassloader = new MyClassloader("/Users/xiaojin/workspace/tmp/");
        try {
            Class<?> speaker = myClassloader.loadClass(COM_COMPANY_TESTS_MY_SPEAKER);
            if (speaker != null) {
                try {
                    Object o = speaker.getDeclaredConstructors()[0].newInstance();
                    speaker.getDeclaredMethod("say").invoke(o);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //test app if have com.company.tests.MySpeaker
        /**
         * class loader之间load的class是不共享的，只会存在各自loader的cache中。
         */
        try {
            Class<?> aClass = Class.forName(COM_COMPANY_TESTS_MY_SPEAKER);
//            Class<?> aClass = Class.forName(COM_COMPANY_TESTS_MY_SPEAKER,false,myClassloader);
            System.out.println("app classloader get "+aClass.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("app classloader haven't get "+COM_COMPANY_TESTS_MY_SPEAKER);
        }

        MyClassloader2 myClassloader2 = new MyClassloader2("/Users/xiaojin/workspace/tmp/");
        try {
            Class<?> speaker = myClassloader2.loadClass("com.company.tests.MySpeaker");
            if (speaker != null) {
                try {
                    Object o = speaker.getDeclaredConstructors()[0].newInstance();
                    speaker.getDeclaredMethod("say").invoke(o);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
