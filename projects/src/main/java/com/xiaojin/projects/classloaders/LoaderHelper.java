package com.xiaojin.projects.classloaders;

import org.apache.logging.log4j.util.Strings;

import java.io.*;

public class LoaderHelper {
    private LoaderHelper() {
    }

    public static String getFileName(String fullPath) throws FileNotFoundException {
        if (Strings.isBlank(fullPath)) {
            throw new FileNotFoundException(fullPath);
        }
        int index = fullPath.indexOf('.');
        if (index == -1) {
            return fullPath + ".class";
        } else {
            return fullPath.replace('.', '/') + ".class";
        }
    }

    public static byte[] loadClassBytes(String libPath, String className) throws IOException {
        try {
            String fileName = getFileName(className);
            File file = new File(libPath, fileName);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int data;
                while ((data = fileInputStream.read()) != -1) {
                    byteArrayOutputStream.write(data);
                }
                byte[] classBytes = byteArrayOutputStream.toByteArray();
                fileInputStream.close();
                byteArrayOutputStream.close();
                return classBytes;
            } catch (IOException ioException) {
                throw ioException;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            throw fileNotFoundException;
        }
    }
}
