package com.xiaojin.algorithm.mics.hanoi;

import com.xiaojin.algorithm.base.ContextHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.xiaojin.algorithm.mics.hanoi.HanoiPriority.COMPUTATION_FOR;
import static com.xiaojin.algorithm.mics.hanoi.HanoiPriority.COMPUTATION_RECURSIVE;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HanoiService.class, HanoiRecursiveProcessor.class, HanoiComputationProcessor.class})
class HanoiServiceTest {
    @Autowired
    private HanoiService hanoiService;

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            int nr = ContextHelper.randomInt(1, 30, false);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>nr =" + nr);
            int recursive = hanoiService.run(nr, COMPUTATION_RECURSIVE);
            int forRun = hanoiService.run(nr, COMPUTATION_FOR);
            System.out.println("nr=" + nr + " recursive=" + recursive + " for=" + forRun);
            Assertions.assertEquals(recursive, forRun);
        }
    }
}
