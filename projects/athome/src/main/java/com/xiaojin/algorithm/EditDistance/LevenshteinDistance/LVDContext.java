package com.xiaojin.algorithm.EditDistance.LevenshteinDistance;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@Data
public class LVDContext extends AlgorithmGeneralContext {
    private String FirstLine;
    private String SecondLine;
    private int[][] dp;

    public LVDContext(String dataPath) {
        if (Strings.isBlank(dataPath)) {
            this.setInputDataPath("EditDistance/1.txt");
            return;
        }
        this.setInputDataPath(dataPath);
    }

    @Data
    @Builder
    static class LVDResult {
        private int minLength;
    }
}
