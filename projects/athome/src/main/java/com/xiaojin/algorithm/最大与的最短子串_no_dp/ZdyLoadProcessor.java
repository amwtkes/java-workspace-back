package com.xiaojin.algorithm.最大与的最短子串_no_dp;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;
import static com.xiaojin.algorithm.最大与的最短子串_no_dp.ZdyPriority.LOAD;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(LOAD)
public class ZdyLoadProcessor implements ZdyProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(ZdyContext zdyContext) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, zdyContext.getInputDataPath());
            zdyContext.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(zdyContext, Integer.MAX_VALUE);
            zdyContext.setItems(items);
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "最大与最短子串 loading...";
    }
}
