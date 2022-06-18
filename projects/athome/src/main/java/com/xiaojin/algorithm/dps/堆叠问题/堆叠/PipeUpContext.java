package com.xiaojin.algorithm.dps.堆叠问题.堆叠;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PipeUpContext extends AlgorithmGeneralContext {
    public PipeUpContext(int switchNr) {
        this.setSwitcher(switchNr);
    }

    private List<Integer> items;
}
