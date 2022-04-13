package com.xiaojin.algorithm.二分答案.电池问题;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.xiaojin.algorithm.二分答案.电池问题.BatteryPriority.BINARY_COMPUTATION;
import static com.xiaojin.algorithm.二分答案.电池问题.BatteryPriority.HEAP_COMPUTATION;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatteryService.class, BatteryLoadProcessor.class, BatteryHeapComputationProcessor.class, BatteryBinarySearchComputationProcessor.class})
class BatteryServiceTest {
    @Autowired
    private BatteryService batteryService;

    @Test
    public void test() {
        int run = batteryService.run("battery/1.txt", HEAP_COMPUTATION);
        Assertions.assertEquals(18, run);

        run = batteryService.run("battery/1.txt", BINARY_COMPUTATION);
        Assertions.assertEquals(18, run);
    }
}
