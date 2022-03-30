package com.xiaojin.algorithm.LCS.processor4;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
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
        List<Integer> ret = markFunction(lcs4Context.getItems(), dp);
        lcs4Context.setResultAndFinish(ret);
    }

    private List<Integer> markFunction(List<Integer> items, int[][] dp) {
        int length = items.size();
        int maxValue = dp[length - 1][length - 1];
        List<Integer> ret = new ArrayList<>(maxValue);
        markInner(ret, items, dp, length - 1, length - 1);
        return ret;
    }

    /**
     * mark函数标记函数的作用就是在取得最大值得情况下，找到取最大值得元素
     *
     * 可以用你运算来求解。这里主要是为了快，就可以直接将compute函数的过程拷贝过来，
     * 然后在+1的地方记录index就可以得到一个解。
     *
     * 当然有多个解。为啥是这个解的原因在于各个分支都是用>来判断的。
     *
     * if ((dp[i - 1][i] + 1) > dp[i - 1][j]) {
     *                 ret.add(i);
     *                 markInner(ret, items, dp, i - 1, i);
     * 比如这个，就忽略了==的情况，其实也是一个解。
     */
    private void markInner(List<Integer> ret, List<Integer> items, int[][] dp, int i, int j) {
        if (i < 0 || j <= 0 || (i > j)) {
            return;
        }

        if (i == 0 && items.get(i) < items.get(j)) {
            ret.add(i);
            return;
        }

        if (i == j) {
            if ((dp[i - 1][i] + 1) > dp[i - 1][j - 1]) {
                ret.add(i);
                markInner(ret, items, dp, i - 1, i);
            } else {
                markInner(ret, items, dp, i - 1, j - 1);
            }
//            dp[i][j] = Math.max(dp[i - 1][i] + 1, dp[i - 1][j - 1]);
            return;
        }

        if (items.get(i).equals(items.get(j))) {
            markInner(ret, items, dp, i - 1, j);
//            dp[i][j] = dp[i - 1][j];
            return;
        }

        if (items.get(i) > items.get(j)) {
            markInner(ret, items, dp, i - 1, j);
//            dp[i][j] = dp[i - 1][j];
            return;
        }
        if (items.get(i) < items.get(j)) {
            if ((dp[i - 1][i] + 1) > dp[i - 1][j]) {
                ret.add(i);
                markInner(ret, items, dp, i - 1, i);
            } else {
                markInner(ret, items, dp, i - 1, j);
            }
//            dp[i][j] = Math.max(dp[i - 1][i] + 1, dp[i - 1][j]);
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
