package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class LISSContext extends AlgorithmGeneralContext {
    public LISSContext(int switchNr) {
        this.setSwitcher(switchNr);
    }

    private List<Integer> items;
}
