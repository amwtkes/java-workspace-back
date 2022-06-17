package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.datastructure.combination.Combinations;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSContext;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.NAIVE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(NAIVE)
public class LissNaiveProcessor implements LISSProcessor {
    @Override
    public void process(LISSContext lissContext) throws ProcessorException {
        if (lissContext.getSwitcher() != NAIVE) {
            System.out.println("不使用Liss的蛮力算法！");
            return;
        }
        List<Integer> items = lissContext.getItems();
        List<List<Integer>> allCombinations = new ArrayList<>();
        for (int i = 1; i < items.size(); i++) {
            allCombinations.addAll(Combinations.cnkGenerate(items.size() - 1, i));
        }
        int maxSize = longestIncrementalListSize(allCombinations, items);
        lissContext.setResultNotFinish(maxSize);
    }

    private int longestIncrementalListSize(List<List<Integer>> indexCombinationLists, List<Integer> items) {
        int maxSize = -1;
        for (List<Integer> indexes : indexCombinationLists) {
            if (isIncrementalList(indexes, items)) {
                if (maxSize < indexes.size()) {
                    maxSize = indexes.size();
                }
            }
        }
        return maxSize;
    }

    /**
     * 等于也不是递增
     *
     * @param indexes
     * @param list    原始的串
     * @return true是单调递增，false不是单调递增
     */
    private boolean isIncrementalList(List<Integer> indexes, List<Integer> list) {
        int prevValue = -1;
        for (Integer integer : indexes) {
            Integer prevValue1 = list.get(integer);
            if (prevValue >= prevValue1) {
                return false;
            } else {
                prevValue = prevValue1;
            }
        }
        return true;
    }

    @Override
    public String getProcessorName() {
        return "LISS 最长递增子序列 蛮力算法...";
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = Combinations.cnkGenerate(10, 4);
        System.out.println(lists);
    }
}
