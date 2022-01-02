package com.xiaojin.algorithm;

import com.xiaojin.algorithm.base.AlgorithmContext;
import com.xiaojin.algorithm.base.AlgorithmProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlgorithmRunner {
    private final DefaultProcessorService defaultProcessorService;
    private final List<AlgorithmProcessor> processorList;

    public Integer run(AlgorithmContext algorithmContext) {
        DefaultProcessorResult<Integer> result = defaultProcessorService.runProcessors(processorList, algorithmContext);
        System.out.println("result is :>" + result.getResult());
        return result.getResult();
    }
}
