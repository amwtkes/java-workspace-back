package com.xiaojin.athome.controllers;

import com.xiaojin.algorithm.AlgorithmRunner;
import com.xiaojin.algorithm.dps.maxsequencingsublistsum.processors.base.MaxSequencingContext;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaxSequencingSubListSumController {
    private final AlgorithmRunner algorithmRunner;

    @GetMapping("/MaxSunOfSequencing")
    public String runAlgorithm() {
        MaxSequencingContext algorithmGeneralContext = new MaxSequencingContext();
        return algorithmRunner.runMaxSequencingAlgorithm(algorithmGeneralContext);
    }

    @GetMapping("/climb_stairs")
    public int runClimbStairs(@RequestParam("stairs") int stairs, @RequestParam("span") int span) {
        ClimbStairsContext climbStairsContext = new ClimbStairsContext();
        climbStairsContext.setClimbSpan(span);
        climbStairsContext.setStairsNumber(stairs);
        algorithmRunner.runClimbStairsAlgorithm(climbStairsContext);
        return climbStairsContext.getResult2();
    }
}
