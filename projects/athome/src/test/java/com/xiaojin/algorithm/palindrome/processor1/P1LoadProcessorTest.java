package com.xiaojin.algorithm.palindrome.processor1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = P1LoadProcessor.class)
class P1LoadProcessorTest {
    @Autowired
    private P1Processor p1Processor;

    @Test
    public void test() throws ProcessorException {
        P1Context p1Context = new P1Context("palindrome/1.txt");
        p1Processor.process(p1Context);
        Assertions.assertEquals("#a#d#b#a#c#a#b#a#c#a#b#a#c#a#", p1Context.getInput());
    }
}
