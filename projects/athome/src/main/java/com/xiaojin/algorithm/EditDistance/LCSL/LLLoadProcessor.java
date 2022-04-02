package com.xiaojin.algorithm.EditDistance.LCSL;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.List;

import static com.xiaojin.algorithm.EditDistance.LCSL.LLPriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LLLoadProcessor implements LLProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(LLContext llContext) throws ProcessorException {
        try {
            List<String> strings = ContextHelper.getInputStringListFromClassPathFile(resourceLoader, llContext.getInputDataPath());
            if (strings.size() >= 2) {
                llContext.setFirstLine(strings.get(0));
                llContext.setSecondLine(strings.get(1));
            } else {
                throw new ProcessorException("Wrong input data! filePath:" + llContext.getInputDataPath());
            }
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "最长公共子序列 loading...";
    }
}
