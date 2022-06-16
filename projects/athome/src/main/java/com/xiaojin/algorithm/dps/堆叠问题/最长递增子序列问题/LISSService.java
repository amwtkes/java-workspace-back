package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class LISSService extends DefaultProcessorService {
    private final List<LISSProcessor> processorList;

    public int run(List<Integer> items, int switcher) {
        LISSContext lissContext = new LISSContext(switcher);
        lissContext.setItems(items);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, lissContext);
        return (int) objectDefaultProcessorResult.getResult();
    }
}
