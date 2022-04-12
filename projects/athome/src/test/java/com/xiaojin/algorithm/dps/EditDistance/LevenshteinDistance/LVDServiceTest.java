package com.xiaojin.algorithm.dps.EditDistance.LevenshteinDistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LVDService.class, LVDLoadProcessor.class, LVDComputationProcessor.class})
class LVDServiceTest {
    @Autowired
    private LVDService lvdService;

    @Test
    public void test() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/1.txt");
        Assertions.assertEquals(run.getMinLength(), 3);
    }

    @Test
    public void test2() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/2.txt");
        Assertions.assertEquals(run.getMinLength(), 6);
    }

    @Test
    public void test3() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/3.txt");
        Assertions.assertEquals(run.getMinLength(), 2);
    }

    @Test
    public void test4() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/4.txt");
        Assertions.assertEquals(run.getMinLength(), 2);
    }

    @Test
    public void test5() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/5.txt");
        Assertions.assertEquals(run.getMinLength(), 2);
    }

    @Test
    public void test6() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/6.txt");
        Assertions.assertEquals(run.getMinLength(), 2);
    }
}
