package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xiaojin.algorithm.堆.topk_min_sum_subsequence.TmssPriority.NAIVE;

@Component
@SortOrder(NAIVE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TmssNaiveProcessor implements TmssProcessor {
    @Override
    public void process(TmssContext tmssContext) throws ProcessorException {
        if (tmssContext.getSwitcher() != NAIVE) {
            System.out.println("没有用蛮力算法");
            return;
        }
        List<Integer> items = tmssContext.getItems();
        int k = tmssContext.getK();
        List<Integer> ans = new ArrayList<>();
        compute(items, 0, 0, ans);
        ans = ans.stream().sorted().collect(Collectors.toList());
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ret.add(ans.get(i));
        }
        tmssContext.setResultNotFinish(ret);
    }

    /**
     * 最小集合是空集。
     * 每个元素要不加入到集合中，要不就不加入。
     * 所以。可以按照这个加入还是不加入进行递归。
     * 最后当跑到最后的元素就终止，向上返回，走另外的分叉。
     * O(2^n)个组合
     *
     * @param items 元素集合
     */
    private void compute(List<Integer> items, int index, int sum, List<Integer> ans) {
        //有意向后越界表示终止。目的是把size-1这最后一个元素也纳入到计算中。
        if (index == items.size()) {
            ans.add(sum);
        } else {
            compute(items, index + 1, sum, ans); //不要当前元素
            compute(items, index + 1, sum + items.get(index), ans); //要当前元素
        }
    }

    @Override
    public String getProcessorName() {
        return "前k小子序列和  naiving...";
    }
}
