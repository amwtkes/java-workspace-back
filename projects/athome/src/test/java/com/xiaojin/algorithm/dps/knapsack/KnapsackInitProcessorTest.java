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
@SpringBootTest(classes = {KnapsackLoaderProcessor.class, KnapsackInitProcessor.class})
class KnapsackInitProcessorTest {
    @Autowired
    private KnapsackInitProcessor knapsackInitProcessor;

    @Autowired
    private KnapsackLoaderProcessor knapsackLoaderProcessor;

    @Test
    void process() {
        try {
            KnapsackContext knapsackContext = new KnapsackContext();
            knapsackLoaderProcessor.process(knapsackContext);
            knapsackInitProcessor.process(knapsackContext);
            Assertions.assertEquals(4, knapsackContext.getItems().size());
            Assertions.assertEquals(knapsackContext.getItems().get(3).getValue(), 9);
            Assertions.assertEquals(knapsackContext.getItems().get(3).getWeight(), 7);
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }
}
