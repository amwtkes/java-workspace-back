package com.xiaojin.algorithm.palindrome.processor1;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class P1Context extends AlgorithmGeneralContext {
    public P1Context(String dataPath) {
        this.setInputDataPath(dataPath);
    }
}
