package com.xiaojin.algorithm.maxsequencingsublistsum.processors.base;

import lombok.Getter;
import lombok.Setter;
import runtime.processor.defaultprocessor.DefaultProcessorContext;

@Getter
@Setter
public class AlgorithmContext extends DefaultProcessorContext<Integer> {
    public AlgorithmContext() {
        super();
        this.getProcessorResult().setResult(-1);
    }
}
