package com.xiaojin.algorithm.LCS.processor3;

import com.xiaojin.algorithm.LCS.base.Lcs2Context;
import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;

import static com.xiaojin.algorithm.LCS.processor3.Lcs3Priority.LOAD;
import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs3LoadProcessor implements Lcs3Processor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(Lcs2Context lcs2Context) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, "lcs/1.txt");
            lcs2Context.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(lcs2Context, Integer.MAX_VALUE);
            lcs2Context.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "Lcs3 loading.";
    }
}
