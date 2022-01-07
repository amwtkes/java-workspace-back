package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.SourceDataType;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.M1ProcessorPriority.SETUP;

@Component
@SortOrder(SETUP)
public class MaxSequencingSetUpProcessor implements MaxSequencingProcessor {
    @Override
    public void process(MaxSequencingContext maxSequencingContext) throws ProcessorException {
        SourceDataType sourceDataType = getSourceType(maxSequencingContext);
        maxSequencingContext.setSourceDataType(sourceDataType);
        setUpInputArrayList(maxSequencingContext);
    }

    private void setUpInputArrayList(MaxSequencingContext maxSequencingContext) throws ProcessorException {
        if (maxSequencingContext.getSourceDataType().equals(SourceDataType.FLOAT)) {
            maxSequencingContext.setFloatList(ContextHelper.splitter(maxSequencingContext, (Float) SourceDataType.FLOAT.getValue()));
            return;
        }
        maxSequencingContext.setIntList(ContextHelper.splitter(maxSequencingContext, (Integer) SourceDataType.INT.getValue()));
    }

    public SourceDataType getSourceType(AlgorithmGeneralContext algorithmGeneralContext) {
        if (algorithmGeneralContext.getInput().contains(".")) {
            return SourceDataType.FLOAT;
        }
        return SourceDataType.INT;
    }

    @Override
    public String getProcessorName() {
        return "设置过程";
    }
}
