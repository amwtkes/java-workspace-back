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

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.DC_I;

@Component
@SortOrder(DC_I)
@Slf4j
public class MaxSequencingSubListSum_DC_I_Processor implements MaxSequencingProcessor {
    @Override
    public void process(MaxSequencingContext algorithmGeneralContext) throws ProcessorException {
        if (algorithmGeneralContext.getSourceDataType().equals(SourceDataType.FLOAT)) {
            return;
        }

        algorithmGeneralContext.assertInputNotBeNull();
        ArrayList<Integer> inputs = algorithmGeneralContext.getIntList();
        if (inputs == null || inputs.size() == 0) {
            inputs = ContextHelper.splitter(algorithmGeneralContext, (Integer) SourceDataType.INT.getValue());
        }
        MaxSequencingResult maxSequencingResult = doJob(inputs);
        log.info(getProcessorName() + " - 计算结果------>" + maxSequencingResult);
        DefaultProcessorResult<Object> processorResult = new DefaultProcessorResult<>();
        processorResult.setResult(maxSequencingResult);
        algorithmGeneralContext.setProcessorResult(processorResult);
    }

    private MaxSequencingResult doJob(ArrayList<Integer> inputs) {
        return findMaxSequencing(inputs, 0, inputs.size() - 1);
    }

    private MaxSequencingResult findMaxSequencing(ArrayList<Integer> arrayList, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return MaxSequencingResult.builder()
                    .maxValueInt(arrayList.get(beginIndex))
                    .rightIndex(beginIndex)
                    .leftIndex(beginIndex).build();
        }
        int midIndex = endIndex / 2 + beginIndex / 2;
        MaxSequencingResult frontHalf = findMaxSequencing(arrayList, beginIndex, midIndex);
        MaxSequencingResult backHalf = findMaxSequencing(arrayList, midIndex + 1, endIndex);
        MaxSequencingResult midHalf = calculateMidHalf(arrayList, beginIndex, endIndex, midIndex);
        if (frontHalf.getMaxValueInt() >= backHalf.getMaxValueInt()) {
            return midHalf.getMaxValueInt() >= frontHalf.getMaxValueInt() ? midHalf : frontHalf;
        }
        return midHalf.getMaxValueInt() >= backHalf.getMaxValueInt() ? midHalf : backHalf;
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
    private MaxSequencingResult calculateMidHalf(ArrayList<Integer> arrayList, int beginIndex, int endIndex, int midIndex) {
        Integer midValue = arrayList.get(midIndex);
        Integer leftMax = midValue;
        Integer leftSum = midValue;
        int leftMaxIndex = midIndex;
        for (int i = midIndex - 1; beginIndex <= i; i--) {
            leftSum += arrayList.get(i);
            if (leftSum > leftMax) {
                leftMax = leftSum;
                leftMaxIndex = i;
            }
        }

        Integer rightMax = midValue;
        Integer rightSum = midValue;
        int rightMaxIndex = midIndex;
        for (int i = midIndex + 1; i <= endIndex; i++) {
            rightSum += arrayList.get(i);
            if (rightSum > rightMax) {
                rightMax = rightSum;
                rightMaxIndex = i;
            }
        }

        if (rightMaxIndex == leftMaxIndex) {
            return MaxSequencingResult.builder().maxValueInt(midValue)
                    .leftIndex(leftMaxIndex)
                    .rightIndex(leftMaxIndex)
                    .build();
        }
        Integer midMax = rightMax + leftMax - midValue;
        return MaxSequencingResult.builder()
                .maxValueInt(midMax)
                .leftIndex(leftMaxIndex)
                .rightIndex(rightMaxIndex)
                .build();
    }

    @Override
    public String getProcessorName() {
        return "最大字段和--分治算法";
    }
}
