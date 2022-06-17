package com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.processors;

import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSContext;
import com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.最长递增子序列问题.LISSPriority.DP;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(DP)
public class LissDpProcessor implements LISSProcessor {
    @Override
    public void process(LISSContext lissContext) throws ProcessorException {
        if (lissContext.getSwitcher() != DP) {
            System.out.println("不使用pure dp");
            return;
        }
        List<Integer> items = lissContext.getItems();
        /**
         * dp[i] - 以i为结尾的最长子序列的长度
         */
        int[] dp = new int[items.size()];
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                dp[i] = 1;
                continue;
            }
            int iValue = items.get(i);

            //从i开始向前查找，遍历所有j=i->0的索引，求出item[j]<item[i] && dp[j]最大的那个，dp[i] = max {dp[j]} + 1
            int maxDp = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (items.get(j) < iValue && maxDp < dp[j]) {
                    maxDp = dp[j];
                }
            }
            dp[i] = maxDp + 1;
        }
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }
        lissContext.setResultNotFinish(maxLength);
    }

    @Override
    public String getProcessorName() {
        return "Liss 最长递增子序列 pure DP....";
    }
}
