package com.xiaojin.algorithm.mics.hanoi;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HanoiContext extends AlgorithmGeneralContext {
    private int nr;
}
