package com.xiaojin.Test;

public class StringTest {
    public static void main(String[] args) {
        String s = "hello world";
        String ss = s;
        s = "hh";
        System.out.println(s.hashCode());
        System.out.println(ss.hashCode());
        System.out.println(s);// hh
        System.out.println(ss); // hello world
    }
}
