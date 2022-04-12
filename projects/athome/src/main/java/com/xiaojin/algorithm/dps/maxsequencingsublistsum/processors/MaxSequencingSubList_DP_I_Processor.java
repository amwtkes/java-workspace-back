package com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.SourceDataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.M1ProcessorPriority.DP_I;

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
        MaxSequencingResult maxSequencingResult = doJob(list, algorithmGeneralContext);
        log.info(getProcessorName() + " - 计算结果------>" + maxSequencingResult);
        DefaultProcessorResult<Object> processorResult = new DefaultProcessorResult<>();
        processorResult.setResult(maxSequencingResult);
        algorithmGeneralContext.setProcessorResult(processorResult);
    }

    private MaxSequencingResult doJob(ArrayList<Integer> arrayList, MaxSequencingContext maxSequencingContext) throws ProcessorException {
        findMaxNoRecursive(arrayList, maxSequencingContext.getDpTable());
        Optional<MaxSequencingResult> max = maxSequencingContext.getDpTable().stream().max(Comparator.comparing(MaxSequencingResult::getMaxValueInt));
        if (max.isPresent()) {
            return max.get();
        }
        throw new ProcessorException("DP返回空！");
    }

    private void findMaxNoRecursive(ArrayList<Integer> arrayList, ArrayList<MaxSequencingResult> table) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                MaxSequencingResult result = table.get(i);
                result.setMaxValueInt(arrayList.get(i));
                result.setLeftIndex(i);
                result.setRightIndex(i);
                continue;
            }
            MaxSequencingResult preResult = table.get(i - 1);
            Integer preMaxValue = preResult.getMaxValueInt();
            int tmpResult = preMaxValue + arrayList.get(i);
            MaxSequencingResult result = table.get(i);
            if (tmpResult >= arrayList.get(i)) {
                result.setMaxValueInt(tmpResult);
                result.setLeftIndex(preResult.getLeftIndex());
            } else {
                result.setMaxValueInt(arrayList.get(i));
                result.setLeftIndex(i);
            }
            result.setRightIndex(i);
        }

    }

    @Override
    public String getProcessorName() {
        return "最大子段和 - 动态规划";
    }
}
