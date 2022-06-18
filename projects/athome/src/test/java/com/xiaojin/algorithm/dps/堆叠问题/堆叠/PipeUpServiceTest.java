package com.xiaojin.algorithm.dps.堆叠问题.堆叠;

import com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors.PipeUpEndWayProcessor;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors.PipeUpGreedyProcessor;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors.PipeUpNaiveProcessor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.NAIVE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PipeUpService.class, PipeUpNaiveProcessor.class, PipeUpEndWayProcessor.class, PipeUpGreedyProcessor.class})
class PipeUpServiceTest {
    @Autowired
    private PipeUpService pipeUpService;

    @Test
    void testNaive() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 3, 5, 7, 9, 11, 21);
        int run = pipeUpService.run(integers, NAIVE);
        Assertions.assertEquals(run, 5);
    }
}
