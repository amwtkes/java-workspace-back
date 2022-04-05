package com.xiaojin.algorithm.magic_exchange_to_max;

import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MemService.class, MemLoadProcessor.class, MemComputationProcessor.class})
class MemServiceTest {
    @Autowired
    private MemService memService;

    @Test
    public void test() {
        Pair<Integer, Integer> run = memService.run("magic_exchange_to_max/1.txt");
        Assertions.assertEquals(new Pair<>(1, 4), run);
    }

    @Test
    public void test2() {
        Pair<Integer, Integer> run = memService.run("magic_exchange_to_max/2.txt");
        Assertions.assertEquals(new Pair<>(0, 3), run);
    }
}
