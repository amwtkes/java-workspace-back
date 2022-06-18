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
