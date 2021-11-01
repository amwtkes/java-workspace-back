package com.xiaojin.projects.classloaders;

import lombok.SneakyThrows;

import static com.xiaojin.projects.classloaders.LoaderHelper.loadClassBytes;

public class MyClassloader2 extends ClassLoader {
    private String libPath;

    public MyClassloader2(String path) {
        this.libPath = path;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String className) {
        byte[] bytes = loadClassBytes(this.libPath, className);
        return defineClass(className, bytes, 0, bytes.length);
    }
}
