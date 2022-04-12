package com.xiaojin.algorithm.dps.palindrome.processor2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P2Service extends DefaultProcessorService {
    private final List<P2Processor> processorList;

    public String run(String path) {
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, new P2Context(path));
        return (String) objectDefaultProcessorResult.getResult();
    }
}
