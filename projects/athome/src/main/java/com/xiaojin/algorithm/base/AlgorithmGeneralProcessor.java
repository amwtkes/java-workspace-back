package com.xiaojin.algorithm.base;

import runtime.processor.defaultprocessor.DefaultProcessor;
import runtime.processor.defaultprocessor.DefaultProcessorContext;

public interface AlgorithmGeneralProcessor<R, T extends DefaultProcessorContext<R>> extends DefaultProcessor<R, T> {
    AlgorithmRunInfo getRunInfo();
}
