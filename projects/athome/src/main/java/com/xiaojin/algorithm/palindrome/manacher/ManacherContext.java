package com.xiaojin.algorithm.palindrome.manacher;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import org.apache.logging.log4j.util.Strings;

public class ManacherContext extends AlgorithmGeneralContext {
    public ManacherContext(String dataPath) {
        if (Strings.isBlank(dataPath)) {
            this.setInputDataPath("palindrome/1.txt");
            return;
        }
        this.setInputDataPath(dataPath);
    }
}
