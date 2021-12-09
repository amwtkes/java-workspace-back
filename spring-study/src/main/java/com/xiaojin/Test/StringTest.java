package com.xiaojin.Test;

public class StringTest {
    public static void main(String[] args) {
//        String s = "hello world";
//        String ss = s;
//        s = "hh";
//        System.out.println(s.hashCode());
//        System.out.println(ss.hashCode());
//        System.out.println(s);// hh
//        System.out.println(ss); // hello world
//
//
//        String sInter = "hello world";
//        System.out.println(sInter.hashCode());


        /**
         * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
         *
         * 在第一段代码中，先看 s3和s4字符串。String s3 = new String("1") + new String("1");，这句代码中现在生成了2最终个对象，是字符串常量池中的“1” 和 JAVA Heap 中的 s3引用指向的对象。中间还有2个匿名的new String("1")我们不去讨论它们。此时s3引用对象内容是”11”，但此时常量池中是没有 “11”对象的。
         * 接下来s3.intern();这一句代码，是将 s3中的“11”字符串放入 String 常量池中，因为此时常量池中不存在“11”字符串，因此常规做法是跟 jdk6 图中表示的那样，在常量池中生成一个 “11” 的对象，关键点是 jdk7 中常量池不在 Perm 区域了，这块做了调整。常量池中不需要再存储一份对象了，可以直接存储堆中的引用。这份引用指向 s3 引用的对象。 也就是说引用地址是相同的。
         * 最后String s4 = "11"; 这句代码中”11”是显示声明的，因此会直接去常量池中创建，创建的时候发现已经有这个对象了，此时也就是指向 s3 引用对象的一个引用。所以 s4 引用就指向和 s3 一样了。因此最后的比较 s3 == s4 是 true。
         *
         * 再看 s 和 s2 对象。 String s = new String("1"); 第一句代码，生成了2个对象。常量池中的“1” 和 JAVA Heap 中的字符串对象。s.intern(); 这一句是 s 对象去常量池中寻找后发现 “1” 已经在常量池里了。
         *
         * 接下来String s2 = "1"; 这句代码是生成一个 s2的引用指向常量池中的“1”对象。 结果就是 s 和 s2 的引用地址明显不同。图中画的很清晰。
         */
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
