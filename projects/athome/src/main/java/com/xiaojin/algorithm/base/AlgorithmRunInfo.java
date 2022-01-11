package com.xiaojin.algorithm.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlgorithmRunInfo {
    private Long eclipseTime;
    private String info;
    private AlgorithmGeneralProcessor algorithmGeneralProcessor;
}
