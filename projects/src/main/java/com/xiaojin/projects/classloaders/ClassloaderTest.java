package com.xiaojin.projects.classloaders;

import java.lang.reflect.InvocationTargetException;

public class ClassloaderTest {
    public static void main(String[] args) {
        MyClassloader myClassloader = new MyClassloader("/Users/xiaojin/workspace/tmp/");
        try {
            Class<?> speaker = myClassloader.loadClass("com.company.tests.MySpeaker");
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
