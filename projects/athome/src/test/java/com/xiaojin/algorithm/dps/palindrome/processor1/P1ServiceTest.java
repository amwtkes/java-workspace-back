package com.xiaojin.algorithm.dps.palindrome.processor1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {P1Service.class, P1LoadProcessor.class, P1ComputationProcessor.class})
class P1ServiceTest {
    @Autowired
    private P1Service p1Service;

    @Test
    public void test() {
        String run = p1Service.run("palindrome/1.txt");
        Assertions.assertEquals("acabacabaca", run);
    }
    @Test
    public void test2() {
        String run = p1Service.run("palindrome/2.txt");
        Assertions.assertEquals("acabaabaca", run);
    }
}
