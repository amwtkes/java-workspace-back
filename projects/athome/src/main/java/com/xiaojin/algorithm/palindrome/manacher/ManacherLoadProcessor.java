package com.xiaojin.algorithm.palindrome.manacher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringFromClassPathFile;
import static com.xiaojin.algorithm.palindrome.PalindromeHelper.addSharpToString;
import static com.xiaojin.algorithm.palindrome.manacher.ManacherPriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManacherLoadProcessor implements ManacherProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(ManacherContext manacherContext) throws ProcessorException {
        try {
            String inputStringFromClassPathFile = getInputStringFromClassPathFile(resourceLoader, manacherContext.getInputDataPath());
            /**
             * 为啥要加#？
             * abba这种情况（偶数的回文），如果不加#，就找不到中心轴，加上以后
             * #a#b#b#a# 变成奇数 回文就有了中心轴 而且最后去掉#
             * 就会还原。
             */
            manacherContext.setInput(addSharpToString(inputStringFromClassPathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProcessorName() {
        return "Manacher loading....";
    }
}
