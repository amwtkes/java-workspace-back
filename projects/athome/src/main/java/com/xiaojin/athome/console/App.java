package com.xiaojin.athome.console;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
//        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        PriorityQueue<Integer> heap = new PriorityQueue<>(integers.size() - 1, (o1, o2) -> o2 - o1);
//        heap.addAll(integers);
//        System.out.println(heap.remove());
//        System.out.println(heap.poll());
        int l = 1, r = 100, length = r * 4;
        int[] arryInt = new int[r + 1];
        Arrays.fill(arryInt, 1);
        int[] sum = new int[length];
        Arrays.fill(sum, 0);
        breakDown(l, r, arryInt, sum, 1);
        for (int i = 1; i < sum.length; i++) {
            if (sum[i] > 0) {
                System.out.println(sum[i]);
            }
        }
    }

    public static int makeRandom(int min, int max) {
//        System.out.println("Random value of type int between " + min + " to " + max + ":");
        int intValue = (int) (Math.random() * (max - min + 1) + min);
        return intValue;
    }
}
