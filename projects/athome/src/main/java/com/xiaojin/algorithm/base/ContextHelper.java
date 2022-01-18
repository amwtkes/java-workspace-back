package com.xiaojin.algorithm.base;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ContextHelper {
    public static <T> ArrayList<T> splitter(AlgorithmGeneralContext algorithmGeneralContext, T t) throws ProcessorException {
        if (algorithmGeneralContext == null) {
            throw new ProcessorException("Context can't be null!");
        }
        algorithmGeneralContext.assertInputNotBeNull();
        String[] elements = algorithmGeneralContext.getInput().split(",");
        if (elements.length == 0) {
            throw new ProcessorException("Context input has no element!");
        }

        return innerSplitter(elements, t);
    }

    private static <T> ArrayList<T> innerSplitter(String[] arrays, T t) throws ProcessorException {
        ArrayList<T> ret = new ArrayList<>(arrays.length);
        for (String e : arrays) {
            if (t instanceof Integer) {
                ret.add((T) Integer.valueOf(e));
            } else if (t instanceof Float) {
                ret.add((T) Float.valueOf(e));
            } else if (t instanceof BigDecimal) {
                ret.add((T) new BigDecimal(e));
            } else if (t instanceof Double) {
                ret.add((T) Double.valueOf(e));
            } else {
                throw new ProcessorException("Type not supported by Splitter!");
            }
        }
        return ret;
    }

    public static BigDecimal round(int count, Float value) {
        BigDecimal maxValue = new BigDecimal(value);
        maxValue = maxValue.setScale(count, RoundingMode.HALF_UP);
        return maxValue;
    }

    public static BigDecimal round2(Float value) {
        BigDecimal maxValue = new BigDecimal(value);
        maxValue = maxValue.setScale(2, RoundingMode.HALF_UP);
        return maxValue;
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getInputStringFromClassPathFile(ResourceLoader resourceLoader, String filePath) throws ProcessorException, IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filePath);
        BufferedReader reader = null;
        try {
            File file = resource.getFile();
            reader = new BufferedReader(new FileReader(file));
            String str;
            StringBuilder stringBuffer = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ProcessorException(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
