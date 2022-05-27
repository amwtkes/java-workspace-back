package com.xiaojin.athome.experiments.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class GclibProxyTest {
    public static void main(String[] args) {
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
    }
}
