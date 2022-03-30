package com.xiaojin.algorithm.LCS.processor3;

import com.xiaojin.algorithm.LCS.base.Lcs2Context;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs3service extends DefaultProcessorService {
    private final List<Lcs3Processor> lcs3ProcessorList;

    public List<Integer> run() {
        return (List<Integer>) this.runProcessors(lcs3ProcessorList, new Lcs2Context()).getResult();
    }
}
