package com.xiaojin.algorithm.dps.LCS.processor4;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Lcs4Context extends AlgorithmGeneralContext {
    private int[][] dp = null;
    private List<Integer> items = null;
    private String dataFilePath = "lcs/1.txt";
}
