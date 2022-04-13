package com.xiaojin.athome.console;

import org.springframework.util.comparator.Comparators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        PriorityQueue<Integer> heap = new PriorityQueue<>(integers.size() - 1, (o1, o2) -> o2 - o1);
        heap.addAll(integers);
        System.out.println(heap.remove());
        System.out.println(heap.poll());
    }

    public static int makeRandom(int min, int max) {
//        System.out.println("Random value of type int between " + min + " to " + max + ":");
        int intValue = (int) (Math.random() * (max - min + 1) + min);
        return intValue;
    }
}
