package com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.SourceDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.CommonProcessorConst;
import runtime.processor.baseprocessor.ProcessorException;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.ArrayList;

@Component
@SortOrder(CommonProcessorConst.PROCESSOR_IGNORED)
@Slf4j
public class MaxSequencingSubListSum_Naive_I_Processor implements MaxSequencingProcessor {
    @Override
    public void process(MaxSequencingContext algorithmGeneralContext) throws ProcessorException {
        if (algorithmGeneralContext.getSourceDataType().equals(SourceDataType.FLOAT)) {
            return;
        }

        algorithmGeneralContext.assertInputNotBeNull();
        ArrayList<Integer> sourceList = algorithmGeneralContext.getIntList();
        if (sourceList == null || sourceList.size() == 0) {
            sourceList = ContextHelper.splitter(algorithmGeneralContext, (Integer) SourceDataType.INT.getValue());
        }
        MaxSequencingResult result = findMaxSequence(sourceList);
        log.info(getProcessorName() + " - 计算结果------>" + result.toString());
        DefaultProcessorResult<Object> processorResult = new DefaultProcessorResult<>();
        processorResult.setResult(result);
        algorithmGeneralContext.setProcessorResult(processorResult);
    }

    @Override
    public String getProcessorName() {
        return "最大字段和蛮力算法";
    }

    private MaxSequencingResult findMaxSequence(ArrayList<Integer> source) {
        Integer max = Integer.MIN_VALUE;
        int lIndex = 0, rIndex = 0;
        int l = source.size();
        for (int i = 0; i < l; i++) {
            Integer tmpSum = 0;
            for (int j = i; j < l; j++) {
                tmpSum += source.get(j);
                if (max < tmpSum) {
                    max = tmpSum;
                    lIndex = i;
                    rIndex = j;
                }

            }
        }

        return MaxSequencingResult.builder()
                .maxValueInt(max)
                .leftIndex(lIndex)
                .rightIndex(rIndex)
                .build();
    }
}
