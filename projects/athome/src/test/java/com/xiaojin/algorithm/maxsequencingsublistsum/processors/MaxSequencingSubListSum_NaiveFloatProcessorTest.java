package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.SourceDataType;
import org.junit.jupiter.api.Test;
import runtime.processor.baseprocessor.ProcessorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MaxSequencingSubListSum_NaiveFloatProcessorTest {

    @Test
    void normalTest() {
        MaxSequencingContext algorithmGeneralContext = new MaxSequencingContext();
        algorithmGeneralContext.setSourceDataType(SourceDataType.FLOAT);
        algorithmGeneralContext.setInput("1.5,-12.3,3.2,-5.5,23.2,3.2,-1.4,-12.2,34.2,5.4,-7.8,1.1,-4.9");
        MaxSequencingSubListSum_NaiveFloatProcessor p = new MaxSequencingSubListSum_NaiveFloatProcessor();
        try {
            p.process(algorithmGeneralContext);
            MaxSequencingResult result = (MaxSequencingResult) algorithmGeneralContext.getProcessorResult().getResult();
            assertNotNull(result);
            assertEquals(result.getMaxValueDecimal(), new BigDecimal("52.40"));
            assertEquals(result.getLeftIndex(), 4);
            assertEquals(result.getRightIndex(), 9);
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }

    @Test
    void normalIntTest() {
        MaxSequencingContext algorithmGeneralContext = new MaxSequencingContext();
        algorithmGeneralContext.setSourceDataType(SourceDataType.INT);
        algorithmGeneralContext.setInput("1,-12,3,-5,23,3,-1,-12,34,5,-7,1,-5");
        MaxSequencingSubListSum_Naive_I_Processor p = new MaxSequencingSubListSum_Naive_I_Processor();
        try {
            p.process(algorithmGeneralContext);
            MaxSequencingResult result = (MaxSequencingResult) algorithmGeneralContext.getProcessorResult().getResult();
            assertNotNull(result);
            assertEquals(result.getMaxValueInt(), 52);
            assertEquals(result.getLeftIndex(), 4);
            assertEquals(result.getRightIndex(), 9);
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }
}
