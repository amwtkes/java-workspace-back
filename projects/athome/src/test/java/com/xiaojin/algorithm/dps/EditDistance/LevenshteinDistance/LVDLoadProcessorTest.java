package com.xiaojin.algorithm.dps.EditDistance.LevenshteinDistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LVDLoadProcessor.class})
class LVDLoadProcessorTest {
    @Autowired
    private LVDLoadProcessor lvdLoadProcessor;

    @Test
    public void test() throws ProcessorException {
        LVDContext lvdContext = new LVDContext("EditDistance/1.txt");
        lvdLoadProcessor.process(lvdContext);
        Assertions.assertEquals("mitcmu", lvdContext.getFirstLine());
        Assertions.assertEquals("mtacnu", lvdContext.getSecondLine());
    }
}
