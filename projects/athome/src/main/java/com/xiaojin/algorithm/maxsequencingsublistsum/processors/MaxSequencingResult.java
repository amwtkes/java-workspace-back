package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MaxSequencingResult {
    private BigDecimal maxValue;
    private int leftIndex;
    private int rightIndex;
}
