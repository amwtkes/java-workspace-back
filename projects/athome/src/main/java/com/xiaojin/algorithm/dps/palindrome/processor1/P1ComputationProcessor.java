package com.xiaojin.algorithm.dps.palindrome.processor1;

import com.xiaojin.algorithm.dps.palindrome.PalindromeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.dps.palindrome.processor1.P1Priority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P1ComputationProcessor implements P1Processor {
    @Override
    public void process(P1Context p1Context) throws ProcessorException {
        p1Context.assertInputNotBeNull();
        String sourceStr = p1Context.getInput();
        int maxLength = 0, maxLeftIndex = -1, maxRightIndex = -1;
        for (int i = 0; i < sourceStr.length(); i++) {
            //到边界，边界不可能
            if (i == 0 || i == sourceStr.length() - 1) {
                continue;
            }
            int iLeft, iRight;//初始化左右的最长边界的索引。
            for (int j = 1; j < sourceStr.length(); j++) {
                int leftIndex = i - j, rightIndex = i + j;
                //越界了，终止
                if (leftIndex < 0 || rightIndex >= sourceStr.length()) {
                    break;
                }
                if (sourceStr.charAt(leftIndex) == sourceStr.charAt(rightIndex)) {
                    iLeft = leftIndex;
                    iRight = rightIndex;
                } else {
                    break;
                }
                int iMaxLength = iRight - iLeft + 1;
                if (iMaxLength > maxLength) {
                    maxLength = iMaxLength;
                    maxLeftIndex = iLeft;
                    maxRightIndex = iRight;
                }
            }
        }
        String ret = PalindromeHelper.deleteSharpOfAString(sourceStr.substring(maxLeftIndex, maxRightIndex));
        p1Context.setResultAndFinish(ret);
    }

    @Override
    public String getProcessorName() {
        return "Palindrome1 computing...";
    }
}
