package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.ArrayList;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.DC;

@Component
@SortOrder(DC)
@Slf4j
public class MaxSequencingSubListSum_DCProcessor implements MaxSequencingProcessor {
    @Override
    public void process(AlgorithmGeneralContext algorithmGeneralContext) throws ProcessorException {
        algorithmGeneralContext.assertInputNotBeNull();
        ArrayList<Float> inputs = ContextHelper.splitter(algorithmGeneralContext, 1f);
        MaxSequencingResult maxSequencingResult = doJob(inputs);
        maxSequencingResult.setMaxValue(ContextHelper.round2(maxSequencingResult.getOriginMaxValue()));
        log.info(getProcessorName() + " - 计算结果------>" + maxSequencingResult);
        DefaultProcessorResult<Object> processorResult = new DefaultProcessorResult<>();
        processorResult.setResult(maxSequencingResult);
        algorithmGeneralContext.setProcessorResult(processorResult);
    }

    private MaxSequencingResult doJob(ArrayList<Float> inputs) {
        return findMaxSequencing(inputs, 0, inputs.size() - 1);
    }

    private MaxSequencingResult findMaxSequencing(ArrayList<Float> arrayList, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return MaxSequencingResult.builder()
                    .originMaxValue(arrayList.get(beginIndex))
                    .rightIndex(beginIndex)
                    .leftIndex(beginIndex).build();
        }
        int midIndex = endIndex / 2 + beginIndex / 2;
        MaxSequencingResult frontHalf = findMaxSequencing(arrayList, beginIndex, midIndex);
        MaxSequencingResult backHalf = findMaxSequencing(arrayList, midIndex + 1, endIndex);
        MaxSequencingResult midHalf = calculateMidHalf(arrayList, beginIndex, endIndex, midIndex);
        if (frontHalf.getOriginMaxValue() >= backHalf.getOriginMaxValue()) {
            return midHalf.getOriginMaxValue() >= frontHalf.getOriginMaxValue() ? midHalf : frontHalf;
        }
        return midHalf.getOriginMaxValue() >= backHalf.getOriginMaxValue() ? midHalf : backHalf;
    }

    /**
     * 将三个部分（前，后，中）减治为包含中间点与不包含中间点的两种情况。两种情况就包含了所有的情况。
     * 不包含中间点的情况就是front与back两者的最大值
     * 包含中间点的情况就是从中间点向左边与右边延伸最大值的和 = 从左边延伸的最大值+向右边延伸的最大值
     *
     * @param arrayList
     * @param beginIndex
     * @param endIndex
     * @param midIndex
     * @return
     */
    private MaxSequencingResult calculateMidHalf(ArrayList<Float> arrayList, int beginIndex, int endIndex, int midIndex) {
        float midValue = arrayList.get(midIndex);
        float leftMax = midValue;
        float leftSum = midValue;
        int leftMaxIndex = midIndex;
        for (int i = midIndex - 1; beginIndex <= i; i--) {
            leftSum += arrayList.get(i);
            if (leftSum > leftMax) {
                leftMax = leftSum;
                leftMaxIndex = i;
            }
        }

        float rightMax = midValue;
        float rightSum = midValue;
        int rightMaxIndex = midIndex;
        for (int i = midIndex + 1; i <= endIndex; i++) {
            rightSum += arrayList.get(i);
            if (rightSum > rightMax) {
                rightMax = rightSum;
                rightMaxIndex = i;
            }
        }

        if (rightMaxIndex == leftMaxIndex) {
            return MaxSequencingResult.builder().originMaxValue(midValue)
                    .leftIndex(leftMaxIndex)
                    .rightIndex(leftMaxIndex)
                    .build();
        }
        float midMax = rightMax + leftMax - midValue;
        return MaxSequencingResult.builder()
                .originMaxValue(midMax)
                .leftIndex(leftMaxIndex)
                .rightIndex(rightMaxIndex)
                .build();
    }

    @Override
    public String getProcessorName() {
        return "最大字段和--分治算法";
    }
}
