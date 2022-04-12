package com.xiaojin.algorithm.dps.LCS.processors1;

import com.xiaojin.algorithm.dps.LCS.base.Lcs1Context;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lcs1ComputationProcessor.class, Lcs1LoadProcessor.class})
class Lcs1ComputationProcessorTest {
    @Autowired
    private Lcs1ComputationProcessor computationProcessor;

    @Autowired
    private Lcs1LoadProcessor lcs1LoadProcessor;

    @Test
    public void test() throws ProcessorException {
        Lcs1Context lcs1Context = new Lcs1Context();
        lcs1LoadProcessor.process(lcs1Context);
        computationProcessor.process(lcs1Context);
        Assertions.assertIterableEquals(Lists.newArrayList(9, 10, 11, 12, 13), lcs1Context.getResult());
    }
}
