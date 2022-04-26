package com.xiaojin.algorithm.二分答案.电池问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatteryService extends DefaultProcessorService {
    private final List<BatteryProcessor> processorList;

    public int run(String path, int switcher) {
        BatteryContext batteryContext = new BatteryContext(path);
        batteryContext.setSwitcher(switcher);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, batteryContext);
        return (int) objectDefaultProcessorResult.getResult();
    }

    public int run(List<Integer> items, int nrCar, int switcher) {
        BatteryContext batteryContext = new BatteryContext(null);
        batteryContext.setSwitcher(switcher);
        batteryContext.setBatteries(items);
        batteryContext.setCarsNr(nrCar);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, batteryContext);
        return (int) objectDefaultProcessorResult.getResult();
    }
}
