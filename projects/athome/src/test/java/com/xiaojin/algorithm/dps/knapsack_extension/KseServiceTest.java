package com.xiaojin.algorithm.dps.knapsack_extension;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {KseService.class, KseLoadProcessor.class, KseComputingProcessor.class})
class KseServiceTest {
    @Autowired
    private KseService kseService;

    @Test
    public void test() {
        List<KseContext.KseResult> run = kseService.run("knapsack_extension/1.txt");
        Assertions.assertEquals(run.get(0).getMaxValue(), 4);
        Assertions.assertEquals(run.get(1).getMaxValue(), 5);
        Assertions.assertEquals(run.get(2).getMaxValue(), 5);

        Assertions.assertIterableEquals(run.get(0).getSelectedIndex().stream().sorted().collect(Collectors.toList()), Lists.newArrayList(1, 2));
        Assertions.assertIterableEquals(run.get(1).getSelectedIndex(), Lists.newArrayList(0));
        Assertions.assertIterableEquals(run.get(2).getSelectedIndex(), Lists.newArrayList(0));
    }
}
