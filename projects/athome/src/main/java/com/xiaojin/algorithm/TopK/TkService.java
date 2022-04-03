package com.xiaojin.algorithm.TopK;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TkService extends DefaultProcessorService {
    private final List<TkProcessor> processorList;

    public List<Integer> run(String path, int topK) {
        TkContext tkContext = new TkContext(path, topK);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, tkContext);
        return (List<Integer>) objectDefaultProcessorResult.getResult();
    }
}
