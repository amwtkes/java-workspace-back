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
import java.util.Comparator;
import java.util.Optional;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.DP_I;

@Component
@Slf4j
@SortOrder(DP_I)
public class MaxSequencingSubList_DP_I_Processor implements MaxSequencingProcessor {
    @Override
    public void process(MaxSequencingContext algorithmGeneralContext) throws ProcessorException {
        if (algorithmGeneralContext.getSourceDataType().equals(SourceDataType.FLOAT)) {
            return;
        }

        algorithmGeneralContext.assertInputNotBeNull();
        ArrayList<Integer> list = algorithmGeneralContext.getIntList();
        if (algorithmGeneralContext.getIntList() == null || algorithmGeneralContext.getIntList().size() == 0) {
            list = ContextHelper.splitter(algorithmGeneralContext, (Integer) SourceDataType.INT.getValue());
        }
        MaxSequencingResult maxSequencingResult = doJob(list);
        log.info(getProcessorName() + " - 计算结果------>" + maxSequencingResult);
        DefaultProcessorResult<Object> processorResult = new DefaultProcessorResult<>();
        processorResult.setResult(maxSequencingResult);
        algorithmGeneralContext.setProcessorResult(processorResult);
    }

    private MaxSequencingResult doJob(ArrayList<Integer> arrayList) throws ProcessorException {
        ArrayList<MaxSequencingResult> table = new ArrayList<>(arrayList.size());
        findMaxNoRecursive(arrayList, table);
        Optional<MaxSequencingResult> max = table.stream().max(Comparator.comparing(MaxSequencingResult::getMaxValueInt));
        if (max.isPresent()) {
            return max.get();
        }
        throw new ProcessorException("DP返回空！");
    }

    private void findMaxNoRecursive(ArrayList<Integer> arrayList, ArrayList<MaxSequencingResult> table) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                MaxSequencingResult result = MaxSequencingResult.builder().maxValueInt(arrayList.get(i)).rightIndex(i).leftIndex(i).build();
                table.add(result);
                continue;
            }
            MaxSequencingResult preResult = table.get(i - 1);
            Integer preMaxValue = preResult.getMaxValueInt();
            int tmpResult = preMaxValue + arrayList.get(i);
            MaxSequencingResult result = tmpResult >= arrayList.get(i) ? MaxSequencingResult.builder()
                    .maxValueInt(tmpResult)
                    .leftIndex(preResult.getLeftIndex())
                    .rightIndex(i)
                    .build() : MaxSequencingResult.builder().maxValueInt(arrayList.get(i)).leftIndex(i).rightIndex(i).build();
            table.add(result);
        }

    }

    @Override
    public String getProcessorName() {
        return "最大子段和 - 动态规划";
    }
}
