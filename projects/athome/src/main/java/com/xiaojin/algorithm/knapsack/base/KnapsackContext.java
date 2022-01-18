package com.xiaojin.algorithm.knapsack.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class KnapsackContext extends AlgorithmGeneralContext {
    public KnapsackContext() {
        super();
    }

    private int knapsackWeightLimit;
    private List<Item> items;
    private String itemSeparator = "-";

    public void setResult2(List<Integer> answerList) {
        this.setResult(answerList);
    }

    public List<Integer> getResult2() {
        return (List<Integer>) getResult();
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        private int value;
        private int weight;
    }
}
