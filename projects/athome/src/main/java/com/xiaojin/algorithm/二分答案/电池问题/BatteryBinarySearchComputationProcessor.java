package com.xiaojin.algorithm.二分答案.电池问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;
import java.util.stream.Collectors;

import static com.xiaojin.algorithm.二分答案.电池问题.BatteryPriority.BINARY_COMPUTATION;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(BINARY_COMPUTATION)
public class BatteryBinarySearchComputationProcessor implements BatteryProcessor {
    @Override
    public void process(BatteryContext batteryContext) throws ProcessorException {
        if (batteryContext.getSwitcher() != BINARY_COMPUTATION) {
            System.out.println("不使用二分答案法！");
            return;
        }
        List<Integer> batteries = batteryContext.getBatteries().stream().sorted().collect(Collectors.toList());
        int size = batteries.size();

        int carNr = batteryContext.getCarsNr();
        int[] sum = new int[size]; //缓存一下前i个的和
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sum[0] = batteries.get(0);
            } else {
                sum[i] = sum[i - 1] + batteries.get(i);
            }
        }

        //电池列表能够充电的最大值，当然肯定不会达到这个值
        //想象对carNr的搜索在[1 - sum[size-1]]范围内展开
        //二分搜索这个最大的值 在batteries范围内，carNr个车最大能撑多少分钟。
        int topRangeOfBattery = sum[size - 1];
        int leftValue = 1;
        int rightValue = topRangeOfBattery;
        int midValue = leftValue + (rightValue - leftValue) / 2;
        int result = -1;
        while (leftValue <= rightValue) {
            if (testFunction(batteries, sum, carNr, midValue)) {
                result = midValue;
                leftValue = midValue + 1;
            } else {
                rightValue = midValue - 1;
            }
            midValue = leftValue + (rightValue - leftValue) / 2;
        }
        batteryContext.setResultAndFinish(result);
    }

    private boolean testFunction(List<Integer> sortedBatteries, int[] sums, int carNr, int times) {

        //找到第一个大于等于times的battery
        int firstIndex = binarySearchFirstBiggerOrEqualIndex(sortedBatteries, times);
        //所有的电池都比times小
        int size = sortedBatteries.size();
        if (firstIndex == -1) {
            return sums[size - 1] >= carNr * times;
        }
        int biggerNr = size - firstIndex;

        //如果大于或者等于times的个数大于实际要充电的车辆数，肯定可以啦
        if (biggerNr >= carNr) {
            return true;
        }

        //需要用零碎电池充电的汽车数量
        int needLingsuiCarNr = carNr - biggerNr;
        int lingSuiCapacity = sums[firstIndex - 1];
        return lingSuiCapacity >= needLingsuiCarNr * times;
    }

    /**
     * 查到第一个大于或者等于value的index位置。
     *
     * @param sortedBatteries 数组
     * @param value           要找的值
     * @return 第一个大于等于value的index位置。如果没有也就是全部都小于value，返回-1.
     */
    private int binarySearchFirstBiggerOrEqualIndex(List<Integer> sortedBatteries, int value) {
        int leftIndex = 0, rightIndex = sortedBatteries.size() - 1;
        int midIndex = getMidIndex(leftIndex, rightIndex);
        int firstBiggerThanTimesIndex = -1;
        while (leftIndex <= rightIndex) {
            Integer midValue = sortedBatteries.get(midIndex);
            if (midValue >= value) {
                firstBiggerThanTimesIndex = midIndex;
                rightIndex = midIndex - 1;
            } else {
                leftIndex = midIndex + 1;
            }
            midIndex = getMidIndex(leftIndex, rightIndex);
        }
        return firstBiggerThanTimesIndex;
    }

    private int getMidIndex(int leftIndex, int rightIndex) {
        return leftIndex + (rightIndex - leftIndex) / 2;
    }

    @Override
    public String getProcessorName() {
        return "电池问题 二分答案法...";
    }
}
