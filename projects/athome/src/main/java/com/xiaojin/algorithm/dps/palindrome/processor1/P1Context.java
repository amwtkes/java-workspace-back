package com.xiaojin.algorithm.dps.palindrome.processor1;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@Data
public class P1Context extends AlgorithmGeneralContext {
    public P1Context(String dataPath) {
        if (Strings.isBlank(dataPath)) {
            this.setInputDataPath("palindrome/1.txt");
            return;
        }
        this.setInputDataPath(dataPath);
    }
}
