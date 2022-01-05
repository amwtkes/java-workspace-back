package com.xiaojin.algorithm.maxsequencingsublistsum.processors;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class MaxSequencingResult {
    private BigDecimal maxValue;
    private int leftIndex;
    private int rightIndex;
}
