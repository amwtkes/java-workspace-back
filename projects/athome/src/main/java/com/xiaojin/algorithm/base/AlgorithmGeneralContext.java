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

    public void setResultAndFinish(Object result) {
        finish(result);
    }

    public Object getResult() {
        return this.getProcessorResult().getResult();
    }

    public void assertInputNotBeNull() throws ProcessorException {
        if (getInput().equals("")) {
            throw new ProcessorException("Input should not be null!");
        }
    }

    protected String getAlgorithmResultInfo() {
        return "";
    }
}
