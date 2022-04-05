package com.xiaojin.algorithm.magic_exchange_to_max;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MemContext extends AlgorithmGeneralContext {
    public MemContext(String path) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("magic_exchange_to_max/1.txt");
        }
    }

    List<Integer> items;
}
