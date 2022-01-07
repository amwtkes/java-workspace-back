package com.xiaojin.algorithm.maxsequencingsublistsum.processors.base;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class MaxSequencingResult {
    private BigDecimal maxValueDecimal;
    private Float originMaxValue;

    private int maxValueInt;
    private int leftIndex;
    private int rightIndex;
}
