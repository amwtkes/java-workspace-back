package com.xiaojin.algorithm.最大与的最短子串_no_dp;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ZdyContext extends AlgorithmGeneralContext {
    private List<Integer> items;

    public ZdyContext(String path) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("zdy/1.txt");
        }
    }
}
