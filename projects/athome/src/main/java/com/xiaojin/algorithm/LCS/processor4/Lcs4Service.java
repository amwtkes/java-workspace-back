package com.xiaojin.algorithm.LCS.processor4;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs4Service extends DefaultProcessorService {
    private final List<Lcs4Processor> lcs4ProcessorList;

    public List<Integer> run() {
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(lcs4ProcessorList, new Lcs4Context());
        return (List<Integer>) objectDefaultProcessorResult.getResult();
    }
}
