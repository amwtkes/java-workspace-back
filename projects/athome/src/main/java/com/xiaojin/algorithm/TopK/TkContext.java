package com.xiaojin.algorithm.TopK;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TkContext extends AlgorithmGeneralContext {
    public TkContext(String path, int topK) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("TopK/1.txt");
        }
        this.topK = topK;
    }

    private final int topK;
    private List<Integer> items;
}
