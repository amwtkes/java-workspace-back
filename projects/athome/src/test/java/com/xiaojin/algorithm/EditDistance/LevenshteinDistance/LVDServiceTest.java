package com.xiaojin.algorithm.EditDistance.LevenshteinDistance;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LVDService.class})
class LVDServiceTest {
    @Autowired
    private LVDService lvdService;

    public void test() {
        LVDContext.LVDResult run = lvdService.run("EditDistance/1.txt");
        Assertions.assertEquals(run.getMinLength(), 3);
    }
}
