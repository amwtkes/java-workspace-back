package com.xiaojin.algorithm.dps.堆叠问题.堆叠;

import com.xiaojin.algorithm.base.ContextHelper;
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

import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.END;
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

    @Test
    void testEnd() {
        ArrayList<Integer> integers = Lists.newArrayList(560, 410, 974, 739, 858, 744, 399, 418, 358, 330, 379, 709, 646, 490, 9);
        int run = pipeUpService.run(integers, END);
        int naiveRun = pipeUpService.run(integers, NAIVE);
        Assertions.assertEquals(run, naiveRun);
    }

    @Test
    void testEnd2() {
        ArrayList<Integer> integers = Lists.newArrayList(168, 410, 192, 538, 775, 388, 168, 586, 129, 380, 439, 281, 737, 912, 948);
        int run = pipeUpService.run(integers, END);
        int naiveRun = pipeUpService.run(integers, NAIVE);
        Assertions.assertEquals(run, naiveRun);
    }

    @Test
    void testEndBatch() {
        for (int i = 0; i < 1000; i++) {
            ArrayList<Integer> integers = ContextHelper.toList(ContextHelper.randomArray(15, 1000, false));
            int naiveRun = pipeUpService.run(integers, NAIVE);
            int endRun = pipeUpService.run(integers, END);
            if (naiveRun != endRun) {
                System.out.println(integers);
            }
            Assertions.assertEquals(naiveRun, endRun);
        }
    }
}
