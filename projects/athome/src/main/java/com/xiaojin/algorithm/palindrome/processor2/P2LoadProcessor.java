package com.xiaojin.algorithm.palindrome.processor2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;
import static com.xiaojin.algorithm.palindrome.PalindromeHelper.addSharpToString;
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
            p2Context.setInput(addSharpToString(inputStringFromClassPathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "p2 loading...";
    }
}
