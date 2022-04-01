package com.xiaojin.algorithm.palindrome.processor2;

import com.xiaojin.algorithm.palindrome.PalindromeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.palindrome.processor2.P2Priority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P2ComputationProcessor implements P2Processor {
    @Override
    public void process(P2Context p2Context) throws ProcessorException {
        p2Context.assertInputNotBeNull();
        String sourceStr = p2Context.getInput();
        p2Context.setA(new int[sourceStr.length()]);
        int A[] = p2Context.getA();
        for (int i = 0; i < sourceStr.length(); i++) {
            //如果没有回文，那么A[i]==i
            if (i == 0) {
                A[i] = 0;
                continue;
            }
            if (i == 1) {
                A[i] = sourceStr.charAt(0) == sourceStr.charAt(1) ? 0 : 1;
                continue;
            }
            //i要跟A[i-1]的前一个元素去比较，才能扩展
            //如果A[i-1]==0 则[0 -> i-1]是一个回文，则只能判断[0->i]是否为回文，而不应该判断 a[A[i-1]-1] == a[i]因为溢出(只能退化成n^2的方法)
            if (A[i - 1] != 0 && sourceStr.charAt(i) == sourceStr.charAt(A[i - 1] - 1)) {
                A[i] = A[i - 1] - 1;
            } else {
                A[i] = computeAiWhenNotEqual(sourceStr, A[i - 1], i);
            }
        }

        int leftMax = 0, rightMax = 0, maxLength = 0;
        for (int i = 0; i < A.length; i++) {
            if ((i - A[i]) > maxLength) {
                maxLength = i - A[i];
                leftMax = A[i];
                rightMax = i;
            }
        }
        p2Context.setResultAndFinish(PalindromeHelper.deleteSharpOfAString(sourceStr.substring(leftMax, rightMax)));
    }

    /**
     * 求算在区间a[leftIndex->i]之间的最大回文
     */
    private int computeAiWhenNotEqual(String sourceStr, int leftIndex, int i) {
        //如果没有找到回文，A[i]=i就是自身
        if (leftIndex == i) {
            return i;
        }
        for (int j = leftIndex; j <= i; j++) {
            boolean isJOk = true;
            //判断j->i是否是回文 k与l相遇，或者k与l交换大小关系 都要停止
            for (int k = j, l = i; l > k; k++, l--) {
                if (sourceStr.charAt(k) != sourceStr.charAt(l)) {
                    isJOk = false;
                    break;
                }
            }
            if (isJOk) {
                return j;
            }
        }
        return i;
    }

    @Override
    public String getProcessorName() {
        return "Palindrome2 computing...";
    }
}
