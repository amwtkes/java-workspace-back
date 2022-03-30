package com.xiaojin.algorithm.LCS.processor4;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;

import static com.xiaojin.algorithm.LCS.processor4.Lcs4Priority.INIT;
import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;

@Component
@SortOrder(INIT)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs4LoadProcessor implements Lcs4Processor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(Lcs4Context lcs4Context) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, lcs4Context.getDataFilePath());
            lcs4Context.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(lcs4Context, Integer.MAX_VALUE);
            lcs4Context.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "Lcs4 initiating.";
    }
}
