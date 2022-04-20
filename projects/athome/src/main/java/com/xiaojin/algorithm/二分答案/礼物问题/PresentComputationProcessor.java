package com.xiaojin.algorithm.二分答案.礼物问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xiaojin.algorithm.二分答案.礼物问题.PresentPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PresentComputationProcessor implements PresentProcessor {
    @Override
    public void process(PresentContext presentContext) throws ProcessorException {
        if (presentContext.getSwitcher() != COMPUTATION) {
            System.out.println("没有使用二分答案法！");
            return;
        }
        List<Integer> sortedItems = presentContext.getItems().stream().sorted().collect(Collectors.toList());
        int k = presentContext.getNr();
        int maxRange = sortedItems.get(sortedItems.size() - 1) - sortedItems.get(0);
        int[] answerList = new int[maxRange];
        for (int i = 0; i < maxRange; i++) {
            answerList[i] = i + 1;
        }
        int leftIndex = 0;
        int rightIndex = maxRange - 1;
        int maxValue = -1;
        List<Integer> ret = new ArrayList<>();
        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            int answerMid = answerList[mid];
            if (answerFunction(sortedItems, k, answerMid, ret)) {
                maxValue = answerMid;
                leftIndex = mid + 1;
            } else {
                rightIndex = mid - 1;
            }
        }
        presentContext.setResultNotFinish(new PresentContext.PresentResult(ret, maxValue));
    }

    //每一对取值!都!要大于anserMid。因为题设中说的是取最小差值，然后再比最大。
    //midAnswer其实是最小值。只要有一个满足k个元素，两两相减都都大于answerMid，就说明
    //这个midAnswer可能不是最大的解。
    private boolean answerFunction(List<Integer> sortedItems, int k, int answerMid, List<Integer> ret) {
        List<Integer> tmpRet = new ArrayList<>();
        tmpRet.add(0);
        for (int i = 0, j = 1; j < sortedItems.size(); ) {
            int first = sortedItems.get(i);
            int next = sortedItems.get(j);
            if (next - first >= answerMid) {
                tmpRet.add(j);
                i = j;
                j++;
                if (tmpRet.size() == k) {
                    ret.clear();
                    ret.addAll(tmpRet);
                    return true;
                }
            } else {
                j++;
            }
        }
        return false;
    }

    @Override
    public String getProcessorName() {
        return "二分答案-礼物问题 -- 二分解法...";
    }
}
