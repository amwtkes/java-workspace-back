package com.xiaojin.algorithm.knapsack.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
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
    private int[][] table;
    private int[][] markTable;
    private ArrayList<Integer> resultVector;

    public void setResult2(int answer) {
        this.setResult(answer);
    }

    public Integer getResult2() {
        return (Integer) getResult();
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        private int value;
        private int weight;
    }
}
