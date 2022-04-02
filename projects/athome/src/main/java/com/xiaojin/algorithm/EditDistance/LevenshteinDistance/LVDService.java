package com.xiaojin.algorithm.EditDistance.LevenshteinDistance;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LVDService extends DefaultProcessorService {
    private final List<LVDProcessor> processors;

    public LVDContext.LVDResult run(String path) {
        LVDContext lvdContext = new LVDContext(path);
        return (LVDContext.LVDResult) this.runProcessors(processors, lvdContext).getResult();
    }
}
