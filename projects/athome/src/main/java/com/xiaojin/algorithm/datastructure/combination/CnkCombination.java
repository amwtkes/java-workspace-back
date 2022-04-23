package com.xiaojin.algorithm.datastructure.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算Cnk的所有组合
 * 返回所有组合的数组
 */
public class CnkCombination {
    /**
     * 返回的是index组合
     *
     * @param n 最大元素的index
     * @param k 多少个
     * @return index数组
     */
    public List<List<Integer>> generate(int n, int k) {
        if (n + 1 < k) {
            throw new IllegalArgumentException("n<k! k:" + k + " n:" + n);
        }
        Stack<Integer> chosen = new Stack<>();
        List<List<Integer>> combination = new ArrayList<>();
        gen(k, 0, n, chosen, combination);
        return combination;
    }

    private void gen(int k, int begin, int end, Stack<Integer> chosen, List<List<Integer>> combination) {
        if (k > 1) {
            for (int i = begin; i < end; i++) {
                chosen.push(i);
                gen(k - 1, i + 1, end, chosen, combination);
                chosen.pop();
            }
            return;
        }
        for (int i = begin; i <= end; i++) {
            ArrayList<Integer> tmp = new ArrayList<>(chosen);
            tmp.add(i);
            combination.add(new ArrayList<>(tmp));
        }
    }
}
