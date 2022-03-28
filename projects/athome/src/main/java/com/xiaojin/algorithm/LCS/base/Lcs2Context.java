package com.xiaojin.algorithm.LCS.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Lcs2Context extends AlgorithmGeneralContext {
    private List<Integer> items;

    public List<Integer> getResult() {
        return (List<Integer>) this.getProcessorResult().getResult();
    }
}
