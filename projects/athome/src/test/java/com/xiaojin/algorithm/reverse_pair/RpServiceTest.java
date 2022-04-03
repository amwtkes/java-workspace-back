package com.xiaojin.algorithm.reverse_pair;

import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RpService.class, RpLoadProcessor.class, RpComputationProcessor.class})
class RpServiceTest {
    @Autowired
    private RpService rpService;

    @Test
    public void test() {
        List<Pair<Integer, Integer>> run = rpService.run("reverse_pair/1.txt");
        Assertions.assertEquals(17, run.size());
    }
}
