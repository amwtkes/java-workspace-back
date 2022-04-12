package com.xiaojin.algorithm.dps.LCS.processor5;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Lcs5Context extends AlgorithmGeneralContext {
    private List<Integer> items;
    private int[] A;
    private String dataFilePath = "lcs/1.txt";
}
