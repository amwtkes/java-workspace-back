package com.xiaojin.algorithm.palindrome.manacher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.palindrome.manacher.ManacherPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManacherComputeProcessor implements ManacherProcessor {
    @Override
    public void process(ManacherContext manacherContext) throws ProcessorException {

    }

    @Override
    public String getProcessorName() {
        return "Manacher computing...";
    }
}
