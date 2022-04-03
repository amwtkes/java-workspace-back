package com.xiaojin.algorithm.TopK;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.TopK.TkPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TkComputationProcessor implements TkProcessor {
    @Override
    public void process(TkContext tkContext) throws ProcessorException {
        tkContext.assertInputNotBeNull();
        List<Integer> items = tkContext.getItems();
        List<Integer> topKList = new ArrayList<>();
        computation(items, tkContext.getTopK(), topKList);
        tkContext.setResultAndFinish(topKList);
    }

    private void computation(List<Integer> items, int topK, List<Integer> ret) throws ProcessorException {
        if (topK == 0) {
            return;
        }
        if (items.size() < topK) {
            throw new ProcessorException("Topk大于输入数组长度！topK:" + topK + " itemLength:" + items.size());
        }
        if (items.size() == topK) {
            ret = items;
        }
        computeInner(items, 0, items.size() - 1, topK, ret);
    }

    private void computeInner(List<Integer> items, int lo, int hi, int topK, List<Integer> ret) {
        if (lo == hi) {
            ret.add(items.get(lo));
            return;
        }
        int midIndex = (hi - lo) / 2 + lo;
        int midValue = items.get(midIndex);
        int rightIndex = hi, leftIndex = lo;
        while (leftIndex < rightIndex) {
            //左侧是小于，因为如果等于也算作要交换到右侧的值
            while (items.get(leftIndex) < midValue && leftIndex < rightIndex) {
                leftIndex++;
            }
            //等于midValue的都留在右侧
            while (items.get(rightIndex) >= midValue && leftIndex < rightIndex) {
                rightIndex--;
                //left可能还没有交换,跟midvalue交换下
                if (leftIndex == rightIndex) {
                    swap(items, midIndex, leftIndex);
                }
            }
            if (leftIndex == rightIndex) {
                break;
            }
            swap(items, leftIndex, rightIndex);
        }
        if (items.get(rightIndex) < midValue) {
            rightIndex++;
        } else {
            /*
             * leftIndex如果没有移动过，RightIndex会向左靠拢直到跟leftIndex相等，这时候--会导致leftIndex<lo
             */
            leftIndex--;
        }

        int rightNr = hi - rightIndex + 1;
        if (rightNr > topK) {
            if (leftIndex < lo) {
                /*
                 * leftIndex没有移动，由于[lo,hi]所有的元素都>=midValue导致，那么因为
                 * rightIndex肯定不是最大值，所以right++没问题
                 * 因为如果lo指向最大值，那么leftIndex不会动，那么会被第二个while交换掉。
                 */
                computeInner(items, ++rightIndex, hi, topK, ret);
            } else {
                computeInner(items, rightIndex, hi, topK, ret);
            }
        } else {
            for (int i = rightIndex; i <= hi; i++) {
                ret.add(items.get(i));
            }
            if (rightNr < topK) {
                int newTopK = topK - rightNr;
                computeInner(items, lo, leftIndex, newTopK, ret);
            }
        }
    }

    private void swap(List<Integer> items, int i, int j) {
        int temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }

    @Override
    public String getProcessorName() {
        return "Top K computing...";
    }
}
