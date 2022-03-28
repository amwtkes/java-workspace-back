package com.xiaojin.algorithm.LCS.processors2;

import com.xiaojin.algorithm.LCS.base.Lcs2Context;
import com.xiaojin.algorithm.LCS.processors1.Lcs1ComputationProcessor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lcs2ComputationProcessor.class, Lcs2LoadProcessor.class, Lcs1ComputationProcessor.class})
class Lcs2ComputationProcessorTest {
    @Autowired
    private Lcs2LoadProcessor lcs2LoadProcessor;
    @Autowired
    private Lcs2ComputationProcessor computationProcessor;

    @Test
    public void test() throws ProcessorException {
        Lcs2Context lcs2Context = new Lcs2Context();
        lcs2LoadProcessor.process(lcs2Context);
        computationProcessor.process(lcs2Context);
        Assertions.assertIterableEquals(Lists.newArrayList(1,2,3,4,5,6,7), lcs2Context.getResult());
    }
}
