package com.xiaojin.algorithm.LCS.processor5;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lcs5Service.class, Lcs5LoadProcessor.class, Lcs5ComputationProcessor.class})
class Lcs5ServiceTest {
    @Autowired
    private Lcs5Service lcs5Service;

    @Test
    public void test() {
        List<Integer> run = lcs5Service.run("lcs/3.txt");
        Assertions.assertIterableEquals(Lists.newArrayList(3, 5, 5, 9), run);
    }
}
