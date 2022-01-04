package com.xiaojin.algorithm.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import runtime.processor.baseprocessor.ProcessorException;
import runtime.processor.defaultprocessor.DefaultProcessorContext;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlgorithmGeneralContext extends DefaultProcessorContext<Object> {
    private String input;

    public AlgorithmGeneralContext() {
        super();
        this.getProcessorResult().setResult(null);
    }

    public void finish(Object object) {
        this.getProcessorResult().setResult(object);
        this.setFinished(true);
    }

    public void assertInputNotBeNull(AlgorithmGeneralContext algorithmGeneralContext) throws ProcessorException {
        if (algorithmGeneralContext.getInput().equals("")) {
            throw new ProcessorException("Input should not be null!");
        }
    }
}
