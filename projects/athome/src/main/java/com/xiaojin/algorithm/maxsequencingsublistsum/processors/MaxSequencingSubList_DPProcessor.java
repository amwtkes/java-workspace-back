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
import java.util.Comparator;
import java.util.Optional;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.DP;

@Component
@Slf4j
@SortOrder(DP)
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

    private MaxSequencingResult doJob(ArrayList<Float> arrayList) throws ProcessorException {
        ArrayList<MaxSequencingResult> table = new ArrayList<>(arrayList.size());
        findMaxNoRecursive(arrayList, table);
        Optional<MaxSequencingResult> max = table.stream().max(Comparator.comparing(MaxSequencingResult::getOriginMaxValue));
        if (max.isPresent()) {
            MaxSequencingResult result = max.get();
            result.setMaxValue(ContextHelper.round2(result.getOriginMaxValue()));
            return result;
        }
        throw new ProcessorException("DP返回空！");
    }

    private MaxSequencingResult findMax(ArrayList<Float> arrayList, ArrayList<MaxSequencingResult> table, int index) {
        if (index == 0) {
            MaxSequencingResult result = MaxSequencingResult.builder().originMaxValue(arrayList.get(index)).rightIndex(0).leftIndex(0).build();
            table.add(result);
            return result;
        }
        MaxSequencingResult preResult = findMax(arrayList, table, index - 1);
        Float preMaxValue = preResult.getOriginMaxValue();
        Float tmpResult = preMaxValue + arrayList.get(index);
        /*
         * F(n) = max{F(n-1)+an,an}
         * */
        MaxSequencingResult result = tmpResult >= arrayList.get(index) ? MaxSequencingResult.builder()
                .originMaxValue(tmpResult)
                .leftIndex(preResult.getLeftIndex())
                .rightIndex(index)
                .build() : MaxSequencingResult.builder().originMaxValue(arrayList.get(index)).leftIndex(index).rightIndex(index).build();
        table.add(result);
        return result;
    }

    private void findMaxNoRecursive(ArrayList<Float> arrayList, ArrayList<MaxSequencingResult> table) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                MaxSequencingResult result = MaxSequencingResult.builder().originMaxValue(arrayList.get(i)).rightIndex(i).leftIndex(i).build();
                table.add(result);
                continue;
            }
            MaxSequencingResult preResult = table.get(i - 1);
            Float preMaxValue = preResult.getOriginMaxValue();
            Float tmpResult = preMaxValue + arrayList.get(i);
            MaxSequencingResult result = tmpResult >= arrayList.get(i) ? MaxSequencingResult.builder()
                    .originMaxValue(tmpResult)
                    .leftIndex(preResult.getLeftIndex())
                    .rightIndex(i)
                    .build() : MaxSequencingResult.builder().originMaxValue(arrayList.get(i)).leftIndex(i).rightIndex(i).build();
            table.add(result);
        }

    }

    @Override
    public String getProcessorName() {
        return "最大子段和 - 动态规划";
    }
}
