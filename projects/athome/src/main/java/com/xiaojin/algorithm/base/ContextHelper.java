package com.xiaojin.algorithm.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
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

    public static int[] randomArray(int len, int maxValue, boolean needNegatives) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = randomInt(maxValue, needNegatives);
        }
        return arr;
    }

    public static ArrayList<Integer> toList(int[] array) {
        ArrayList<Integer> ret = new ArrayList<>(array.length);
        for (int j : array) {
            ret.add(j);
        }
        return ret;
    }

    public static String intArrayToString(int[] array) {
        String s = Arrays.stream(array).mapToObj(v -> v + ",").reduce((a, b) -> a + b).orElse("");
        return s.substring(0, s.length() - 1);
    }

    public static String intArrayToString(List<Integer> array) {
        String s = array.stream().map(v -> v + ",").reduce((a, b) -> a + b).orElse("");
        return s.substring(0, s.length() - 1);
    }

    public static int randomInt(int maxValue, boolean needNegatives) {
        int delta = needNegatives ? (int) (Math.random() * maxValue) : 0;
        return (int) (Math.random() * maxValue) - delta;
    }

    public static int randomInt(int minValue, int maxValue, boolean needNegatives) {
        int delta = needNegatives ? (int) (Math.random() * maxValue) : 0;
        int i = (int) (Math.random() * maxValue);
        while (i < minValue) {
            i = (int) (Math.random() * maxValue);
        }
        return i - delta;
    }

    /**
     * 二分搜索
     *
     * @param array 目标数组，必须是从小到大排列的
     * @param value 要搜索的值
     * @return 返回索引值,-1表示没有找到.
     */
    public static BinarySearchResult binarySearch(ArrayList<Integer> array, int value) throws ProcessorException {
        if (array == null || array.size() == 0) {
            throw new ProcessorException("array is empty!");
        }
        int begin = 0;
        int end = array.size() - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (array.get(mid) == value) {
                return new BinarySearchResult(array, mid, end, begin, value);
            }
            if (array.get(mid) > value) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return new BinarySearchResult(array, -1, end, begin, value);
    }

    public enum BinarySearchResultType {
        NO_BIGGER,
        NO_LESSER,
        NORMAL_MISS,
        OK
    }

    @Getter
    @AllArgsConstructor
    public static class BinarySearchResult {
        private ArrayList<Integer> items;
        private int resultIndex;
        private int end;
        private int begin;
        private int searchValue;

        public BinarySearchResultType getResultType() {
            if (resultIndex != -1) {
                return BinarySearchResultType.OK;
            }
            if (begin == items.size()) {
                return BinarySearchResultType.NO_BIGGER;
            }
            if (end == -1) {
                return BinarySearchResultType.NO_LESSER;
            }
            return BinarySearchResultType.NORMAL_MISS;
        }

        /**
         * 获取小于目标值得最大元素索引号。如果有相等的，则返回相等元素的索引值。
         *
         * @return -1表示没有比目标值更小的元素。
         */
        public int getLastLessOneIndex() {
            BinarySearchResultType resultType = getResultType();
            switch (resultType) {
                case OK:
                    int preResultIndex = resultIndex - 1;
                    while (preResultIndex >= 0) {
                        if (items.get(preResultIndex) < searchValue) {
                            return preResultIndex;
                        }
                        preResultIndex--;
                    }
                    return -1;
                case NORMAL_MISS:
                    return end;
                default:
                    return -1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(intArrayToString(randomArray(10, 50, true)));
    }

}
