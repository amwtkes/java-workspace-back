package com.xiaojin.algorithm.datastructure.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算Cnk的所有组合
 * 返回所有组合的数组
 */
public class Combinations {
    /**
     * 返回的是长度为k的子序列索引
     *
     * @param n !最大元素的index
     * @param k 多少个
     * @return index数组
     */
    public static List<List<Integer>> cnkGenerate(int n, int k) {
        if (n + 1 < k) {
            throw new IllegalArgumentException("n<k! k:" + k + " n:" + n);
        }
        Stack<Integer> chosen = new Stack<>();
        List<List<Integer>> combination = new ArrayList<>();
        gen(k, 0, n, chosen, combination);
        return combination;
    }

    /**
     * 生成所有的子串索引
     *
     * @param n 数组长度
     * @return 子串索引号的二维数组
     */
    public static List<List<Integer>> genAllSubSequnceIndex(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            ret.addAll(genSeqBySpan(i, n));
        }
        return ret;
    }

    private static List<List<Integer>> genSeqBySpan(int span, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < span; j++) {
                int index = j + i;
                if (index == n) {
                    return ret;
                }
                tmp.add(index);
            }
            ret.add(tmp);
        }
        return ret;
    }

    private static void gen(int k, int begin, int end, Stack<Integer> chosen, List<List<Integer>> combination) {
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
