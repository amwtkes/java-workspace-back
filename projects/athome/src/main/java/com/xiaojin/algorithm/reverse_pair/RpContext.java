package com.xiaojin.algorithm.reverse_pair;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import com.xiaojin.algorithm.datastructure.Tuple.Pair;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RpContext extends AlgorithmGeneralContext {
    public RpContext(String path) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("reverse_pair/1.txt");
        }
    }

    private List<Integer> items;
    private List<Pair<Integer, Integer>> rpPairs = new ArrayList<>();
}
