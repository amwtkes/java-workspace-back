package com.xiaojin.algorithm.palindrome.processor3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {P3Service.class})
class L3ServiceTest {
    @Autowired
    private P3Service p3Service;

    @Test
    public void test() {
        String run = p3Service.run("palindrome/1.txt");
        Assertions.assertEquals("acabacabaca", run);
    }
}
