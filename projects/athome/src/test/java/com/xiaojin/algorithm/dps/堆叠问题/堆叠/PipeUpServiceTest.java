package com.xiaojin.algorithm.dps.堆叠问题.堆叠;

import com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors.PipeUpEndWayProcessor;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors.PipeUpGreedyProcessor;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors.PipeUpNaiveProcessor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PipeUpService.class, PipeUpNaiveProcessor.class, PipeUpEndWayProcessor.class, PipeUpGreedyProcessor.class})
class PipeUpServiceTest {
    @Autowired
    private PipeUpService pipeUpService;

    @Test
    void test() {

    }
}
