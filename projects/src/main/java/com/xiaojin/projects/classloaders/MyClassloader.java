package com.xiaojin.projects.classloaders;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import static com.xiaojin.projects.classloaders.LoaderHelper.loadClassBytes;

@Getter
@Setter
public class MyClassloader extends ClassLoader {
    private String libPath;

    public MyClassloader(String path) {
        this.libPath = path;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String className) {
        byte[] bytes = loadClassBytes(this.libPath, className);
        return defineClass(className, bytes, 0, bytes.length);
    }
}
