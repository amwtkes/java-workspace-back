package com.xiaojin.athome.console;

import com.xiaojin.algorithm.datastructure.combination.CnkCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        PriorityQueue<Integer> heap = new PriorityQueue<>(integers.size() - 1, (o1, o2) -> o2 - o1);
//        heap.addAll(integers);
//        System.out.println(heap.remove());
//        System.out.println(heap.poll());
//        int l = 1, r = 100, length = r * 4;
//        int[] arryInt = new int[r + 1];
//        Arrays.fill(arryInt, 1);
//        int[] sum = new int[length];
//        Arrays.fill(sum, 0);
//        breakDown(l, r, arryInt, sum, 1);
//        for (int i = 1; i < sum.length; i++) {
//            if (sum[i] > 0) {
//                System.out.println(sum[i]);
//            }
//        }

        List<List<Integer>> generate = new CnkCombination().generate(5-1, 2);
        for(List<Integer> c : generate){
            System.out.println(c);
        }
    }

    public static int breakDown(int l, int r, int[] arry, int[] sum, int index) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
//            String value = getString(l, r, index, arry[l]);
            sum[index] = arry[l];
            return arry[l];
        }
        int mid = l + (r - l) / 2;
        int left = breakDown(l, mid, arry, sum, index << 1);
        int right = breakDown(mid + 1, r, arry, sum, index << 1 | 1);
        //        arryRet[index] = getString(l, r, index, total);
        int total = left + right;
        sum[index] = total;
        return total;
    }

    private static String getString(int l, int r, int index, int total) {
        return "index=" + index + "-[" + l + "-" + r + "]=" + total;
    }

    public static int makeRandom(int min, int max) {
//        System.out.println("Random value of type int between " + min + " to " + max + ":");
        int intValue = (int) (Math.random() * (max - min + 1) + min);
        return intValue;
    }
}
