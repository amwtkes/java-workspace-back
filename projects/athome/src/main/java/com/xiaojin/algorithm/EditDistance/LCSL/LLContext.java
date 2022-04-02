package com.xiaojin.algorithm.EditDistance.LCSL;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@Data
public class LLContext extends AlgorithmGeneralContext {
    public LLContext(String dataPath) {
        if (Strings.isBlank(dataPath)) {
            this.setInputDataPath("EditDistance/1.txt");
            return;
        }
        this.setInputDataPath(dataPath);
    }

    private String FirstLine;
    private String SecondLine;
    /**
     * dp[i][j]
     * firstString [0 -> i]与secondString[0-> j]的最长公共子串长度
     */
    private int[][] dp;
    private String maxCommonSubsequence;
}
