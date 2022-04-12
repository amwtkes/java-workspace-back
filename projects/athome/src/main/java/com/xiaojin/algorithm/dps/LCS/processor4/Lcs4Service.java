package com.xiaojin.algorithm.dps.LCS.processor4;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs4Service extends DefaultProcessorService {
    private final List<Lcs4Processor> lcs4ProcessorList;

    public List<Integer> run(String filePath) {
        Lcs4Context context = new Lcs4Context();
        if (!Strings.isBlank(filePath)) {
            context.setDataFilePath(filePath);
        }
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(lcs4ProcessorList, context);
        return (List<Integer>) objectDefaultProcessorResult.getResult();
    }
}
