package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.base.AlgorithmGeneralProcessor;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.M1ProcessorPriority.ALL;

@Component
@SortOrder(ALL)
public class MaxSequencingSubListSum_NaiveIntProcessor implements AlgorithmGeneralProcessor {
    @Override
    public void process(AlgorithmGeneralContext algorithmGeneralContext) throws ProcessorException {
        algorithmGeneralContext.assertInputNotBeNull(algorithmGeneralContext);

        algorithmGeneralContext.finish(101);
    }
}
