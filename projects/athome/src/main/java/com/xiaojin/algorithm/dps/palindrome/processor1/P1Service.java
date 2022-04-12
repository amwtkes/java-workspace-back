package com.xiaojin.algorithm.dps.palindrome.processor1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P1Service extends DefaultProcessorService {
    private final List<P1Processor> processorList;

    public String run(String dataPath) {
        P1Context p1Context = new P1Context(dataPath);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, p1Context);
        return (String) objectDefaultProcessorResult.getResult();
    }
}
