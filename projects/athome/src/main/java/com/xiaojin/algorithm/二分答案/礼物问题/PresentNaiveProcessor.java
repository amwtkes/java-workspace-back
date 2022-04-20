package com.xiaojin.algorithm.二分答案.礼物问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xiaojin.algorithm.二分答案.礼物问题.PresentPriority.NAIVE;

@Component
@SortOrder(NAIVE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PresentNaiveProcessor implements PresentProcessor {
    @Override
    public void process(PresentContext presentContext) throws ProcessorException {
        if (presentContext.getSwitcher() != NAIVE) {
            System.out.println("不是用的暴力解！");
            return;
        }
        int k = presentContext.getNr();
        List<Integer> sortedItems = presentContext.getItems().stream().sorted().collect(Collectors.toList());
        int maxValue = 0;
        List<Integer> ret = new ArrayList<>();
        List<Integer> chosen = new ArrayList<>(k);
        List<List<Integer>> combination = new ArrayList<>();

        computeChosen(sortedItems, k, 0, sortedItems.size() - 1, chosen, combination);
        for (List<Integer> indexCombinationList : combination) {
            int minTemp = Integer.MAX_VALUE;

            //三个数比两次就行了
            //求最小取值中，最大的组合
            for (int i = 0; i < indexCombinationList.size() - 1; ) {
                Integer first = sortedItems.get(indexCombinationList.get(i));
                Integer next = sortedItems.get(indexCombinationList.get(++i));
                if (minTemp > next - first) {
                    minTemp = next - first;
                }
            }
            if (minTemp > maxValue) {
                maxValue = minTemp;
                ret = indexCombinationList;
            }
        }
        presentContext.setResultNotFinish(new PresentContext.PresentResult(ret, maxValue));
    }

    private void computeChosen(List<Integer> items, int k, int begin, int end, List<Integer> chosen, List<List<Integer>> combination) {
        if (k > 1) {
            for (int i = begin; i < end; i++) {
                chosen.add(i);
                computeChosen(items, k - 1, i + 1, end, chosen, combination);
                chosen.remove(chosen.size() - 1);
            }
            return;
        }
        for (int i = begin; i <= end; i++) {
            ArrayList<Integer> tmp = new ArrayList<>(chosen);
            tmp.add(i);
            combination.add(new ArrayList<>(tmp));
        }
    }

    @Override
    public String getProcessorName() {
        return "二分答案-礼物问题 -- 暴力解...";
    }
}
