package com.xiaojin.athome.console;

public class StringTestApp {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        String s = "xiaojin";
        String s1 = "xiao";
        String s2 = "jin";
        String s3 = s1 + s2;//s3是个对象了，跟stringbuilder类似；
        System.out.println(s.intern() == s3);

        /**
         * 1、计算机 软件 ja va xiaojin这些都是字面量，经过编译器以后会编译成字节码，存放在字节码常量池中；或者叫文件常量池
         * 2、使用String s= "string" 跟 String s = new StringBuilder("string")行为语义是不同的。虽然在类加载的时候，他们都会进入"运行时常量池"中，但是不见得都会进入到"字符串常量池"。前者主要是用于给虚拟机栈做计算使用的，后者是VM全局缓存使用的。所以所有的字面量都会进入"运行时常量池"；但是在实际被"执行引擎"执行时，结果取不同；
         * 3、对于String s = "string"——语义是它就是当做一个常量来使用，所以从运行时常量池中取出来后，就直接去"全局字符串常量池了"也就是StringTable；
         * 4、对于new出来的字符串，语义是可变的，课操作的，它是不能当做常量使用的，所以默认不会进入"全局字符串常量池"。而是在堆上另外开辟空间存储。当然，stringtable中也是引用，具体的字符串还是在堆上存着的。直到调用intern()方法；
         * 5、intern()方法，语义是，将一个字符串放入全局字符串常量池，如果存在则返回引用；如果不存在则分配并将引用存在stringtable中。
         * https://cloud.tencent.com/developer/article/1450501
         *
         * 6、class常量池是编译后的常量池，运行时常量池是加载后准备计算的常量池——还包括符号表，行号，方法名等信息（一个线程一个把）；字符串常量池就是全局的。
         */
    }
}
