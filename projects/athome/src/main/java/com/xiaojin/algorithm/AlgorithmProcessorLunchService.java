package com.xiaojin.algorithm;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.base.AlgorithmGeneralProcessor;
import com.xiaojin.algorithm.dps.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.dps.knapsack.base.KnapsackProcessor;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsContext;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsProcessor;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmProcessorLunchService extends DefaultProcessorService {
    public DefaultProcessorResult<Object> runSingleProcessor(AlgorithmGeneralProcessor processor, AlgorithmGeneralContext context) {
        List<AlgorithmGeneralProcessor> processorList = new ArrayList<>(1);
        processorList.add(processor);
        return this.runProcessors(processorList, context);
    }

    public DefaultProcessorResult<Object> run(List<AlgorithmGeneralProcessor> processorList, AlgorithmGeneralContext context) {
        return this.runProcessors(processorList, context);
    }

    public DefaultProcessorResult<Object> runMaxSequencingSubAlgorithm(List<MaxSequencingProcessor> processorList, MaxSequencingContext context) {
        return this.runProcessors(processorList, context);
    }

    public DefaultProcessorResult<Object> runClimbStairsAlgorithm(List<ClimbStairsProcessor> processors, ClimbStairsContext climbStairsContext) {
        return this.runProcessors(processors, climbStairsContext);
    }

    public DefaultProcessorResult<Object> runKnapsackAlgorithm(List<KnapsackProcessor> processors, KnapsackContext knapsackContext) {
        return this.runProcessors(processors, knapsackContext);
    }
}
