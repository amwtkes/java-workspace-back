package com.xiaojin.algorithm.knapsack_extension;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class KseContext extends AlgorithmGeneralContext {
    public KseContext(String path) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("knapsack_extension/1.txt");
        }
    }

    @AllArgsConstructor
    @Getter
    static class Item {
        private int volume;
        private int value;
    }

    private List<Item> items = new ArrayList<>();
    private List<Ask> asks;

    @AllArgsConstructor
    @Getter
    static class Ask {
        private int removeIndex;
        private int bagVolume;
    }

    @AllArgsConstructor
    @Getter
    static class KseResult {
        private int maxValue;
        private List<Integer> selectedIndex = new ArrayList<>();
    }
}
