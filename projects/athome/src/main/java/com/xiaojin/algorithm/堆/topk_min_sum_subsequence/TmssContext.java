package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TmssContext extends AlgorithmGeneralContext {
    public TmssContext(String path, int switcher) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("tmss/1.txt");
        }
        this.setSwitcher(switcher);
    }

    private List<Integer> items;
    private int k;
    private List<Integer> topK;

    public static int randomK(int itemSize) {
        int k = (int) (Math.random() * ((1 << itemSize) - 1)) + 1;
        return k;
    }
}
