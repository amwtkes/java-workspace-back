package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.SourceDataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Source;

import static org.junit.jupiter.api.Assertions.*;

class MaxSequencingSetUpProcessorTest {

    @Test
    void testGetSourceType() {
        MaxSequencingSetUpProcessor maxSequencingSetUpProcessor = new MaxSequencingSetUpProcessor();
        MaxSequencingContext algorithmGeneralContext = new MaxSequencingContext();
        algorithmGeneralContext.setInput("1.5,-12.3,3.2,-5.5,23.2,3.2,-1.4,-12.2,34.2,5.4,-7.8,1.1,-4.9");
//        algorithmGeneralContext.setInput("1,-12,3,-5,23,3,-1,-12,34,5,-7,1,-5");
        Assertions.assertEquals(SourceDataType.FLOAT, maxSequencingSetUpProcessor.getSourceType(algorithmGeneralContext));
    }
}
