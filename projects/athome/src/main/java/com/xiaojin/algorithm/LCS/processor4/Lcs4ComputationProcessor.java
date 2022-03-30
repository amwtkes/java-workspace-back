package com.xiaojin.algorithm.LCS.processor4;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.LCS.processor4.Lcs4Priority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs4ComputationProcessor implements Lcs4Processor {
    @Override
    public void process(Lcs4Context lcs4Context) throws ProcessorException {
        lcs4Context.assertInputNotBeNull();
        int[][] dp = new int[lcs4Context.getItems().size()][lcs4Context.getItems().size()];
        initDpArray(lcs4Context, dp);
        for (int i = 0; i < lcs4Context.getItems().size(); i++) {
            for (int j = 0; j < lcs4Context.getItems().size(); j++) {
                computation(lcs4Context.getItems(), dp, i, j);
            }
        }

    }

    /**
     * 计算函数
     * <p>
     * i     可选值得索引最大值
     * j     不能超过的最大值的索引值。也就是后面一个元素的索引值。
     */
    private void computation(List<Integer> items, int[][] dp, int i, int j) {
        if (i < 0 || j < 0) {
            return;
        }
        /**
         * 如果i==j的含义是
         *
         * 可以选n个元素，但是还没有选任何一个的情况。也就是初始状态
         * 最终我们要求的dp[n][n]就是解。
         */
        if (i == 0 && j == 0) {
            dp[i][j] = 1;
            return;
        }
        /**
         * dp表的下半部分没有意义
         * 可选的索引最大值i 不可能超过最大值的索引。
         * j都会大于1
         */
        if (i > j) {
            return;
        }
        /**
         *  子问题的解 dp[0][1] 如果a[0]>=a[1] 不能取a[0] 所以等于0   如果a[0]<a[1]可以取a[0]故+1；
         *  */
        if (i == 0) {
            dp[i][j] = items.get(i) >= items.get(j) ? 0 : 1;
            return;
        }

        if (i == j) {
            /**
             * 选择这个元素与不选择这个元素
             */
            dp[i][j] = Math.max(dp[i - 1][i] + 1, dp[i - 1][j - 1]);
            return;
        }
        /**
         * 如果i的值跟j的值相等
         * 则直接跳过
         */
        if (items.get(i).equals(items.get(j))) {
            dp[i][j] = dp[i - 1][j];
            return;
        }

        if (items.get(i) > items.get(j)) {
            /**
             * 不需要包含dp[i-1][i-1]因为这个对称轴上的元素只能是从i==j这个分支下来的。不能包含在有前序节点的情况下，
             * 因为这样的话，在递归过程中可能会出现a[n]<a[i-1] n>i的情况，误认为要+1
             */
            dp[i][j] = dp[i - 1][j];
            return;
        }
        if (items.get(i) < items.get(j)) {
            dp[i][j] = Math.max(dp[i - 1][i] + 1, dp[i - 1][j]);
        }
    }

    private void initDpArray(Lcs4Context lcs4Context, int[][] dp) {
        lcs4Context.setDp(dp);

        for (int i = 0; i < lcs4Context.getItems().size(); i++) {
            for (int j = 0; j < lcs4Context.getItems().size(); j++) {
                dp[i][j] = 0;
            }
        }
    }

    @Override
    public String getProcessorName() {
        return "Lcs4 computation.";
    }
}
