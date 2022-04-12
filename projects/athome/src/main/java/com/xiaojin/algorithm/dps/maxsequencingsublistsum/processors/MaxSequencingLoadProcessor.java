package com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;
import static com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.M1ProcessorPriority.LOAD;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(LOAD)
public class MaxSequencingLoadProcessor implements MaxSequencingProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(MaxSequencingContext algorithmGeneralContext) throws ProcessorException {
        try {
            String inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, "MaxSequencingSublist/Data_Int.txt");
            algorithmGeneralContext.setInput(inputStringFromClassPathFile);
        } catch (IOException e) {
            throw new ProcessorException(e.getMessage());
        }
    }

    @Override
    public String getProcessorName() {
        return "加载测试数据";
    }
}
