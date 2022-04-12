package com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.base.ContextHelper;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingResult;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.SourceDataType;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;

import static com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.M1ProcessorPriority.SETUP;

@Component
@SortOrder(SETUP)
public class MaxSequencingSetUpProcessor implements MaxSequencingProcessor {
    @Override
    public void process(MaxSequencingContext maxSequencingContext) throws ProcessorException {
        SourceDataType sourceDataType = getSourceType(maxSequencingContext);
        maxSequencingContext.setSourceDataType(sourceDataType);
        setUpInputArrayList(maxSequencingContext);
        prepareDPObjectCaching(maxSequencingContext);
    }

    private void prepareDPObjectCaching(MaxSequencingContext maxSequencingContext) {
        ArrayList<MaxSequencingResult> dpTable = new ArrayList<>(maxSequencingContext.getListSize());
        for (int i = 0; i < maxSequencingContext.getListSize(); i++) {
            dpTable.add(MaxSequencingResult.builder().build());
        }
        maxSequencingContext.setDpTable(dpTable);
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
