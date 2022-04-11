package com.xiaojin.algorithm.最大与的最短子串_no_dp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.最大与的最短子串_no_dp.ZdyPriority.COMPUTATION;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(COMPUTATION)
public class ZdyComputationProcessor implements ZdyProcessor {
    @Override
    public void process(ZdyContext zdyContext) throws ProcessorException {
        if (zdyContext.getSwitcher() != COMPUTATION) {
            return;
        }
        List<Integer> items = zdyContext.getItems();
        int maxValue = 0;
        for (Integer item : items) {
            maxValue |= item;
        }
        int[] array = new int[32]; //位数为index的位数次数和
        for (int i = 0; i < items.size(); i++) {
            array[i] = 0;
        }
        int leftIndex = 0, rightIndex = 0, minLength = Integer.MAX_VALUE, tempMaxValue = 0;
        for (int i = 0; i < items.size(); i++) {
            rightIndex = i;
            Integer item = items.get(i);
            tempMaxValue |= item;
            addItemToArray(array, item);
            /*
             * 顺着来遇到了等于maxValue的index
             * 如果遇到maxValue
             * 1、删除最左边起始位置的元素，i -> j  => i+1 -> j
             * 2、看看删除后的 i+1 -> j是否还等于maxValue
             * 3、如果等于，则继续删除，
             * 4、如果不等于，则停止，然后更新如果得到长度更短的解，就更新left与right index
             */

            if (tempMaxValue == maxValue) {
                for (int j = leftIndex; j <= i; j++) {
                    int tempItem = items.get(j);
                    if (deleteItemFromArray(array, tempItem) == maxValue) {
                        continue;
                    }
                    addItemToArray(array, tempItem);//删除最后不相等的那个item要重新加回来。
                    leftIndex = j;
                    break;
                }
                int tempLength = rightIndex - leftIndex + 1;
                if (tempLength < minLength) {
                    minLength = tempLength;
                }
            }
        }

        List<Integer> ret = items.subList(leftIndex, rightIndex + 1);
        zdyContext.setResultAndFinish(ret);
    }

    private void addItemToArray(int[] array, Integer item) {
        for (int i = 0; i < array.length; i++) {
            array[i] += (item & 1 << i) == 0 ? 0 : 1;
        }
    }

    private int deleteItemFromArray(int[] array, Integer item) {
        for (int i = 0; i < array.length; i++) {
            array[i] -= (item & 1 << i) == 0 ? 0 : 1;
        }
        return computeArray(array);
    }

    private int computeArray(int[] array) {
        int ret = 0;
        for (int i = 0; i < array.length; i++) {
            ret += array[i] > 0 ? 1 << i : 0;
        }
        return ret;
    }

    @Override
    public String getProcessorName() {
        return "最大与最短子串 巧妙解...";
    }
}
