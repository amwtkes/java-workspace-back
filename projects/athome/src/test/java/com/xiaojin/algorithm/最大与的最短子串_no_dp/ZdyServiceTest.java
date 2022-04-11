package com.xiaojin.algorithm.最大与的最短子串_no_dp;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ZdyService.class, ZdyLoadProcessor.class, ZdyManliProcessor.class, ZdyComputationProcessor.class})
class ZdyServiceTest {
    @Autowired
    private ZdyService zdyService;

    @Test
    public void test() {
        List<Integer> run = zdyService.run("zdy/1.txt", ZdyPriority.COMPUTATION_MANLI);
        Assertions.assertIterableEquals(Lists.newArrayList(1, 6), run);

        run = zdyService.run("zdy/1.txt", ZdyPriority.COMPUTATION);
        Assertions.assertIterableEquals(Lists.newArrayList(1, 6), run);
    }

    @Test
    public void test2() {
        List<Integer> run = zdyService.run("zdy/2.txt", ZdyPriority.COMPUTATION_MANLI);
        Assertions.assertIterableEquals(Lists.newArrayList(1, 6), run);

        run = zdyService.run("zdy/2.txt", ZdyPriority.COMPUTATION);
        Assertions.assertIterableEquals(Lists.newArrayList(1, 6), run);
    }
}
