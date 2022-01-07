package com.xiaojin.algorithm.maxsequencingsublistsum.processors.base;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class MaxSequencingResult {
    private BigDecimal maxValue;
    private Float originMaxValue;
    private int leftIndex;
    private int rightIndex;
}
