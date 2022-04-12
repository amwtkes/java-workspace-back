package com.xiaojin.algorithm.dps.palindrome.manacher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManacherService extends DefaultProcessorService {
    private final List<ManacherProcessor> processorList;

    public String run(String path) {
        ManacherContext manacherContext = new ManacherContext(path);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, manacherContext);
        return (String) objectDefaultProcessorResult.getResult();
    }
}
