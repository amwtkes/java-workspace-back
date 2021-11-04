package com.xiaojin.projects.classloaders.TestStaticField;

public class B extends A {
    public void display(String key) {
        System.out.println(find(key));
    }

    public static void main(String[] args) {
        A a = new A();
        a.addElement("1", "1");
        a.addElement("2", "2");
        System.out.println(a.find("xiao"));
        B b = new B();
        b.display("1");
        b.addElement("xiao", "jin");
        System.out.println(a.find("xiao"));
    }
}
