package com.xiaojin.algorithm.reverse_pair;

import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.reverse_pair.RpPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RpComputationProcessor implements RpProcessor {
    @Override
    public void process(RpContext rpContext) throws ProcessorException {
        List<Integer> items = rpContext.getItems();
        List<Pair<Integer, Integer>> pairs = rpContext.getRpPairs();
        List<Integer> tempList = new ArrayList<>(items);
        mergeSort(items, pairs, tempList, 0, items.size() - 1);
        rpContext.setResultAndFinish(pairs);
    }

    private void mergeSort(List<Integer> items, List<Pair<Integer, Integer>> pairs, List<Integer> tempList, int begin, int end) {
        if (end == begin || begin > end) {
            return;
        }
        if (end - begin == 1) {
            if (items.get(end) < items.get(begin)) {
                pairs.add(new Pair<>(items.get(begin), items.get(end)));
                tempList.set(begin, items.get(end));
                tempList.set(end, items.get(begin));
                items.set(begin, tempList.get(begin));
                items.set(end, tempList.get(end));
            }
            return;
        }
        int midIndex = (end - begin) / 2 + begin;
        mergeSort(items, pairs, tempList, begin, midIndex); // [being -> midIndex]
        mergeSort(items, pairs, tempList, midIndex + 1, end); // [midIndex+1 -> end]
        //合并
        int tempBegin = begin;
        for (int i = tempBegin, j = midIndex + 1; tempBegin <= end; tempBegin++) {
            if (i > midIndex) {
                tempList.set(tempBegin, items.get(j));
                items.set(tempBegin, tempList.get(tempBegin));
                j++;
                continue;
            }
            if (j > end) {
                tempList.set(tempBegin, items.get(i));
                i++;
                continue;
            }

            if (items.get(j) < items.get(i)) {
                for (int k = i; k <= midIndex; k++) {
                    pairs.add(new Pair<>(items.get(k), items.get(j)));
                }
                tempList.set(tempBegin, items.get(j));
                j++;
            } else {
                tempList.set(tempBegin, items.get(i));
                i++;
            }
        }
        //回写
        for (int i = begin; i <= end; i++) {
            items.set(i, tempList.get(i));
        }
    }

    @Override
    public String getProcessorName() {
        return "逆序对 computation...";
    }
}
