package com.xiaojin.algorithm.palindrome.processor2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;
import static com.xiaojin.algorithm.palindrome.processor2.P2Priority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P2LoadProcessor implements P2Processor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(P2Context p2Context) throws ProcessorException {
        try {
            String inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, p2Context.getInputDataPath());
            //这个dp的方法因为没有用到中心向两侧扩展的过程，所以不用sharp填充
            p2Context.setInput(inputStringFromClassPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "p2 loading...";
    }
}
