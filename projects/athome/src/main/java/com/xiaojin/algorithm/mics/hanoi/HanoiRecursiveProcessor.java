package com.xiaojin.algorithm.mics.hanoi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.mics.hanoi.HanoiPriority.COMPUTATION_RECURSIVE;

@Component
@SortOrder(COMPUTATION_RECURSIVE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HanoiRecursiveProcessor implements HanoiProcessor {
    @Override
    public void process(HanoiContext hanoiContext) throws ProcessorException {
        if (hanoiContext.getSwitcher() != COMPUTATION_RECURSIVE) {
            System.out.println("hanoi 不用递归");
            return;
        }
        assert hanoiContext.getNr() > 0;
        int A = Integer.MAX_VALUE;//A柱
        int B = Integer.MAX_VALUE;//B柱
        int T = Integer.MAX_VALUE;//临时的柱子
        //目的是将nr个盘子从A柱移动到B柱，可以使用T柱做中转
        int total = hanoiProcessor(hanoiContext.getNr(), A, B, T);
        hanoiContext.setResultNotFinish(total);
    }

    /**
     * 表示将nr个盘子从source 移动到 dest，中间使用temp的总步数
     *
     * @param nr          要移动的盘子个数
     * @param source      源柱子
     * @param destination 目的柱子
     * @param temporary   可临时存放的柱子
     */
    private int hanoiProcessor(int nr, int source, int destination, int temporary) {
        //只有一块盘子了，可以直接挪动
        if (nr == 1) {
            return 1;
        }

        int firstStepTotal = hanoiProcessor(nr - 1, source, temporary, destination);
        int secondStepTotal = hanoiProcessor(nr - 1, temporary, destination, source);
        return firstStepTotal + secondStepTotal + 1;
    }

    @Override
    public String getProcessorName() {
        return "Hanoi recursive...";
    }
}
