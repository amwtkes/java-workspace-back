package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import org.junit.jupiter.api.Test;
import runtime.processor.baseprocessor.ProcessorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MaxSequencingSubList_DPProcessorTest {

    @Test
    void testDpNormal() {
        AlgorithmGeneralContext algorithmGeneralContext = new AlgorithmGeneralContext();
        algorithmGeneralContext.setInput("1.5,-12.3,3.2,-5.5,23.2,3.2,-1.4,-12.2,34.2,5.4,-7.8,1.1,-4.9");
        MaxSequencingSubList_DPProcessor p = new MaxSequencingSubList_DPProcessor();
        try {
            p.process(algorithmGeneralContext);
            MaxSequencingResult result = (MaxSequencingResult) algorithmGeneralContext.getProcessorResult().getResult();
            assertNotNull(result);
            assertEquals(result.getMaxValue(), new BigDecimal("52.40"));
            assertEquals(result.getLeftIndex(), 4);
            assertEquals(result.getRightIndex(), 9);
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }
}
