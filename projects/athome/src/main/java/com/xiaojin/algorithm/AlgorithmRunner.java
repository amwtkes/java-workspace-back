package com.xiaojin.algorithm;

import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlgorithmRunner {
    private final AlgorithmProcessorLunchService lunchService;
    private final List<MaxSequencingProcessor> processorList;

    public String runMaxSequencingAlgorithm(MaxSequencingContext algorithmGeneralContext) {
        DefaultProcessorResult<Object> result = lunchService.runMaxSequencingSubAlgorithm(processorList, algorithmGeneralContext);
        System.out.println("result is :>" + result.getResult());
        return result.getResult().toString();
    }
}
