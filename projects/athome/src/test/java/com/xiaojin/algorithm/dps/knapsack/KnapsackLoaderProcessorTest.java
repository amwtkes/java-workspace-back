package com.xiaojin.algorithm.dps.knapsack;

import com.xiaojin.algorithm.dps.knapsack.base.KnapsackContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KnapsackLoaderProcessor.class})
class KnapsackLoaderProcessorTest {
    @Autowired
    private KnapsackLoaderProcessor knapsackLoaderProcessor;

    @Test
    void testLoader() {
        KnapsackContext knapsackContext = new KnapsackContext();
        try {
            knapsackLoaderProcessor.process(knapsackContext);
            Assertions.assertEquals("1-2,3-3,5-4,9-7", knapsackContext.getInput());
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }
}
