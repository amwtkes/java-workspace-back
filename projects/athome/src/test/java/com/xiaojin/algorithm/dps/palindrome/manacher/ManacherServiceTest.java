package com.xiaojin.algorithm.dps.palindrome.manacher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ManacherService.class, ManacherLoadProcessor.class, ManacherComputeProcessor.class})
class ManacherServiceTest {
    @Autowired
    private ManacherService manacherService;

    @org.junit.jupiter.api.Test
    public void test() {
        String run = manacherService.run("palindrome/1.txt");
        Assertions.assertEquals("acabacabaca", run);
    }

    @Test
    public void test2() {
        String run = manacherService.run("palindrome/2.txt");
        Assertions.assertEquals("acabaabaca", run);
    }
}
