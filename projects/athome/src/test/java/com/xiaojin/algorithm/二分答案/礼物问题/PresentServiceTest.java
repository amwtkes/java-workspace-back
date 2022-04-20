package com.xiaojin.algorithm.二分答案.礼物问题;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.xiaojin.algorithm.二分答案.礼物问题.PresentPriority.NAIVE;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PresentService.class, PresentLoadProcessor.class, PresentNaiveProcessor.class})
class PresentServiceTest {
    @Autowired
    private PresentService presentService;

    @Test
    public void test() {
        PresentContext.PresentResult run = presentService.run("present/1.txt", NAIVE, 3);
        System.out.println(run.getNr());
        System.out.println(run.getIndexes());
    }
}
