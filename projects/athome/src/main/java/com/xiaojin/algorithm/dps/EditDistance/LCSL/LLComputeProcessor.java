package com.xiaojin.algorithm.dps.EditDistance.LCSL;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.dps.EditDistance.LCSL.LLPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LLComputeProcessor implements LLProcessor {
    @Override
    public void process(LLContext llContext) throws ProcessorException {
        String a = llContext.getFirstLine();
        String b = llContext.getSecondLine();
        int[][] dp = new int[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (i == 0 || j == 0) {
                    if (a.charAt(i) == b.charAt(j)) {
                        dp[i][j] = 1;
                        continue;
                    } else {
                        if (i == 0 && j == 0) {
                            dp[i][j] = 0;
                        } else if (i == 0) {
                            dp[i][j] = dp[i][j - 1];
                        } else {
                            dp[i][j] = dp[i - 1][j];
                        }
                    }
                    continue;
                }
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        String maxStr = markFunction(dp, a, b);
        llContext.setResultAndFinish(maxStr);
    }

    private String markFunction(int[][] dp, String a, String b) {
        int maxCommonLength = dp[a.length() - 1][b.length() - 1];
        char[] chars = new char[maxCommonLength];
        int k = maxCommonLength - 1;
        for (int i = a.length() - 1; i >= 0; ) {
            if (k < 0) {
                break;
            }
            for (int j = b.length() - 1; j >= 0; ) {
                if (i < 0) {
                    break;
                }
                if (i == 0 || j == 0) {
                    if (a.charAt(i) == b.charAt(j)) {
                        chars[k] = a.charAt(i);
                        k--;
                        i--;
                        j--;
                    } else if (i == 0) {
                        j--;
                    } else {
                        i--;
                    }
                    continue;
                }
                int maxParameter;
                if (a.charAt(i) == b.charAt(j)) {
                    maxParameter = getMaxParameter(dp[i - 1][j - 1] + 1, dp[i - 1][j], dp[i][j - 1]);
                    if (maxParameter == 0) {
                        chars[k] = a.charAt(i);
                        i--;
                        j--;
                        k--;
                        continue;
                    }
                    if (maxParameter == 1) {
                        i--;
                    }
                    if (maxParameter == 2) {
                        j--;
                    }
                } else {
                    maxParameter = getMaxParameter(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
                    if (maxParameter == 0) {
                        i--;
                        j--;
                    }
                    if (maxParameter == 1) {
                        i--;
                    }
                    if (maxParameter == 2) {
                        j--;
                    }
                }
            }
        }
        return new String(chars);
    }

    private int getMaxParameter(int a, int b, int c) {
        if (a >= b && a >= c) {
            return 0;
        }
        if (b >= a && b >= c) {
            return 1;
        }
        return 2;
    }

    @Override
    public String getProcessorName() {
        return "最长公共子序列 computing...";
    }
}
