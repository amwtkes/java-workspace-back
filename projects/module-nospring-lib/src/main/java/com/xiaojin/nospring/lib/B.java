package com.xiaojin.nospring.lib;

import java.util.Locale;

public class B {
    public static String toUpperCase(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("str should not be null or empty!");
        }
        return str.toUpperCase(Locale.ROOT);
    }
}
