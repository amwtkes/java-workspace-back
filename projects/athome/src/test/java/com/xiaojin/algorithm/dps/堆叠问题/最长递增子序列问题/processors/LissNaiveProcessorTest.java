package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.NAIVE;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LISSService.class, LissNaiveProcessor.class})
class LissNaiveProcessorTest {
    @Autowired
    private LISSService lissService;

    @Test
    void process() {
        List<Integer> integers = Lists.list(3, 2, 1, 2, 3, 0, 4, 6, 2, 7);//ContextHelper.toList(ContextHelper.randomArray(20, 100, false));
        int run = lissService.run(integers, NAIVE);
        assertEquals(run, 6);
        integers = Lists.list(4, 1, 3, 2, 3, 9, 5, 6);
        run = lissService.run(integers, NAIVE);
        assertEquals(5, run);
    }
}
