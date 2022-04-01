package com.xiaojin.algorithm.palindrome.processor3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {P3Service.class, P3LoadProcessor.class, P3ComputationProcessor.class})
class P3ServiceTest {
    @Autowired
    private P3Service p3Service;

    @Test
    public void test() {
        String run = p3Service.run("palindrome/1.txt");
        Assertions.assertEquals("acabacabaca", run);
    }

    @Test
    public void test2() {
        String run = p3Service.run("palindrome/2.txt");
        Assertions.assertEquals("acabaabaca", run);
    }
}
