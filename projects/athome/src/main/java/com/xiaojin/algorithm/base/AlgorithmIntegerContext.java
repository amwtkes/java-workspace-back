package com.xiaojin.algorithm.base;

import runtime.processor.defaultprocessor.DefaultProcessorContext;

public class AlgorithmIntegerContext extends DefaultProcessorContext<Integer> {
    public AlgorithmIntegerContext() {
        super();
        this.getProcessorResult().setResult(-1);
    }

    public void finish(Integer result) {
        this.getProcessorResult().setResult(result);
        this.setFinished(true);
    }
}
