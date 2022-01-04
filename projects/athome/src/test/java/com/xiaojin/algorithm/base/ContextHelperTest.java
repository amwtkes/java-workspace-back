package com.xiaojin.algorithm.base;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;

@DisplayName("测试Context的转换功能")
public class ContextHelperTest {
    @Test
    public void shouldReturnIntegerListWhenSourceIsInteger() {
        String input = "1,2,3,4,5,6";
        AlgorithmGeneralContext algorithmGeneralContext = new AlgorithmGeneralContext();
        algorithmGeneralContext.setInput(input);
        try {
            ArrayList<Integer> splitter = ContextHelper.splitter(algorithmGeneralContext, Integer.MAX_VALUE);
            Assertions.assertArrayEquals(splitter.toArray(), new Integer[]{1, 2, 3, 4, 5, 6});
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnIntegerListWhenSourceIsFloat() {
        String input = "1.1,2.2,3.23,4.333,5.444,6.222";
        AlgorithmGeneralContext algorithmGeneralContext = new AlgorithmGeneralContext();
        algorithmGeneralContext.setInput(input);
        try {
            ArrayList<Float> splitter = ContextHelper.splitter(algorithmGeneralContext, Float.MAX_VALUE);
            Assertions.assertArrayEquals(splitter.toArray(), new Float[]{1.1f, 2.2f, 3.23f, 4.333f, 5.444f, 6.222f});
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnIntegerListWhenSourceIsDouble() {
        String input = "1.1,2.2,3.23,4.333,5.444,6.222";
        AlgorithmGeneralContext algorithmGeneralContext = new AlgorithmGeneralContext();
        algorithmGeneralContext.setInput(input);
        try {
            ArrayList<Double> splitter = ContextHelper.splitter(algorithmGeneralContext, Double.MAX_VALUE);
            Assertions.assertArrayEquals(splitter.toArray(), new Double[]{1.1d, 2.2d, 3.23d, 4.333d, 5.444d, 6.222d});
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldThrownProcessorExceptionWhenSourceIsNull() {
        String input = "";
        AlgorithmGeneralContext algorithmGeneralContext = new AlgorithmGeneralContext();
        algorithmGeneralContext.setInput(input);
        ProcessorException e = Assertions.assertThrowsExactly(ProcessorException.class, () -> ContextHelper.splitter(algorithmGeneralContext, Double.MAX_VALUE));
        Assertions.assertEquals(e.getMessage(), "ProcessorException->Input should not be null!");
    }
}
