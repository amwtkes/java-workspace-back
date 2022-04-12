package com.xiaojin.algorithm.dps.magic_exchange_to_max;

import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemService extends DefaultProcessorService {
    private final List<MemProcessor> processorList;

    public Pair<Integer, Integer> run(String path) {
        MemContext memContext = new MemContext(path);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processorList, memContext);
        return (Pair<Integer, Integer>) objectDefaultProcessorResult.getResult();
    }
}
