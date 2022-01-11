package com.xiaojin.algorithm.p_recursive.race_n;

import com.xiaojin.algorithm.p_recursive.base.ClimbStairsContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import runtime.processor.baseprocessor.ProcessorException;

import static org.junit.jupiter.api.Assertions.*;

class ComputationProcessorTest {

    @Test
    void testProcess() {
        ClimbStairsContext climbStairsContext = new ClimbStairsContext();
        climbStairsContext.setClimbSpan(2);
        climbStairsContext.setStairsNumber(20);
        ComputationProcessor computationProcessor = new ComputationProcessor();
        try {
            computationProcessor.process(climbStairsContext);
            Assertions.assertEquals(10946, climbStairsContext.getResult2());
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }
}
