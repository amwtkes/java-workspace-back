package com.xiaojin.algorithm.TopK;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TkService.class, TkLoadProcessor.class, TkComputationProcessor.class})
class TkServiceTest {
    @Autowired
    private TkService tkService;

    @Test
    public void test() {
        List<Integer> run = tkService.run("TopK/1.txt", 5);
        Assertions.assertEquals(5, run.size());
    }
}
