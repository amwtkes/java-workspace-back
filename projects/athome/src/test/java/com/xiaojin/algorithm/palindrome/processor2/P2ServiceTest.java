package com.xiaojin.algorithm.palindrome.processor2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {P2Service.class, P2LoadProcessor.class, P2ComputationProcessor.class})
class P2ServiceTest {
    @Autowired
    private P2Service p2Service;

    @Test
    public void test() {
        String run = p2Service.run("palindrome/1.txt");
        Assertions.assertEquals("acabacabaca", run);
    }
}
