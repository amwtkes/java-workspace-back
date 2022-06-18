package com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpContext;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.base.ContextHelper.binarySearch;
import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.END;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(END)
public class PipeUpEndWayProcessor implements PipeUpProcessor {
    /**
     * 这里求的是items是乱序情况下的，最大堆叠层数。
     * 跟最大自增子序列有很大的相似处，因为最大堆叠的子序列必然是递增子序列，
     * 而这里不过是加上了一个堆叠的限制。即：
     * 见面的i个元素的和必须小于第i+1个元素，否则即便a[i+1]>a[i]也不能形成子序列解。
     */
    @Override
    public void process(PipeUpContext pipeUpContext) throws ProcessorException {
        if (pipeUpContext.getSwitcher() != END) {
            System.out.println("不使用END方法！");
            return;
        }
        System.out.println("使用end方法！");
        List<Integer> items = pipeUpContext.getItems();
        /**
         * end[i]数组的含义：长度为i的子序列中，和最小的值。
         * 最小说明肯定能够得到一个最优解，虽然，最优解不一定唯一。
         *
         * 在正向遍历items数组的时候，不仅仅要求一个递增子序列，而且还要满足堆叠条件。
         * */
        ArrayList<Integer> end = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                end.add(items.get(i));
                continue;
            }
            ContextHelper.BinarySearchResult binarySearchResult = binarySearch(end, items.get(i));
            switch (binarySearchResult.getResultType()) {
                case NO_BIGGER:
                    end.add(end.get(end.size() - 1) + items.get(i));//这个元素比end数组中所有元素都大，也就是，所有已经形成堆叠的子序列和都没这个元素大，所以可以形成新的长度的子序列，而且和是最小的。
                    break;
                case NO_LESSER:
                    end.set(0, items.get(i));//更新长度为1的end数组的值，意义是，这个元素可以作为子序列的第一个。长度为1.
                    break;
                case NORMAL_MISS://没有找到这个item值，但是好消息是，它被夹在某两个值之间了，那么就更新后面这个end索引的值。表示i+1长度的最小值可以更新了，但是要比较。
                    int lastLessOneIndex = binarySearchResult.getLastLessOneIndex();
                    Integer lastLessValue = end.get(lastLessOneIndex);
                    int newSmallestSum = lastLessValue + items.get(i);
                    int prevSmallestSum = end.get(lastLessOneIndex + 1);
                    if (newSmallestSum < prevSmallestSum) { //end数组维护的时最小值。
                        end.set(lastLessOneIndex + 1, newSmallestSum);
                    }
                    break;
                default://ok 找到了。说明找到了一个正好可以堆叠的情况，end数组长度+1
                    int resultIndex = binarySearchResult.getResultIndex();
                    if (resultIndex == (end.size() - 1)) {//匹配了最后一个元素
                        end.add(end.get(end.size() - 1) + items.get(i));
                    } else {
                        int newValue = items.get(i) * 2;
                        if (newValue < end.get(resultIndex + 1)) {
                            end.set(resultIndex + 1, newValue);
                        }
                    }
            }
        }
        pipeUpContext.setResultNotFinish(end.size());
    }

    @Override
    public String getProcessorName() {
        return "堆叠问题-end方法...";
    }
}
