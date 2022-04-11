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
            System.out.println("使用了蛮力计算");
            return;
        }
        List<Integer> items = zdyContext.getItems();
        int maxValue = 0;
        for (Integer item : items) {
            maxValue |= item;
        }
        int[] array = new int[32]; //位数为index的位数次数和
        int leftIndex = 0, rightIndex = 0, minLength = Integer.MAX_VALUE, tempMaxValue = 0;
        for (int l = 0, r = 0; r < items.size(); ) {
            int iValue = items.get(r);
            tempMaxValue |= iValue;
            addItemToArray(array, iValue);
            if (tempMaxValue == maxValue) {
                int tempLength = r - l + 1;
                if (tempLength < minLength) {
                    leftIndex = l;
                    rightIndex = r;
                    minLength = tempLength;
                }

                int lValue = items.get(l);
                tempMaxValue = deleteItemFromArray(array, lValue);
                /**
                 * l ==r时，那么肯定就出现了单个元素达到最大的情况。要更新下left与right index。
                 * 而且l还可以指向下一个不满足的位置
                 */
                while (tempMaxValue == maxValue) {//
                    l++;
                    lValue = items.get(l);
                    tempMaxValue = deleteItemFromArray(array, lValue);
                }

                //此时l指向了去掉这个元素就不等于maxValue的点
                //也就是满足条件的起始位置
                tempLength = r - l + 1;
                if (tempLength < minLength) {
                    leftIndex = l;
                    rightIndex = r;
                    minLength = tempLength;
                }
                //让它指向第一个不满足的点，因为下面要开始计算了。
                //tempMaxValue也进行了更新了。
                //此时l++ 就跟deleteItemFromArray的效果对上了。
                l++;//
            }
            r++; //正常遍历，还没有达到maxValue时，每次+1
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
