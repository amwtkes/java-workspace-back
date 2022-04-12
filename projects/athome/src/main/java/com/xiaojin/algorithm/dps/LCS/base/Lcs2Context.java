package com.xiaojin.algorithm.dps.LCS.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Lcs2Context extends AlgorithmGeneralContext {
    private List<Integer> items;

    public List<Integer> getResult() {
        return (List<Integer>) this.getProcessorResult().getResult();
    }

    //for lcs3
    private List<Integer> A = new ArrayList<>();
}
