package com.xiaojin.algorithm.base;

import runtime.processor.defaultprocessor.DefaultProcessorContext;


public class AlgorithmContext extends DefaultProcessorContext<Integer> {
    public AlgorithmContext() {
        super();
        this.getProcessorResult().setResult(-1);
    }
}
