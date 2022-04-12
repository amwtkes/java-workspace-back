package com.xiaojin.algorithm.dps.EditDistance.LevenshteinDistance;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.List;

import static com.xiaojin.algorithm.dps.EditDistance.LevenshteinDistance.LVDPriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LVDLoadProcessor implements LVDProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(LVDContext lvdContext) throws ProcessorException {
        try {
            List<String> strings = ContextHelper.getInputStringListFromClassPathFile(resourceLoader, lvdContext.getInputDataPath());
            if (strings.size() >= 2) {
                lvdContext.setFirstLine(strings.get(0));
                lvdContext.setSecondLine(strings.get(1));
            } else {
                throw new ProcessorException("Wrong input data! filePath:" + lvdContext.getInputDataPath());
            }
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "LVD loading...";
    }
}
