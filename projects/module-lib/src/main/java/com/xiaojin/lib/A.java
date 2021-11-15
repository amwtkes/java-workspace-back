package com.xiaojin.lib;

import org.apache.logging.log4j.util.Strings;

import java.util.Locale;

public class A {
    public static void Print(String str) {
        if (Strings.isNotBlank(str)) {
            System.out.println(str);
            return;
        }
        System.out.println("empty");
    }

    public static String transformString(String str) {
        if (Strings.isBlank(str)) {
            throw new IllegalArgumentException("args is should not be null or empty!");
        }
        return str.toUpperCase(Locale.ROOT);
    }
}
