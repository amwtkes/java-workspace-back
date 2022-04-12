package com.xiaojin.algorithm.dps.LCS.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class Lcs1Context extends AlgorithmGeneralContext {
    private List<Integer> items;
    private Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public List<Integer> getResult() {
        return (List<Integer>) this.getProcessorResult().getResult();
    }
}
