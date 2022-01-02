package com.xiaojin.algorithm.base;

import runtime.processor.defaultprocessor.DefaultProcessorContext;

public class AlgorithmGeneralContext extends DefaultProcessorContext<Object> {
    public AlgorithmGeneralContext() {
        super();
        this.getProcessorResult().setResult(null);
    }

    public void finish(Object object) {
        this.getProcessorResult().setResult(object);
        this.setFinished(true);
    }
}
