package com.xiaojin.projects.classloaders.TestStaticField;

import java.util.concurrent.ConcurrentHashMap;

public class A {
    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(1024);

    public void addElement(String key, String value) {
        map.putIfAbsent(key, value);
    }

    public String find(String key) {
        return map.getOrDefault(key, "NUll");
    }

}
