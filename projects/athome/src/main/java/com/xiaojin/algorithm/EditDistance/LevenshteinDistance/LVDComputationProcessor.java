package com.xiaojin.algorithm.EditDistance.LevenshteinDistance;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.EditDistance.LevenshteinDistance.LVDPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LVDComputationProcessor implements LVDProcessor {
    @Override
    public void process(LVDContext lvdContext) throws ProcessorException {
        String firstStr = lvdContext.getFirstLine();
        String secondStr = lvdContext.getSecondLine();
        int[][] dp = new int[firstStr.length()][secondStr.length()];
        lvdContext.setDp(dp);

        /**
         * dp[i][j] = min{dp[i-1][j-1]+1,dp[i-1][j]+1,dp[i][j-1]+1}
         * i j从大到小递归
         * 所以dp的方式，是从小到大for循环
         */
        boolean flagI = false; //如果i==0已经被匹配了，则设置成ture
        boolean flagJ = false; //如果j==0已经被匹配了，则设置成ture
        for (int i = 0; i < firstStr.length(); i++) {
            for (int j = 0; j < secondStr.length(); j++) {
                if (i == 0) { //i位置不能进行替换，删除操作，只能在a0后面加上bj来保持i的指针不动
                    if (j == 0) {
                        if (firstStr.charAt(i) == secondStr.charAt(j)) {
                            dp[i][j] = 0;
                            flagI = true;
                            flagJ = true;
                        } else {
                            dp[i][j] = 1;
                        }
                    } else {
                        /**
                         * 如果a0 == b0 那么dp[0][j]子问题的解都是删除操作即可也就是：dp[0][0] == 0 ? dp[i][j - 1] + 1
                         * 如果a[0] != b[0] 那么还要判断a[0]与b[j]的大小关系.
                         * 所以，如果flagI为T，则完成了匹配，后面的bj都可以是+1
                         * 否则，还要匹配a[0]与b[j]的关系来设置flagI
                         */
                        if (!flagI && firstStr.charAt(i) == secondStr.charAt(j)) {
                            flagI = true;
                            dp[i][j] = dp[i][j - 1];
                        } else {
                            dp[i][j] = dp[i][j - 1] + 1;
                        }
                    }
                }
                if (i > 0) {//说明i=0的所有情况都做完了
                    if (j == 0) {//跟i==0的情况一样，处理这个边界情况。从a[n] n>1开始
                        if (!flagJ && firstStr.charAt(i) == secondStr.charAt(j)) {
                            flagJ = true;
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            dp[i][j] = dp[i - 1][j] + 1;
                        }
                    }
                    if (j > 0) {
                        if (firstStr.charAt(i) == secondStr.charAt(j)) {
                            dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                        } else {
                            dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                        }
                    }
                }
            }
        }

        lvdContext.setResultAndFinish(new LVDContext.LVDResult(dp[firstStr.length() - 1][secondStr.length() - 1]));
    }

    @Override
    public String getProcessorName() {
        return "LVD computing...";
    }
}
