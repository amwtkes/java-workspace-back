package com.xiaojin.algorithm.EditDistance.LCSL;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LLService extends DefaultProcessorService {
    private final List<LLProcessor> processors;

    public String run(String path) {
        LLContext llContext = new LLContext(path);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processors, llContext);
        return (String) objectDefaultProcessorResult.getResult();
    }
}
