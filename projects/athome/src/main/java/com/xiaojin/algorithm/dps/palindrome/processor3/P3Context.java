package com.xiaojin.algorithm.dps.palindrome.processor3;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@Data
public class P3Context extends AlgorithmGeneralContext {
    public P3Context(String path) {
        if (Strings.isBlank(path)) {
            this.setInputDataPath("palindrome/1.txt");
            return;
        }
        this.setInputDataPath(path);
    }

    /**
     * dp[i][j]=boolean - 代表[i->j]以i为开头j为结尾的子串是否为回文
     * dp[i][j] = true if a[i]==a[j] && dp[i][j]=ture
     */
    private boolean dp[][];
}
