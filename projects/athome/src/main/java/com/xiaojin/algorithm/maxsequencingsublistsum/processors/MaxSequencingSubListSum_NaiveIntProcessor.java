package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmIntegerContext;
import com.xiaojin.algorithm.base.AlgorithmIntProcessor;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.M1ProcessorPriority.ALL;

@Component
@SortOrder(ALL)
public class MaxSequencingSubListSum_NaiveIntProcessor implements AlgorithmIntProcessor {
    @Override
    public void process(AlgorithmIntegerContext algorithmIntegerContext) {
        algorithmIntegerContext.finish(100);
    }
}
