package com.xiaojin.algorithm.dps.palindrome.processor3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class P3Service extends DefaultProcessorService {
    private final List<P3Processor> processors;

    public String run(String path) {
        P3Context p3Context = new P3Context(path);
        return (String) this.runProcessors(processors, p3Context).getResult();
    }
}
