package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static com.xiaojin.algorithm.堆.topk_min_sum_subsequence.TmssPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
/**
 * 1- 树的结构跟二项式系数的增长是一致的。Cn1+Cn2+Cn3+....+Cnn = 2^n-1
 * 2- 树的第n层表示包含第n个元素的所有排列组合。第n层的节点数为2^(n-1)个= 第n-1层的所有组合(即包含n-1这个元素的所有组合)+ 不包含n-1的所有组合 + 只包含第n个元素的组合
 * 3- 如何构造这课树？根据2这个公式
 * 3.1 - 将第n-1层的所有组合(即包含n-1这个元素的所有组合).将第n-1层的每个元素后面+上n这个元素，构成新的子序列
 * 3.2 - 将第n-1层所有组合最后一个元素去掉，也就是去掉n-1这个元素后（就是2这个公式上的不包含n-1这个元素的所有集合） +上第n这个元素
 * 4. - 这是一个小顶堆，可以在lgn的时间内完成插入操作
 * 5. - 只要弹出一个，并将这个元素的index按照3这个算法，添加两个进堆，循环k次，即可得解。
 * 6. 时间复杂度nlogn + k*logn = nlogn
 */
public class TmssComputationProcessor implements TmssProcessor {
    @Override
    public void process(TmssContext tmssContext) throws ProcessorException {
        if (tmssContext.getSwitcher() != COMPUTATION) {
            System.out.println("不走堆解法");
            return;
        }
        List<Integer> items = tmssContext.getItems();
        int k = tmssContext.getK();
        List<Integer> sortedItems = items.stream().sorted().collect(Collectors.toList());
        PriorityQueue<HeapElement> integerPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(HeapElement::getSubSequenceSum));
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        integerPriorityQueue.add(new HeapElement(0, sortedItems.get(0)));
        while (answer.size() != k) {
            HeapElement smallest = integerPriorityQueue.remove();
            int smallestIndex = smallest.getLastIndex();
            answer.add(smallest.getSubSequenceSum());
            int nextIndex = smallest.getLastIndex() + 1;
            /**
             * 无法再分裂，说明已经到最后一层了
             */
            if (nextIndex < sortedItems.size()) {
                //在n-1后面加
                integerPriorityQueue.add(new HeapElement(nextIndex, smallest.getSubSequenceSum() + sortedItems.get(nextIndex)));
                //将n-1替换成n
                integerPriorityQueue.add(new HeapElement(nextIndex, smallest.getSubSequenceSum() - sortedItems.get(smallestIndex) + sortedItems.get(nextIndex)));
            }
        }
        tmssContext.setResultAndFinish(answer);
    }

    @Override
    public String getProcessorName() {
        return "前k小子序列和 堆算法...";
    }

    @Data
    @AllArgsConstructor
    /**
     * 分裂的规则：
     * 假设heap中弹出的元素是index:[.....a,b,c] - 表示的是以index=c的元素结尾的m个数相加的和，m个数就是一个子序列
     * 分裂成：加入index = d
     * 1. 包含c以及之前index的子序列 [.....a,b,c,d]
     * 2. 不包含c的之前index子序列[......a,b,d]
     * 可见，这里只跟最后一个元素的index c相关，所以前面m-1个元素不用存储。只要存储他们的和即可。
     */
    private static class HeapElement {
        private int lastIndex; //Heap的节点是每一个排列组合的子序列，这里表示这个节点子序列的最后一个元素的index
        private int subSequenceSum; //这个子序列的累加和
    }
}
