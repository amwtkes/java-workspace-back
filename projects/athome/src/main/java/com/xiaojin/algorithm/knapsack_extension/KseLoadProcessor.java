package com.xiaojin.algorithm.knapsack_extension;

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
import static com.xiaojin.algorithm.knapsack_extension.KsePriority.LOAD;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(LOAD)
public class KseLoadProcessor implements KseProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(KseContext kseContext) throws ProcessorException {
        try {
            List<String> inputStrings = getInputStringListFromClassPathFile(resourceLoader, kseContext.getInputDataPath());
            if (inputStrings.size() <= 0) {
                throw new ProcessorException("Input is empty!");
            }
            int askNr = 0;
            for (String line : inputStrings) {
                ArrayList<Integer> lineItem = ContextHelper.splitter(line, Integer.MAX_VALUE);
                if (askNr > 0) {
                    KseContext.Ask ask = new KseContext.Ask(lineItem.get(1), lineItem.get(0));
                    kseContext.getAsks().add(ask);
                    continue;
                }
                if (lineItem.size() == 1) {
                    askNr = lineItem.get(0);
                    kseContext.setAsks(new ArrayList<>(askNr));
                    continue;
                }
                KseContext.Item item = new KseContext.Item(lineItem.get(0), lineItem.get(1));
                kseContext.getItems().add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "背包扩展问题 loading";
    }
}
