package com.xiaojin.algorithm.LCS.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Lcs1Context extends AlgorithmGeneralContext {
    private List<Integer> items;
    private HashMap<Integer, Integer> map;

    public List<Integer> getResult() {
        return (List<Integer>) this.getProcessorResult().getResult();
    }
}
