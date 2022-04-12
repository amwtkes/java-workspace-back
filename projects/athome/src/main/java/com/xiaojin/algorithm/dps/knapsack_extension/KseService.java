package com.xiaojin.algorithm.dps.knapsack_extension;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KseService extends DefaultProcessorService {
    private final List<KseProcessor> processorList;

    public List<KseContext.KseResult> run(String path) {
        KseContext kseContext = new KseContext(path);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, kseContext);
        return (List<KseContext.KseResult>) objectDefaultProcessorResult.getResult();
    }
}
