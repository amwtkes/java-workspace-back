package com.xiaojin.algorithm.p_recursive.race_n;

import com.xiaojin.algorithm.base.AlgorithmRunInfo;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsContext;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsProcessor;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.p_recursive.base.ClimbStairPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
public class ComputationProcessor implements ClimbStairsProcessor {
    @Override
    public AlgorithmRunInfo getRunInfo() {
        return null;
    }

    @Override
    public void process(ClimbStairsContext climbStairsContext) throws ProcessorException {
        int stairsNumber = climbStairsContext.getStairsNumber();
        int span = climbStairsContext.getClimbSpan();
        if (stairsNumber == 0 || span == 0 || stairsNumber < span) {
            throw new ProcessorException("参数错误！stair number:" + stairsNumber + " span:" + span);
        }

        int ret = computation(stairsNumber, span);
        climbStairsContext.setResult2(ret);
    }

    private int computation(int stairsNumber, int span) {
        int length = stairsNumber + 1;
        int[] table = new int[length];
        for (int i = 1; i <= stairsNumber; i++) {
            if (i == 1) {
                table[i] = 1;
                continue;
            }
            if (i == 2) {
                table[i] = 2;
                continue;
            }
            for (int j = 1; (j < span + 1) && (i - j > 0); j++) {
                table[i] += table[i - j];
            }
        }
        return table[stairsNumber];
    }

    @Override
    public String getProcessorName() {
        return "计算上台阶问题的主要算法部分";
    }
}
