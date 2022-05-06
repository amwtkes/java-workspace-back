package com.xiaojin.algorithm.mics.hanoi;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.mics.hanoi.HanoiPriority.COMPUTATION_FOR;

@Component
@SortOrder(COMPUTATION_FOR)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HanoiComputationProcessor implements HanoiProcessor {
    @Override
    public void process(HanoiContext hanoiContext) throws ProcessorException {
        if (hanoiContext.getSwitcher() != COMPUTATION_FOR) {
            System.out.println("Hanoi 不用for");
            return;
        }
        //需要移动n个盘子，则记录为dp[n]
        int[] dp = new int[hanoiContext.getNr() + 1];
        //dp[1] = 1 如果只剩下一个要移动，则直接移动1步即可
        //dp[n] = 2dp[n-1]+1
        for (int i = 1; i <= hanoiContext.getNr(); i++) {
            if (i == 1) {
                dp[1] = 1;
                continue;
            }
            dp[i] = 2 * dp[i - 1] + 1;
        }
        hanoiContext.setResultNotFinish(dp[hanoiContext.getNr()]);
    }

    @Override
    public String getProcessorName() {
        return "Hanoi for computation...";
    }
}
