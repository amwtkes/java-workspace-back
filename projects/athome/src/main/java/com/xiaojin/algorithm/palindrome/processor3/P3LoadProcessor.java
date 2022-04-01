package com.xiaojin.algorithm.palindrome.processor3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;
import static com.xiaojin.algorithm.palindrome.PalindromeHelper.addSharpToString;
import static com.xiaojin.algorithm.palindrome.processor3.P3Priority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P3LoadProcessor implements P3Processor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(P3Context p3Context) throws ProcessorException {
        try {
            String inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, p3Context.getInputDataPath());
            //sharp处理原始字符串，长度变成奇数，同时不影响最后的结果。
            p3Context.setInput(addSharpToString(inputStringFromClassPathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "Palindrome3 loading...";
    }
}
