package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors;

import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.datastructure.combination.Combinations;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSContext;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.NAIVE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(NAIVE)
public class LissNaiveProcessor implements LISSProcessor {
    @Override
    public void process(LISSContext lissContext) throws ProcessorException {
        if (lissContext.getSwitcher() != NAIVE) {
            System.out.println("不使用Liss的蛮力算法！");
            return;
        }
        List<Integer> items = lissContext.getItems();

    }

    @Override
    public String getProcessorName() {
        return "LISS 最长递增子序列 蛮力算法...";
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = Combinations.cnkGenerate(10, 4);
        System.out.println(lists);
    }
}
