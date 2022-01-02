package com.xiaojin.algorithm;

import com.xiaojin.algorithm.base.AlgorithmIntegerContext;
import com.xiaojin.algorithm.base.AlgorithmIntProcessor;
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
    private final AlgorithmProcessorLunchService lunchService;
    private final List<AlgorithmIntProcessor> processorList;

    public Integer run(AlgorithmIntegerContext algorithmIntegerContext) {
        DefaultProcessorResult<Integer> result = lunchService.run(processorList, algorithmIntegerContext);
        System.out.println("result is :>" + result.getResult());
        return result.getResult();
    }
}
