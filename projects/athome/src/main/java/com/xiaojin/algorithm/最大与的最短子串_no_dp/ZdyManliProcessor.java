package com.xiaojin.algorithm.最大与的最短子串_no_dp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.最大与的最短子串_no_dp.ZdyPriority.COMPUTATION_MANLI;

@Component
@SortOrder(COMPUTATION_MANLI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZdyManliProcessor implements ZdyProcessor {
    @Override
    public void process(ZdyContext zdyContext) throws ProcessorException {
        if (zdyContext.getSwitcher() != COMPUTATION_MANLI) {
            return;
        }

        List<Integer> items = zdyContext.getItems();
        int minLength = Integer.MAX_VALUE;
        int maxValue = -1;
        int leftMinIndex = 0, rightMinIndex = 0;
        for (int i = 0; i < items.size(); i++) {
            int tempMax = 0;
            for (int j = i; j < items.size(); j++) {
                if (i == j) {
                    tempMax = items.get(i);
                } else {
                    tempMax |= items.get(j);
                }
                if (tempMax >= maxValue) { //取到新的最大值
                    int tempMinLength = j - i + 1;
                    if (tempMax > maxValue) {
                        minLength = tempMinLength;
                        leftMinIndex = i;
                        rightMinIndex = j;
                        maxValue = tempMax;
                        continue;
                    }

                    //如果等于maxValue
                    if (tempMinLength < minLength) {
                        minLength = tempMinLength;
                        leftMinIndex = i;
                        rightMinIndex = j;
                    }

                    //skip掉那些tempMax == maxValue 但是长度没有缩短的情况
                }
            }
        }
        List<Integer> ret = items.subList(leftMinIndex, rightMinIndex + 1);
        zdyContext.setResultNotFinish(ret);
    }

    @Override
    public String getProcessorName() {
        return "最大与最短子串 蛮力计算...";
    }
}
