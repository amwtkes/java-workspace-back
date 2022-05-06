package com.xiaojin.algorithm.mics.hanoi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HanoiService extends DefaultProcessorService {
    private final List<HanoiProcessor> processorList;

    public int run(int nr, int switcher) {
        HanoiContext hanoiContext = new HanoiContext();
        hanoiContext.setNr(nr);
        hanoiContext.setSwitcher(switcher);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, hanoiContext);
        return (int) objectDefaultProcessorResult.getResult();
    }
}
