package com.xiaojin.athome.experiments.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Delegator implements InvocationHandler {

    // preloaded Method objects for the methods in java.lang.Object
    private static final Method hashCodeMethod;
    private static final Method equalsMethod;
    private static final Method toStringMethod;

    static {
        try {
            hashCodeMethod = Object.class.getMethod("hashCode", null);
            equalsMethod =
                    Object.class.getMethod("equals", Object.class);
            toStringMethod = Object.class.getMethod("toString", null);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    private final Class[] interfaces;
    private final Object[] delegates;

    public Delegator(Class[] interfaces, Object[] delegates) {
        this.interfaces = interfaces.clone();
        this.delegates = delegates.clone();
    }

    /**
     * 代理类所有的方法调用都会到这里来。
     * @param proxy 代理类实例
     * @param m 使用代理类调用的方法
     * @param args 参数
     * @return 返回值
     * @throws Throwable 可能调到非注册接口的方法。
     */
    @Override
    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable {
        Class declaringClass = m.getDeclaringClass();

        if (declaringClass == Object.class) {
            if (m.equals(hashCodeMethod)) {
                return proxyHashCode(proxy);
            } else if (m.equals(equalsMethod)) {
                return proxyEquals(proxy, args[0]);
            } else if (m.equals(toStringMethod)) {
                return proxyToString(proxy);
            } else {
                throw new InternalError(
                        "unexpected Object method dispatched: " + m);
            }
        } else {
            for (int i = 0; i < interfaces.length; i++) {
                if (declaringClass.isAssignableFrom(interfaces[i])) {
                    try {
                        System.out.println(">>>>>>>>>>>>>");
                        Object invoke = m.invoke(delegates[i], args);
                        System.out.println("<<<<<<<<<<<<<");
                        return invoke;
                    } catch (InvocationTargetException e) {
                        throw e.getTargetException();
                    }
                }
            }

            return invokeNotDelegated(proxy, m, args);
        }
    }

    protected Object invokeNotDelegated(Object proxy, Method m,
                                        Object[] args)
            throws Throwable {
        throw new InternalError("unexpected method dispatched: " + m);
    }

    protected Integer proxyHashCode(Object proxy) {
        return System.identityHashCode(proxy);
    }

    protected Boolean proxyEquals(Object proxy, Object other) {
        return (proxy == other ? Boolean.TRUE : Boolean.FALSE);
    }

    protected String proxyToString(Object proxy) {
        return proxy.getClass().getName() + '@' +
                Integer.toHexString(proxy.hashCode());
    }
}
