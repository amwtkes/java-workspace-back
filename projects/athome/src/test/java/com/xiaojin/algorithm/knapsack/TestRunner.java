package com.xiaojin.algorithm.knapsack;

import com.xiaojin.algorithm.AlgorithmProcessorLunchService;
import com.xiaojin.algorithm.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.knapsack.base.KnapsackProcessor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KnapsackLoaderProcessor.class, KnapsackInitProcessor.class, KnapsackComputationProcessor.class, KnapsackComputeResultVectorProcessor.class, AlgorithmProcessorLunchService.class})
public class TestRunner {
    @Autowired
    private List<KnapsackProcessor> processorList;
    @Autowired
    private AlgorithmProcessorLunchService lunchService;

    @Test
    public void test() {
        KnapsackContext knapsackContext = new KnapsackContext();
        knapsackContext.setKnapsackWeightLimit(10);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = lunchService.runKnapsackAlgorithm(processorList, knapsackContext);
        Assertions.assertEquals(knapsackContext.getResult2(), 12);
        int[] ret = {0, 1, 0, 1};
        for (int i = 0; i < knapsackContext.getItems().size(); i++) {
            Assertions.assertEquals(ret[i], knapsackContext.getResultVector().get(i));
        }
    }

}
