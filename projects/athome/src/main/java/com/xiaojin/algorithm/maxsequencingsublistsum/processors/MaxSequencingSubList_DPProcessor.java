package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.base.ContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import runtime.processor.baseprocessor.ProcessorException;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.ArrayList;

//@Component
@Slf4j
public class MaxSequencingSubList_DPProcessor implements MaxSequencingProcessor {
    @Override
    public void process(AlgorithmGeneralContext algorithmGeneralContext) throws ProcessorException {
        algorithmGeneralContext.assertInputNotBeNull();
        ArrayList<Float> list = ContextHelper.splitter(algorithmGeneralContext, 1.0f);
        MaxSequencingResult maxSequencingResult = doJob(list);
        log.info(getProcessorName() + " - 计算结果------>" + maxSequencingResult);
        DefaultProcessorResult<Object> processorResult = new DefaultProcessorResult<>();
        processorResult.setResult(maxSequencingResult);
        algorithmGeneralContext.setProcessorResult(processorResult);
    }

    private MaxSequencingResult doJob(ArrayList<Float> list) {
        return null;
    }

    @Override
    public String getProcessorName() {
        return "最大子段和 - 动态规划";
    }
}
