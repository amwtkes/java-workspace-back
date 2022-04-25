package com.xiaojin.algorithm.base;

import org.apache.logging.log4j.util.Strings;
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
import java.util.Arrays;
import java.util.List;

public class ContextHelper {
    public static <T> ArrayList<T> splitter(AlgorithmGeneralContext algorithmGeneralContext, T t) throws ProcessorException {
        if (algorithmGeneralContext == null) {
            throw new ProcessorException("Context can't be null!");
        }
        algorithmGeneralContext.assertInputNotBeNull();
        return splitter(algorithmGeneralContext.getInput(), t);
    }

    public static <T> ArrayList<T> splitter(String str, T t) throws ProcessorException {
        String[] elements = str.split(",");
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
            } else if (t instanceof String) {
                ret.add((T) e);
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

    public static List<String> getInputStringListFromClassPathFile(ResourceLoader resourceLoader, String filePath) throws ProcessorException, IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filePath);
        BufferedReader reader = null;
        List<String> ret = new ArrayList<>(8);
        try {
            File file = resource.getFile();
            reader = new BufferedReader(new FileReader(file));
            String str;
            while ((str = reader.readLine()) != null) {
                if (Strings.isNotBlank(str)) {
                    ret.add(str);
                }
            }
            return ret;
        } catch (IOException e) {
            throw new ProcessorException(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static int[] genRandomIntArray(int length, int maxValue, boolean needNegatives) {
        int[] ints = randomArray(length, maxValue);
        int delta = needNegatives ? (int) (Math.random() * maxValue) : 0;
        return Arrays.stream(ints).map(v -> v - delta).toArray();
    }

    public static String intArrayToString(int[] array) {
        String s = Arrays.stream(array).mapToObj(v -> v + ",").reduce((a, b) -> a + b).orElse("");
        return s.substring(0, s.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(intArrayToString(genRandomIntArray(10, 50, false)));
    }
}
