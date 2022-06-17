package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题;

import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors.LissDpProcessor;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors.LissNaiveProcessor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.DP;
import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.NAIVE;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LISSService.class, LissNaiveProcessor.class, LissDpProcessor.class})
class LISSServiceTest {
    @Autowired
    private LISSService lissService;

    @Test
    void testNaive() {
        List<Integer> integers = Lists.list(3, 2, 1, 2, 3, 0, 4, 6, 2, 7);//ContextHelper.toList(ContextHelper.randomArray(20, 100, false));
        int run = lissService.run(integers, NAIVE);
        assertEquals(run, 6);
        integers = Lists.list(4, 1, 3, 2, 3, 9, 5, 6);
        run = lissService.run(integers, NAIVE);
        assertEquals(5, run);
    }

    @Test
    public void testDp() {
        List<Integer> integers = Lists.list(3, 2, 1, 2, 3, 0, 4, 6, 2, 7);//ContextHelper.toList(ContextHelper.randomArray(20, 100, false));
        int naiveRun = lissService.run(integers, NAIVE);
        assertEquals(naiveRun, 6);
        int dpRun = lissService.run(integers, DP);
        assertEquals(naiveRun, dpRun);

        integers = Lists.list(4, 1, 3, 2, 3, 9, 5, 6);
        naiveRun = lissService.run(integers, NAIVE);
        dpRun = lissService.run(integers, DP);
        assertEquals(5, naiveRun);
        assertEquals(naiveRun, dpRun);
    }
}
