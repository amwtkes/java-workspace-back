package com.xiaojin.algorithm.二分答案.电池问题;

import com.xiaojin.algorithm.base.ContextHelper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void test2() {
        int run = batteryService.run("battery/2.txt", HEAP_COMPUTATION);
        Assertions.assertEquals(72, run);

        run = batteryService.run("battery/2.txt", BINARY_COMPUTATION);
        Assertions.assertEquals(72, run);
    }

    @Test
    public void test3() {
        int run = batteryService.run("battery/3.txt", HEAP_COMPUTATION);
        Assertions.assertEquals(4, run);

        run = batteryService.run("battery/3.txt", BINARY_COMPUTATION);
        Assertions.assertEquals(4, run);
    }

    @Test
    public void test4() {
        for (int i = 0; i < 100; i++) {
            List<Integer> batteries = ContextHelper.toList(ContextHelper.randomArray(20, 50, false));
            int nrCar = ContextHelper.randomInt(10, false);
            int runHeap = batteryService.run(batteries, nrCar, HEAP_COMPUTATION);

            int runBinary = batteryService.run(batteries, nrCar, BINARY_COMPUTATION);
            System.out.println(batteries);
            System.out.println(nrCar);
            Assertions.assertEquals(runHeap, runBinary);
        }
    }

    @Test
    public void test5() {
        List<Integer> batteries = Lists.list(36, 37, 1, 9, 40, 46, 24, 30, 8, 14, 14, 32, 12, 13, 11, 25, 0, 21, 39, 1);
        int nrCar = 5;
        int runHeap = batteryService.run(batteries, nrCar, HEAP_COMPUTATION);
        int runBinary = batteryService.run(batteries, nrCar, BINARY_COMPUTATION);
        System.out.println(batteries);
        System.out.println(nrCar);
        Assertions.assertEquals(runHeap, runBinary);
    }
}
