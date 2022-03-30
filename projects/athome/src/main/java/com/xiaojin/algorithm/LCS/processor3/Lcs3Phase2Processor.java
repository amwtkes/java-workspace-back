package com.xiaojin.algorithm.LCS.processor3;

import com.xiaojin.algorithm.LCS.base.Lcs2Context;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.LCS.processor3.Lcs3Priority.PHASE2;

@Component
@SortOrder(PHASE2)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs3Phase2Processor implements Lcs3Processor {
    @Override
    public void process(Lcs2Context lcs2Context) throws ProcessorException {
        int maxValue = 0, maxLength = 0;
        for (int i = 0; i < lcs2Context.getA().size(); i++) {
            if (lcs2Context.getA().get(i) > maxLength) {
                maxLength = lcs2Context.getA().get(i);
                maxValue = lcs2Context.getItems().get(i);
            }
        }
        List<Integer> ret = new ArrayList<>(maxLength);
        for (int i = maxLength; i > 0; i--) {
            ret.add(maxValue - i + 1);
        }
        lcs2Context.setResultAndFinish(ret);
    }

    @Override
    public String getProcessorName() {
        return "lcs3 phase3";
    }
}
