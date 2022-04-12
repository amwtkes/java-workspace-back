package com.xiaojin.algorithm.dps.EditDistance.LCSL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LLService.class, LLLoadProcessor.class, LLComputeProcessor.class})
class LLServiceTest {
    @Autowired
    private LLService llService;

    @Test
    public void test() {
        String run = llService.run("EditDistance/1.txt");
        Assertions.assertEquals("mtcu", run);
    }

    @Test
    public void test2() {
        String run = llService.run("EditDistance/2.txt");
        Assertions.assertEquals("Program", run);
    }

    @Test
    public void test3() {
        String run = llService.run("EditDistance/3.txt");
        Assertions.assertEquals("a", run);
    }

    @Test
    public void test4() {
        String run = llService.run("EditDistance/4.txt");
        Assertions.assertEquals("b", run);
    }

    @Test
    public void test5() {
        String run = llService.run("EditDistance/5.txt");
        Assertions.assertEquals("b", run);
    }

    @Test
    public void test6() {
        String run = llService.run("EditDistance/6.txt");
        Assertions.assertEquals("b", run);
    }
}
