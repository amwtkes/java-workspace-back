package com.xiaojin.algorithm.knapsack;

import com.xiaojin.algorithm.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.knapsack.base.KnapsackPriority;
import com.xiaojin.algorithm.knapsack.base.KnapsackProcessor;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

@Component
@SortOrder(KnapsackPriority.INIT)
public class KnapsackInitProcessor implements KnapsackProcessor {
    @Override
    public void process(KnapsackContext knapsackContext) throws ProcessorException {
        knapsackContext.assertInputNotBeNull();
        String[] elements = knapsackContext.getInput().split(",");
        List<KnapsackContext.Item> items = new ArrayList<>(elements.length);
        for (String e : elements) {
            String[] itemStr = e.split(knapsackContext.getItemSeparator());
            if (itemStr.length != 2) {
                throw new ProcessorException("输入背包数据格式不正确！" + " string is:" + e);
            }
            items.add(new KnapsackContext.Item(Integer.parseInt(itemStr[0]), Integer.parseInt(itemStr[1])));
        }
        knapsackContext.setItems(items);
    }

    @Override
    public String getProcessorName() {
        return "背包问题数据初始化 - 10";
    }
}
