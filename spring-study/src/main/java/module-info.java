module spring.study {
    exports com.xiaojin;
    requires lombok;
    requires spring.beans; //automatic module
    requires spring.context;
}
