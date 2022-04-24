package com.xiaojin.algorithm.dps.palindrome.processor3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.dps.palindrome.processor3.P3Priority.COMPUTATION;

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
        int n = sourceStr.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //i==j 表示本身这个字符串，自己形成一个回文
                dp[i][j] = i == j;
            }
        }
        int maxLength = 0, maxLeftIndex = 0, maxRightIndex = 0;
        /**
         * dp[i][j]表示是否i到j是回文= true or false
         *
         * dp[i][j]= dp[i+1][j-1] && a[i]==a[j]
         *
         * 1 所以i应该从大到小计算(因为下一项比前一项大，所以要反着遍历才能命中缓存，先算大的，再算小的)
         * 2 j应该从小到大计算（因为递归的下一项比前一项要小。由y->y-1）
         * 3 i<j 否则无效 （所以j初始化的时候应该=i）
         */
        for (int i = sourceStr.length() - 1; i >= 0; i--) {
            for (int j = i; j < sourceStr.length(); j++) {
                if (i == j) {
                    dp[i][j] = true;
                    continue;
                }
                boolean flag = sourceStr.charAt(i) == sourceStr.charAt(j);
                /**
                 * 因为dp[i][j]= dp[i+1][j-1] && a[i]==a[j] 而且i应该在j的前面才有意义 i<=j
                 * 所以 如果i+1 >j-1 说明i与j挨着了，这时只要比较a[i]？= a[j]即可
                 */
                dp[i][j] = i + 1 > j - 1 ? flag : flag && dp[i + 1][j - 1];
                if (dp[i][j]) {
                    int tempMaxLength = j - i + 1;
                    if (tempMaxLength > maxLength) {
                        maxLength = tempMaxLength;
                        maxLeftIndex = i;
                        maxRightIndex = j;
                    }
                }
            }
        }
        p3Context.setResultAndFinish(sourceStr.substring(maxLeftIndex, maxRightIndex + 1));
    }

    @Override
    public String getProcessorName() {
        return "Palindrome3 computation....";
    }
}
