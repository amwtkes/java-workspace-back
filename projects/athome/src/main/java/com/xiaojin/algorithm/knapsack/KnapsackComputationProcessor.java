package com.xiaojin.algorithm.knapsack;

import com.xiaojin.algorithm.knapsack.base.KnapsackContext;
import com.xiaojin.algorithm.knapsack.base.KnapsackPriority;
import com.xiaojin.algorithm.knapsack.base.KnapsackProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

@Component
@SortOrder(KnapsackPriority.COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KnapsackComputationProcessor implements KnapsackProcessor {
    @Override
    public void process(KnapsackContext knapsackContext) throws ProcessorException {
        if (knapsackContext.getItems() == null || knapsackContext.getItems().size() == 0) {
            throw new ProcessorException("背包问题没有初始化物品列表！");
        }
        if (knapsackContext.getKnapsackWeightLimit() <= 0) {
            throw new ProcessorException("背包的总容量不能是0或者小于0！");
        }
        computation(knapsackContext);
    }

    private void computation(KnapsackContext knapsackContext) {
        innerComputation(knapsackContext);
    }

    private void innerComputation(KnapsackContext knapsackContext) {
        int numberOfItems = knapsackContext.getItems().size();
        int knapsackWeightLimit = knapsackContext.getKnapsackWeightLimit();
        List<KnapsackContext.Item> items = knapsackContext.getItems();
        int[][] markTable = new int[numberOfItems + 1][knapsackWeightLimit + 1];
        int[][] table = new int[numberOfItems + 1][knapsackWeightLimit + 1];
        for (int i = 1; i <= numberOfItems; i++) {
            for (int bagWeight = 0; bagWeight <= knapsackWeightLimit; bagWeight++) {
                if (i - 1 == 0) {
                    if (bagWeight - items.get(0).getWeight() < 0) {
                        table[i][bagWeight] = 0;
                        markTable[i][bagWeight] = 0;
                    } else {
                        table[i][bagWeight] = table[i][bagWeight - items.get(0).getWeight()] + items.get(0).getValue();
                        if (bagWeight - items.get(0).getWeight() >= 0) {
                            markTable[i][bagWeight] = i;
                        }
                    }
                } else {
                    if (bagWeight == 0) {
                        table[i][bagWeight] = 0;
                        continue;
                    }

                    int preItemValue = table[i - 1][bagWeight];
                    int useThisValue;
                    if (bagWeight - items.get(i - 1).getWeight() < 0) {
                        table[i][bagWeight] = preItemValue;
                        markTable[i][bagWeight] = markTable[i - 1][bagWeight];
                    } else {
                        useThisValue = table[i][bagWeight - items.get(i - 1).getWeight()] + items.get(i - 1).getValue();
                        table[i][bagWeight]
                                = Math.max(preItemValue, useThisValue);
                        if (useThisValue >= preItemValue) { //Fn-1(bagWeight) == Fn(bagweight-wi)+vi的时候取最大标号
                            markTable[i][bagWeight] = i;
                        } else {
                            markTable[i][bagWeight] = markTable[i - 1][bagWeight];
                        }
                    }
                }
            }
        }
        knapsackContext.setResult2(table[items.size()][knapsackWeightLimit]);
        knapsackContext.setTable(table);
        knapsackContext.setMarkTable(markTable);
    }

    @Override
    public String getProcessorName() {
        return "背包问题计算所逻辑 - 3";
    }
}
