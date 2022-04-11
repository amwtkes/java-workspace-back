package com.xiaojin.algorithm.最大与的最短子串_no_dp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZdyService extends DefaultProcessorService {
    private final List<ZdyProcessor> processorList;

    public List<Integer> run(String path, int switcher) {
        ZdyContext zdyContext = new ZdyContext(path);
        zdyContext.setSwitcher(switcher);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, zdyContext);
        return (List<Integer>) objectDefaultProcessorResult.getResult();
    }
}
