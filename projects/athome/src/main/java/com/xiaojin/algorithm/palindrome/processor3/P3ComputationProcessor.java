package com.xiaojin.algorithm.palindrome.processor3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.palindrome.processor3.P3Priority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P3ComputationProcessor implements P3Processor {
    @Override
    public void process(P3Context p3Context) throws ProcessorException {
        p3Context.assertInputNotBeNull();
        String sourceStr = p3Context.getInput();
        p3Context.setDp(new boolean[sourceStr.length()][sourceStr.length()]);
        boolean[][] dp = p3Context.getDp();

        for (int i = 0; i < sourceStr.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < sourceStr.length(); i++) {

        }
    }

    @Override
    public String getProcessorName() {
        return "Palindrome3 computation....";
    }
}
