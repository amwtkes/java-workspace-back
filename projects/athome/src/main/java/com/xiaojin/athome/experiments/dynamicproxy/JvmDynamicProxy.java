package com.xiaojin.athome.experiments.dynamicproxy;

import java.lang.reflect.Proxy;

public class JvmDynamicProxy {
    public static void main(String[] args) {
        RichMan richMan = new RichMan();
        Delegator delegator = new Delegator(new Class[]{Person.class}, new Object[]{richMan});

        /*
         *
         * new ProxyInstance最终会调用到
         * NativeConstructorAccessorImpl
         * 里面的newInstance.
         * return newInstance0(c, args);
         * 实际到了jvm的native方法。
         */
        Person o = (Person) Proxy.newProxyInstance(richMan.getClass().getClassLoader(), richMan.getClass().getInterfaces(), delegator);
        o.sayHello("哈哈");
    }
}
