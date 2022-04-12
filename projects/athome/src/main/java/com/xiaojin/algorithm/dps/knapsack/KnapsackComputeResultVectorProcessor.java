package com.xiaojin.algorithm.dps.knapsack;

import com.xiaojin.algorithm.dps.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.dps.knapsack.base.KnapsackPriority;
import com.xiaojin.algorithm.dps.knapsack.base.KnapsackProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;

@Component
@SortOrder(KnapsackPriority.VECTOR)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KnapsackComputeResultVectorProcessor implements KnapsackProcessor {
    @Override
    public void process(KnapsackContext knapsackContext) throws ProcessorException {
        //结果表。i为index，值表示有多少个该物品
        int[] markVectorArray = new int[knapsackContext.getItems().size() + 1];
        for (int i = knapsackContext.getItems().size(), j = knapsackContext.getKnapsackWeightLimit(); i > 0 && j > 0; ) {
            int maxMarkIndex = knapsackContext.getMarkTable()[i][j];
            if (maxMarkIndex == i) {
                j = j - knapsackContext.getItems().get(i - 1).getWeight();
                markVectorArray[i] += 1;
                continue;
            }
            markVectorArray[maxMarkIndex] += 1;
            i = maxMarkIndex;
            j = j - knapsackContext.getItems().get(maxMarkIndex - 1).getWeight();
        }

        ArrayList<Integer> ret = new ArrayList<>(knapsackContext.getItems().size());
        for (int i = 1; i < knapsackContext.getItems().size() + 1; i++) {
            ret.add(markVectorArray[i]);
        }
        knapsackContext.setResultVector(ret);
        knapsackContext.setResult2(knapsackContext.getTable()[knapsackContext.getItems().size()][knapsackContext.getKnapsackWeightLimit()]);
    }

    @Override
    public String getProcessorName() {
        return "计算标记函数。";
    }
}
