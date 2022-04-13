package com.xiaojin.algorithm.二分答案.电池问题;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringListFromClassPathFile;
import static com.xiaojin.algorithm.二分答案.电池问题.BatteryPriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatteryLoadProcessor implements BatteryProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(BatteryContext batteryContext) throws ProcessorException {
        try {
            List<String> inputStrings = getInputStringListFromClassPathFile(resourceLoader, batteryContext.getInputDataPath());
            if (inputStrings.size() <= 0) {
                throw new ProcessorException("Input is empty!");
            }
            if (inputStrings.size() != 2) {
                throw new ProcessorException("Input nr should be 2!");
            }

            for (String line : inputStrings) {
                ArrayList<Integer> lineItems = ContextHelper.splitter(line, Integer.MAX_VALUE);
                if (lineItems.size() > 1) {
                    batteryContext.setBatteries(lineItems);
                } else {
                    batteryContext.setCarsNr(lineItems.get(0));
                }
            }
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "电池分配问题 loading...";
    }
}
