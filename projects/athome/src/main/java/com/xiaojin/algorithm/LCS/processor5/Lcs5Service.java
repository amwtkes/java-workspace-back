package com.xiaojin.algorithm.LCS.processor5;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs5Service extends DefaultProcessorService {
    private final List<Lcs5Processor> processorList;

    public List<Integer> run(String filePath) {
        Lcs5Context lcs5Context = new Lcs5Context();
        lcs5Context.setDataFilePath(filePath);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, lcs5Context);
        return (List<Integer>) objectDefaultProcessorResult.getResult();
    }
}
