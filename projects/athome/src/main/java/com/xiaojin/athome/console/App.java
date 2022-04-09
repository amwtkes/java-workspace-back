package com.xiaojin.athome.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
//        int length = 10;
//        ArrayList<Integer> a = new ArrayList<>(length);
//        ArrayList<Integer> b = new ArrayList<>(length);
//        for (int i = 0; i < length; i++) {
//            a.add(makeRandom(1, 20));
//            b.add(makeRandom(1, 20));
//        }
//        for (Integer e : a) {
//            System.out.print(e + " ");
//        }
//        System.out.println();
//        for (Integer e : b) {
//            System.out.print(e + " ");
//        }
//        System.out.println();
//        List<Integer> collect = b.stream().sorted().collect(Collectors.toList());
//        collect.forEach(p -> System.out.print(p + " "));

        int[][] as = new int[5][2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                as[i][j] = i * j;
            }
        }
        System.out.println(as.length);
    }

    public static int makeRandom(int min, int max) {
//        System.out.println("Random value of type int between " + min + " to " + max + ":");
        int intValue = (int) (Math.random() * (max - min + 1) + min);
        return intValue;
    }
}
