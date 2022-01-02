package com.xiaojin.algorithm;

import com.xiaojin.algorithm.base.AlgorithmIntProcessor;
import com.xiaojin.algorithm.base.AlgorithmIntegerContext;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmProcessorLunchService extends DefaultProcessorService {
    public DefaultProcessorResult<Integer> runSingleProcessor(AlgorithmIntProcessor processor, AlgorithmIntegerContext context) {
        List<AlgorithmIntProcessor> processorList = new ArrayList<>(1);
        processorList.add(processor);
        return this.runProcessors(processorList, context);
    }

    public DefaultProcessorResult<Integer> run(List<AlgorithmIntProcessor> processorList, AlgorithmIntegerContext context) {
        return this.runProcessors(processorList, context);
    }
}
