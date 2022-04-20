package com.xiaojin.athome.myspring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 可以定义一个{@link FactoryBean}来初始化一个bean实例
 */
public class MyObjectFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        System.out.println("getting object myObject.");
        return new MyObject("xiaojin", 40);
    }

    @Override
    public Class<?> getObjectType() {
        return MyObject.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
