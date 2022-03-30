package com.xiaojin.algorithm.LCS.processor4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lcs4Service.class, Lcs4LoadProcessor.class, Lcs4ComputationProcessor.class})
class Lcs4ServiceTest {
    @Autowired
    private Lcs4Service lcs4Service;

    @Test
    public void test() {
        List<Integer> run = lcs4Service.run("lcs/2.txt");
        Assertions.assertEquals(2, run.size());

        run = lcs4Service.run("lcs/1.txt");
        Assertions.assertEquals(8, run.size());
    }
}
