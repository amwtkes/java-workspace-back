open module spring.study {
    exports com.xiaojin;
    exports com.objects;
    requires lombok;
    requires spring.beans; //automatic module
    requires spring.context;
    requires spring.core;
}
