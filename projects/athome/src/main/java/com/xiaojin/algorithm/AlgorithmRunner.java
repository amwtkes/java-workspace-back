package com.xiaojin.algorithm;

import com.xiaojin.algorithm.dps.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.dps.knapsack.base.KnapsackProcessor;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingProcessor;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsContext;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsProcessor;
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
    private final List<ClimbStairsProcessor> climbStairsProcessors;
    private final List<KnapsackProcessor> knapsackProcessors;

    public String runMaxSequencingAlgorithm(MaxSequencingContext algorithmGeneralContext) {
        DefaultProcessorResult<Object> result = lunchService.runMaxSequencingSubAlgorithm(processorList, algorithmGeneralContext);
        System.out.println("result is :>" + result.getResult());
        return result.getResult().toString();
    }

    public String runClimbStairsAlgorithm(ClimbStairsContext climbStairsContext) {
        DefaultProcessorResult<Object> objectDefaultProcessorResult = lunchService.runClimbStairsAlgorithm(climbStairsProcessors, climbStairsContext);
        return objectDefaultProcessorResult.getResult().toString();
    }

    public String runKnapsackAlgorithm(KnapsackContext knapsackContext) {
        DefaultProcessorResult<Object> objectDefaultProcessorResult = lunchService.runKnapsackAlgorithm(knapsackProcessors, knapsackContext);
        return objectDefaultProcessorResult.getResult().toString();
    }
}
