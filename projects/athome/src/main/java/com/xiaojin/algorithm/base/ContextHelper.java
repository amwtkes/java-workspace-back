package com.xiaojin.algorithm.base;

import runtime.processor.baseprocessor.ProcessorException;

import java.math.BigDecimal;
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
}
