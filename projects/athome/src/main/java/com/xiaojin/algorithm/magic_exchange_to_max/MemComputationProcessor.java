package com.xiaojin.algorithm.magic_exchange_to_max;

import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.magic_exchange_to_max.MemPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemComputationProcessor implements MemProcessor {
    @Override
    public void process(MemContext memContext) throws ProcessorException {
        memContext.assertInputNotBeNull();
        List<Integer> items = memContext.getItems();
        int[] A = new int[items.size()]; //从左到有包含a[i]的向前最大连续子串和
        int[] B = new int[items.size()]; //从右到左包含a[i]的向后最大连续子串和
        computeA(items, A);
        computeB(items, B);
        //left为max(A[0->i])的最大值的索引位置
        //right为left取最大值时候的B的起始位置
        //aMax为A[0->i]取最大值的时候的索引
        int aMaxIndex = 0, totalMax = 0, leftExchangeIndex = 0, rightExchangeIndex = 0;
        for (int i = 0; i < items.size() - 1; i++) {
            if (i == 0) {
                totalMax = A[aMaxIndex] + B[1];
                continue;
            }
            if (A[i] > aMaxIndex) {
                aMaxIndex = i;
            }
            int tempTotal = A[aMaxIndex] + B[i + 1];
            if (tempTotal > totalMax) {
                totalMax = tempTotal;
                rightExchangeIndex = i;
                leftExchangeIndex = getLeftIndexWhenMaxA(items, A, aMaxIndex);
            }
        }
        memContext.setResultAndFinish(new Pair<>(leftExchangeIndex, rightExchangeIndex));
    }

    private int getLeftIndexWhenMaxA(List<Integer> items, int[] a, int aMaxIndex) {
        int maxValue = a[aMaxIndex];
        int left = 0;
        for (int i = aMaxIndex; maxValue != 0; i--) {
            maxValue -= items.get(i);
            left = i;
        }
        return left;
    }

    private void computeB(List<Integer> items, int[] b) {
        int maxIndex = items.size() - 1;
        for (int i = maxIndex; i >= 0; i--) {
            if (i == maxIndex) {
                b[i] = items.get(maxIndex);
                continue;
            }
            b[i] = Math.max(b[i + 1] + items.get(i), items.get(i));
        }
    }

    private void computeA(List<Integer> items, int[] a) {
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                a[i] = items.get(i);
                continue;
            }
            a[i] = Math.max(a[i - 1] + items.get(i), items.get(i));
        }
    }

    @Override
    public String getProcessorName() {
        return "魔法逆序的最大子串和问题 computing...";
    }
}
