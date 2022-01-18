package com.xiaojin.algorithm.knapsack;

import com.xiaojin.algorithm.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.knapsack.base.KnapsackPriority;
import com.xiaojin.algorithm.knapsack.base.KnapsackProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;

@Component
@SortOrder(KnapsackPriority.LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KnapsackLoaderProcessor implements KnapsackProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(KnapsackContext knapsackContext) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, "knapsack/items.txt");
            knapsackContext.setInput(inputStringFromClassPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "加载背包数据 - 1";
    }
}
