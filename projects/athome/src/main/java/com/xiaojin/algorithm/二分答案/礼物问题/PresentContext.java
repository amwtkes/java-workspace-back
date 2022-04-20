package com.xiaojin.algorithm.二分答案.礼物问题;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PresentContext extends AlgorithmGeneralContext {
    public PresentContext(String path, int switcher) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
            this.setSwitcher(switcher);
        } else {
            this.setInputDataPath("present/1.txt");
        }
    }

    private List<Integer> items;
    private int nr;

    @AllArgsConstructor
    @Getter
    static class PresentResult {
        private List<Integer> indexes;
        private int nr;
    }
}
