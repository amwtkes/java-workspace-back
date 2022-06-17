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
         * end[i]代表一个函数，i表示长度为i+1 -> end(i) -> 值是满足i+1长度的子序列中，子序列的最后一个元素的最小值。
         * 长度到最小末端元素的关系。
         * end[i]肯定是一个递增的数组
         * end函数的参数i跟array[i]的i没有关系。
         * 为什么end函数有效？
         * 1. 顺序性，最长子序列的长度,本身是有顺序的，从左到右，依次遍历。NORMAL_MISS的更新，跟这个顺序性息息相关。没有顺序性，这个end函数就不成立。
         * 2. 扩展性跟一个子序列最后一个元素的大小十分相关，特殊元素的作用。扩展的过程是个代数比较的过程，跟最小值有关。
         * 3。 所以可以做一个end函数来描述这个关系。
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
