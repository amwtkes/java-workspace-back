package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import com.xiaojin.algorithm.base.ContextHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.xiaojin.algorithm.堆.topk_min_sum_subsequence.TmssPriority.NAIVE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TmssService.class, TmssLoadProcessor.class, TmssNaiveProcessor.class})
class TmssServiceTest {
    @Autowired
    private TmssService tmssService;

    @Test
    public void test() {
        int[] ints = ContextHelper.randomArray(10, 50, false);
        List<Integer> items = ContextHelper.toList(ints);
        int k = TmssContext.randomK(items.size());
        List<Integer> run = tmssService.run(items, k, NAIVE);
        System.out.println("items->" + items);
        System.out.println("k->" + k);
        System.out.println(run);
    }
}
