package com.xiaojin.algorithm.二分答案.礼物问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PresentService extends DefaultProcessorService {
    private final List<PresentProcessor> processorList;

    public PresentContext.PresentResult run(String path, int switcher, int k) {
        PresentContext presentContext = new PresentContext(path, switcher);
        presentContext.setNr(k);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, presentContext);
        return (PresentContext.PresentResult) objectDefaultProcessorResult.getResult();
    }
}
