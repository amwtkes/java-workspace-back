package com.xiaojin.algorithm.reverse_pair;

import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RpService extends DefaultProcessorService {
    private final List<RpProcessor> processorList;

    public List<Pair<Integer, Integer>> run(String path) {
        RpContext rpContext = new RpContext(path);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, rpContext);
        return (List<Pair<Integer, Integer>>) objectDefaultProcessorResult.getResult();
    }
}
