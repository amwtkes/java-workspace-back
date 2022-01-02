package com.xiaojin.algorithm.base;

import runtime.processor.defaultprocessor.DefaultProcessorContext;

public class AContext extends DefaultProcessorContext<Integer> {
    public AContext() {
        super();
        this.getProcessorResult().setResult(-1);
    }
}
