package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TmssService extends DefaultProcessorService {
    private final List<TmssProcessor> processorList;

    public List<Integer> run(String path, int switcher) {
        TmssContext tmssContext = new TmssContext(path, switcher);
        return (List<Integer>) this.runProcessors(processorList, tmssContext).getResult();
    }

    public List<Integer> run(List<Integer> items, int k, int switcher) {
        TmssContext tmssContext = new TmssContext(null, switcher);
        tmssContext.setItems(items);
        tmssContext.setK(k);
        return (List<Integer>) this.runProcessors(processorList, tmssContext).getResult();
    }
}
