package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.SourceDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.ArrayList;

import static com.xiaojin.algorithm.base.ContextHelper.round;
import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.NAIVE_F;

@Component
@SortOrder(NAIVE_F)
@Slf4j
public class MaxSequencingSubListSum_NaiveIntProcessor implements MaxSequencingProcessor {
    @Override
    public void process(MaxSequencingContext algorithmGeneralContext) throws ProcessorException {
        if (algorithmGeneralContext.getSourceDataType().equals(SourceDataType.INT)) {
            return;
        }

        algorithmGeneralContext.assertInputNotBeNull();
        ArrayList<Float> sourceList = algorithmGeneralContext.getFloatList();
        if (sourceList == null || sourceList.size() == 0) {
            sourceList = ContextHelper.splitter(algorithmGeneralContext, 0f);
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

    private MaxSequencingResult findMaxSequence(ArrayList<Float> source) {
        float max = Float.MIN_VALUE;
        int lIndex = 0, rIndex = 0;
        int l = source.size();
        for (int i = 0; i < l; i++) {
            float tmpSum = 0.0f;
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
                .maxValueDecimal(round(2, max))
                .originMaxValue(max)
                .leftIndex(lIndex)
                .rightIndex(rIndex)
                .build();
    }
}
