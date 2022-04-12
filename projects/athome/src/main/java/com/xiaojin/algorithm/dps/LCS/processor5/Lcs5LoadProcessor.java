package com.xiaojin.algorithm.dps.LCS.processor5;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;

import static com.xiaojin.algorithm.dps.LCS.processor5.Lcs5Priority.LOAD;
import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs5LoadProcessor implements Lcs5Processor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(Lcs5Context lcs5Context) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, lcs5Context.getDataFilePath());
            lcs5Context.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(lcs5Context, Integer.MAX_VALUE);
            lcs5Context.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "Lcs5 initiating.";
    }
}
