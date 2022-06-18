package com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors;

import com.xiaojin.algorithm.datastructure.combination.Combinations;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpContext;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.NAIVE;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(NAIVE)
public class PipeUpNaiveProcessor implements PipeUpProcessor {
    /**
     * 这里求的是items是乱序情况下的，最大堆叠层数。
     * 跟最大自增子序列有很大的相似处，因为最大堆叠的子序列必然是递增子序列，
     * 而这里不过是加上了一个堆叠的限制。即：
     * 见面的i个元素的和必须小于第i+1个元素，否则即便a[i+1]>a[i]也不能形成子序列解。
     * */
    @Override
    public void process(PipeUpContext pipeUpContext) throws ProcessorException {
        if (pipeUpContext.getSwitcher() != NAIVE) {
            System.out.println("不使用naive方法！");
            return;
        }
        System.out.println("使用naive方法！");
        List<Integer> items = pipeUpContext.getItems();
        int maxLength = 0;
        for (int i = 1; i <= items.size(); i++) {
            List<List<Integer>> indexes = Combinations.cnkGenerate(items.size() - 1, i);
            for (List<Integer> index : indexes) {
                if (isOk(index, items) && maxLength < index.size()) {
                    maxLength = index.size();
                }
            }
        }
        pipeUpContext.setResultNotFinish(maxLength);
    }

    private boolean isOk(List<Integer> indexList, List<Integer> items) {
        int sum = -1;
        for (Integer index : indexList) {
            int nowValue = items.get(index);
            if (nowValue > sum) {
                sum += nowValue;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getProcessorName() {
        return "堆叠问题-蛮力算法...";
    }

    public static void main(String[] args) {
        //可以
        List<List<Integer>> lists = Combinations.cnkGenerate(5, 6);
        System.out.println(lists);
    }
}
