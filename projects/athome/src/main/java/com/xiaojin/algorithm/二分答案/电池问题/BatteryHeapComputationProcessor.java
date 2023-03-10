package com.xiaojin.algorithm.二分答案.电池问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;
import java.util.PriorityQueue;

import static com.xiaojin.algorithm.二分答案.电池问题.BatteryPriority.HEAP_COMPUTATION;

@Component
@SortOrder(HEAP_COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatteryHeapComputationProcessor implements BatteryProcessor {
    @Override
    public void process(BatteryContext batteryContext) throws ProcessorException {
        if (batteryContext.getSwitcher() != HEAP_COMPUTATION) {
            System.out.println("不是用堆的解法！");
            return;
        }
        List<Integer> batteries = batteryContext.getBatteries();
        int CarsNr = batteryContext.getCarsNr();
        //大顶堆
        PriorityQueue<Integer> maxTopHeap = new PriorityQueue<>(batteries.size(), ((o1, o2) -> o2 - o1));
        maxTopHeap.addAll(batteries);
        int[] cars = new int[batteryContext.getCarsNr()];
        int ret = 0;
        while (true) {
            //从大顶堆中取出最大的topk个电池去给车充电
            for (int i = 0; i < cars.length; i++) {
                Integer topValue = maxTopHeap.remove();
                /**
                 * 一旦出现了最大值有0的情况
                 * 表示已经不可能有电池给nr辆车充电了，就返回结果
                 */
                if (topValue <= 0) {
                    batteryContext.setResultNotFinish(ret);
                    return;
                }
                cars[i] = topValue;
            }
            ret++;
            //过一个小时候，每个在车里的电池都会减少一格点，然后将电池扔回堆里
            //做完一次充电
            for (int car : cars) {
                maxTopHeap.add(car - 1);
            }
        }
    }

    @Override
    public String getProcessorName() {
        return "电池问题  堆解法...";
    }
}
