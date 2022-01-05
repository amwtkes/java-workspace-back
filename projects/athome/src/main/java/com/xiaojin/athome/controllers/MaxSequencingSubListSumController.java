package com.xiaojin.athome.controllers;

import com.xiaojin.algorithm.AlgorithmRunner;
import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaxSequencingSubListSumController {
    private final AlgorithmRunner algorithmRunner;

    @GetMapping("/MaxSunOfSequencing")
    public String runAlgorithm() {
        AlgorithmGeneralContext algorithmGeneralContext = new AlgorithmGeneralContext();
        algorithmGeneralContext.setInput("1.5,-12.3,3.2,-5.5,23.2,3.2,-1.4,-12.2,34.2,5.4,-7.8,1.1,-4.9");
        return algorithmRunner.run(algorithmGeneralContext);
    }
}
