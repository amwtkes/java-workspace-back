package com.xiaojin.algorithm.dps.magic_exchange_to_max;

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
import static com.xiaojin.algorithm.dps.magic_exchange_to_max.MemPriority.LOAD;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(LOAD)
public class MemLoadProcessor implements MemProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(MemContext memContext) throws ProcessorException {
        String inputStringFromClassPathFile = null;
        try {
            inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, memContext.getInputDataPath());
            memContext.setInput(inputStringFromClassPathFile);
            ArrayList<Integer> items = ContextHelper.splitter(memContext, Integer.MAX_VALUE);
            memContext.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "魔法逆序的最大子串和问题 loading...";
    }
}
