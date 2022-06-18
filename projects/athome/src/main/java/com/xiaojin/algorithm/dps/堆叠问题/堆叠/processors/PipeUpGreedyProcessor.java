package com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors;

import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpContext;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.GREEDY;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(GREEDY)
public class PipeUpGreedyProcessor implements PipeUpProcessor {
    @Override
    public void process(PipeUpContext pipeUpContext) throws ProcessorException {
        if (pipeUpContext.getSwitcher() != GREEDY) {
            System.out.println("不使用greedy算法！");
            return;
        }
        List<Integer> items = pipeUpContext.getItems();
        if (!isOkForGreedy(items)) {
            System.out.println("这个数组不适合用贪心法！");
            return;
        }
        System.out.println("使用贪心法解决....");
    }

    /**
     * 必须是递增数列才能用贪心法！
     * 这种方法是递增子序列问题的一个特例。
     */
    private boolean isOkForGreedy(List<Integer> items) {
        int preValue = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (preValue > items.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getProcessorName() {
        return "堆叠问题-贪心法....";
    }
}
