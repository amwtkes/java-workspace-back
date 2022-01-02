package com.xiaojin.athome.controllers;

import com.xiaojin.algorithm.AlgorithmRunner;
import com.xiaojin.algorithm.base.AlgorithmContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaxSequencingSubListSumController {
    private final AlgorithmRunner algorithmRunner;

    @GetMapping("/MaxSunOfSequencing")
    public Integer runAlgorithm() {
        return algorithmRunner.run(new AlgorithmContext());
    }
}
