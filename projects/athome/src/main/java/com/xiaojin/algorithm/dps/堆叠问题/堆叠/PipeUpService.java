package com.xiaojin.algorithm.dps.堆叠问题.堆叠;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PipeUpService extends DefaultProcessorService {
    private final List<PipeUpProcessor> processorList;

    public int run(List<Integer> items, int switchNr) {
        PipeUpContext pipeUpContext = new PipeUpContext(switchNr);
        pipeUpContext.setItems(items);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, pipeUpContext);
        return (int) objectDefaultProcessorResult.getResult();
    }
}
