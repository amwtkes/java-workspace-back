package com.xiaojin.algorithm.reverse_pair;

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
import static com.xiaojin.algorithm.reverse_pair.RpPriority.LOAD;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(LOAD)
public class RpLoadProcessor implements RpProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(RpContext rpContext) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, rpContext.getInputDataPath());
            rpContext.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(rpContext, Integer.MAX_VALUE);
            rpContext.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "逆序对 loading...";
    }
}
