package com.xiaojin.algorithm.dps.LCS.processor3;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lcs3service.class, Lcs3LoadProcessor.class, Lcs3Phase1Processor.class, Lcs3Phase2Processor.class})
class Lcs3serviceTest {
    @Autowired
    private Lcs3service lcs3service;

    @Test
    public void test() {
        List<Integer> run = lcs3service.run();
        Assertions.assertIterableEquals(Lists.newArrayList(9, 10, 11, 12, 13), run);
    }
}
