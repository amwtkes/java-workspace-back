package com.xiaojin.algorithm.p_recursive.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Scanner;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClimbStairsContext extends AlgorithmGeneralContext {
    public ClimbStairsContext() {
        super();
        setScanner(new Scanner(System.in));
    }

    private Scanner scanner;
    private int stairsNumber;
    private int climbSpan;

    public void setResult2(int totalCombinations) {
        this.setResultAndFinish(totalCombinations);
    }

    public int getResult2() {
        return (int) getResult();
    }
}
