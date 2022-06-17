package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSContext;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.base.ContextHelper.binarySearch;
import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.END_DP;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(END_DP)
public class LissDpEndProcessor implements LISSProcessor {
    @Override
    public void process(LISSContext lissContext) throws ProcessorException {
        if (lissContext.getSwitcher() != END_DP) {
            System.out.println("不使用end_dp");
            return;
        }
        System.out.println("使用end_dp算法!");
        List<Integer> items = lissContext.getItems();
//        System.out.println(items);
        /**
         * 算法见README
         * end[i]肯定是一个递增的数组
         *
         */
        ArrayList<Integer> end = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                end.add(items.get(0));
                continue;
            }
            ContextHelper.BinarySearchResult binarySearchResult = binarySearch(end, items.get(i));
            if (binarySearchResult.getResultType() == ContextHelper.BinarySearchResultType.NO_LESSER) {
                end.set(0, items.get(i));
            } else if (binarySearchResult.getResultType() == ContextHelper.BinarySearchResultType.NO_BIGGER) {
                end.add(items.get(i));
            } else if (binarySearchResult.getResultType() == ContextHelper.BinarySearchResultType.NORMAL_MISS) {
                end.set(binarySearchResult.getLastLessOneIndex() + 1, items.get(i));
            }
        }
        lissContext.setResultNotFinish(end.size());
    }

    @Override
    public String getProcessorName() {
        return "Liss dp end 最长递增子序列...";
    }
}
