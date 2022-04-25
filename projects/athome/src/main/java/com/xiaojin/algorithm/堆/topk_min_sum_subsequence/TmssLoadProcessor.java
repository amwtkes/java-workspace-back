package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringListFromClassPathFile;
import static com.xiaojin.algorithm.base.ContextHelper.intArrayToString;
import static com.xiaojin.algorithm.堆.topk_min_sum_subsequence.TmssPriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TmssLoadProcessor implements TmssProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(TmssContext tmssContext) throws ProcessorException {
        if (tmssContext.getItems() != null && tmssContext.getItems().size() > 0) {
            System.out.println("items->" + intArrayToString(tmssContext.getItems()));
            return;
        }
        try {
            List<String> inputStrings = getInputStringListFromClassPathFile(resourceLoader, tmssContext.getInputDataPath());
            if (inputStrings.size() <= 0) {
                throw new ProcessorException("Input is empty!");
            }
            for (int i = 0; i < 2; i++) {
                ArrayList<Integer> lineItem = ContextHelper.splitter(inputStrings.get(i), Integer.MAX_VALUE);
                if (i == 0) {
                    tmssContext.setItems(lineItem);
                } else {
                    int nr = lineItem.get(0);
                    tmssContext.setK(nr);
                }
            }
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "前k小子序列和 loading...";
    }
}
