package com.xiaojin.algorithm.dps.LCS.processor3;

import com.xiaojin.algorithm.dps.LCS.base.Lcs2Context;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.xiaojin.algorithm.dps.LCS.processor3.Lcs3Priority.PHASE1;

@Component
@SortOrder(PHASE1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs3Phase1Processor implements Lcs3Processor {
    @Override
    public void process(Lcs2Context lcs2Context) throws ProcessorException {
        /**
         * item的index代表以index元素结尾的子序列
         * item的值表示以index结尾的子序列的最长连续子序列的长度值。
         *
         * A[i]-以i为结尾的最长连续（自增）子序列的长度值。(a[i]包含在这个最长子序列中)
         * A[i]=看a[i]-1这个值在index为[0,i-1]这个区间是否存在（注意这里是n的，真正的dp这里应该是O(1)的。当然可以用hash来，那么就相当于Lcs1的做法了。）
         * if存在假设为j则，A[i] = A[j]+1
         * if不存在，则A[i] = 1
         */
        List<Integer> items = lcs2Context.getItems();
        lcs2Context.setA(new ArrayList<>(items.size()));
        List<Integer> A = lcs2Context.getA();
        for (int i = 0; i < items.size(); i++) {
            lcs2Context.getA().add(0);
        }

        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                A.set(0, 1);
                continue;
            }

            Integer iPreviousValue = items.get(i) - 1;
            for (int j = i - 1; j >= 0; j--) {
                if (Objects.equals(items.get(j), iPreviousValue)) {
                    //找离i最近的j即可。
                    A.set(i, A.get(j) + 1);
                    break;
                }
            }
            //没找到a[i]的前序值
            if (A.get(i) == 0) {
                A.set(i, 1);
            }
        }
    }

    @Override
    public String getProcessorName() {
        return "Lcs3 phase1.";
    }
}
