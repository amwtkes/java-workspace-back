package com.xiaojin.algorithm.TopK;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;

import static com.xiaojin.algorithm.TopK.TkPriority.LOAD;
import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TkLoadProcessor implements TkProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(TkContext tkContext) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, tkContext.getInputDataPath());
            tkContext.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(tkContext, Integer.MAX_VALUE);
            tkContext.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "Top K loading...";
    }
}
