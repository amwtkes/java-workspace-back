package com.xiaojin.algorithm.LCS.processors1;

import com.xiaojin.algorithm.LCS.base.Lcs1Context;
import com.xiaojin.algorithm.LCS.base.Lcs1Processor;
import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;

import static com.xiaojin.algorithm.LCS.processors1.Lcs1Priority.LOAD;
import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs1LoadProcessor implements Lcs1Processor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(Lcs1Context lcs1Context) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, "lcs/1.txt");
            lcs1Context.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(lcs1Context, Integer.MAX_VALUE);
            lcs1Context.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getProcessorName() {
        return "Lcs 1 Loading";
    }
}
