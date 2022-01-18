package com.xiaojin.algorithm.knapsack;

import com.xiaojin.algorithm.knapsack.base.KnapsackContext;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

import static org.junit.jupiter.api.Assertions.*;

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
            Assertions.assertEquals(5, knapsackContext.getItems().size());
            Assertions.assertEquals(knapsackContext.getItems().get(3).getValue(), 9);
            Assertions.assertEquals(knapsackContext.getItems().get(3).getWeight(), 7);
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }
}
