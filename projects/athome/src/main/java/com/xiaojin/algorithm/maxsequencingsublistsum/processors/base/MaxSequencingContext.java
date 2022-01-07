package com.xiaojin.algorithm.maxsequencingsublistsum.processors.base;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class MaxSequencingContext extends AlgorithmGeneralContext {
    public MaxSequencingContext() {
        super();
    }

    private SourceDataType sourceDataType;
    private ArrayList<MaxSequencingResult> dpTable;
    private ArrayList<Float> floatList;
    private ArrayList<Integer> intList;

    public int getListSize() {
        if (sourceDataType.equals(SourceDataType.FLOAT)) {
            return floatList.size();
        }
        return intList.size();
    }
}
