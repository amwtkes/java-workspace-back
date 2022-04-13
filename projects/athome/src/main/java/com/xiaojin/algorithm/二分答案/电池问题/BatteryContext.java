package com.xiaojin.algorithm.二分答案.电池问题;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BatteryContext extends AlgorithmGeneralContext {
    public BatteryContext(String path) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
        } else {
            this.setInputDataPath("battery/1.txt");
        }
    }

    private List<Integer> batteries;
    private int carsNr;
}
