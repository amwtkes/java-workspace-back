package com.xiaojin.algorithm.dps.palindrome.manacher;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManacherContext extends AlgorithmGeneralContext {
    public ManacherContext(String dataPath) {
        if (Strings.isBlank(dataPath)) {
            this.setInputDataPath("palindrome/1.txt");
            return;
        }
        this.setInputDataPath(dataPath);
    }

    private int[] dp;
}
