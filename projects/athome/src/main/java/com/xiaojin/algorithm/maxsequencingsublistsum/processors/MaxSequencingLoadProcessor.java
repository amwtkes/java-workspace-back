package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.*;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.LOAD;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(LOAD)
public class MaxSequencingLoadProcessor implements MaxSequencingProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(AlgorithmGeneralContext algorithmGeneralContext) throws ProcessorException {
        Resource resource = resourceLoader.getResource("classpath:MaxSequencingSublist/Data.txt");
        try {
            File file = resource.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str;
            StringBuilder stringBuffer = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
            }
            algorithmGeneralContext.setInput(stringBuffer.toString());
        } catch (
                IOException e) {
            throw new ProcessorException(e.getMessage());
        }

    }

    @Override
    public String getProcessorName() {
        return "加载测试数据";
    }
}
