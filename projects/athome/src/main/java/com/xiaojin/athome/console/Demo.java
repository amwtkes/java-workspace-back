package com.xiaojin.athome.console;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class Demo {
    public static void main(String[] args) throws Throwable {
        Runnable runnable = () -> System.out.println("hello");
        runnable.run();

        test();

        int x = 5;
        IntStream.of(1, 2, 3).map(i -> i * 2).map(i -> i * x); // map(FunctionalInterface) 所以callsite会实例化这个FunctionalInterface实例。
    }

    private static void test() throws Throwable {
        Object x, y;
        String s;
        int i;
        MethodType mt;
        MethodHandle mh;
        MethodHandles.Lookup lookup = MethodHandles.lookup();
// mt is (char,char)String
        mt = MethodType.methodType(String.class, char.class, char.class);
        mh = lookup.findVirtual(String.class, "replace", mt);
        s = (String) mh.invokeExact("daddy", 'd', 'n');
// invokeExact(Ljava/lang/String;CC)Ljava/lang/String;
        assertEquals(s, "nanny");
// weakly typed invocation (using MHs.invoke)
        s = (String) mh.invokeWithArguments("sappy", 'p', 'v');
        assertEquals(s, "savvy");
// mt is (Object[])List
        mt = MethodType.methodType(java.util.List.class, Object[].class);
        mh = lookup.findStatic(java.util.Arrays.class, "asList", mt);
        assert (mh.isVarargsCollector());
        x = mh.invoke("one", "two");
// invoke(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        assertEquals(x, java.util.Arrays.asList("one", "two"));
// mt is (Object,Object,Object)Object
        mt = MethodType.genericMethodType(3);
        mh = mh.asType(mt);
        x = mh.invokeExact((Object) 1, (Object) 2, (Object) 3);
// invokeExact(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        assertEquals(x, java.util.Arrays.asList(1, 2, 3));
// mt is ()int
        mt = MethodType.methodType(int.class);
        mh = lookup.findVirtual(java.util.List.class, "size", mt);
        i = (int) mh.invokeExact(java.util.Arrays.asList(1, 2, 3));
// invokeExact(Ljava/util/List;)I
        assert (i == 3);
        mt = MethodType.methodType(void.class, String.class);
        mh = lookup.findVirtual(java.io.PrintStream.class, "println", mt);
        mh.invokeExact(System.out, "Hello, world.");
// invokeExact(Ljava/io/PrintStream;Ljava/lang/String;)V
    }
}
