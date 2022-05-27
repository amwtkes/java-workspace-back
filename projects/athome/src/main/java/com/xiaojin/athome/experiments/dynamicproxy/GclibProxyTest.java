package com.xiaojin.athome.experiments.dynamicproxy;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GclibProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
//        enhancer.setCallback((FixedValue) () -> 111);
        enhancer.setCallback((MethodInterceptor) (obj, method, as, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                if (method.getName().equals("sayHello")) {
                    return "ffffff";
                } else {
                    return proxy.invokeSuper(obj, as);
                }
            } else {
                return proxy.invokeSuper(obj, as);
            }
        });
        PersonService proxy = (PersonService) enhancer.create();

        System.out.println(proxy.sayHello(null));
        System.out.println(proxy.lengthOfName("null").toString());

        /////////////////////////////
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");

        Method getter = myBean.getClass().getMethod("getName");
        System.out.println(getter.invoke(myBean));
    }
}
