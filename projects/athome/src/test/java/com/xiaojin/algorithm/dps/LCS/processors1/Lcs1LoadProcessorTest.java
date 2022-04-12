package com.xiaojin.algorithm.dps.LCS.processors1;

import com.xiaojin.algorithm.dps.LCS.base.Lcs1Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lcs1LoadProcessor.class})
class Lcs1LoadProcessorTest {
    @Autowired
    private Lcs1LoadProcessor lcs1LoadProcessor;

    @Test
    public void process() throws ProcessorException {
        Lcs1Context lcs1Context = new Lcs1Context();
        lcs1LoadProcessor.process(lcs1Context);
        List<Integer> expectedList = new ArrayList<>();
//        expectedList.add(7,1,4,3,5,5,9,4,10,25,11,12,33,2,13,6)
        expectedList.add(7);
        expectedList.add(1);
        expectedList.add(4);
        expectedList.add(3);
        expectedList.add(5);
        expectedList.add(5);
        expectedList.add(9);
        expectedList.add(4);
        expectedList.add(10);
        expectedList.add(25);
        expectedList.add(11);
        expectedList.add(12);
        expectedList.add(33);
        expectedList.add(2);
        expectedList.add(13);
        expectedList.add(6);
        Assertions.assertIterableEquals(expectedList, lcs1Context.getItems());
    }
}
