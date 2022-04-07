package com.xiaojin.algorithm.knapsack_extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KseService.class, KseLoadProcessor.class, KseComputingProcessor.class})
class KseServiceTest {
    @Autowired
    private KseService kseService;

    @Test
    public void test() {
        List<Integer> run = kseService.run("knapsack_extension/1.txt");
        Assertions.assertEquals(run.get(0), 4);
        Assertions.assertEquals(run.get(1), 5);
        Assertions.assertEquals(run.get(2), 5);
    }
}
