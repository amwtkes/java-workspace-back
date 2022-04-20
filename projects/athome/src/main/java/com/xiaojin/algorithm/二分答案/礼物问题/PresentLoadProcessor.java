package com.xiaojin.algorithm.二分答案.礼物问题;

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
import static com.xiaojin.algorithm.二分答案.礼物问题.PresentPriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PresentLoadProcessor implements PresentProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(PresentContext presentContext) throws ProcessorException {
        try {
            List<String> inputStrings = getInputStringListFromClassPathFile(resourceLoader, presentContext.getInputDataPath());
            if (inputStrings.size() <= 0) {
                throw new ProcessorException("Input is empty!");
            }
            for (int i = 0; i < 2; i++) {
                ArrayList<Integer> lineItem = ContextHelper.splitter(inputStrings.get(i), Integer.MAX_VALUE);
                if (i == 0) {
                    presentContext.setItems(lineItem);
                } else {
                    presentContext.setNr(lineItem.get(0));
                }
            }
            if (presentContext.getItems().size() < presentContext.getNr()) {
                throw new ProcessorException("item no < nr!");
            }
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "二分答案-礼物问题 loading...";
    }
}
