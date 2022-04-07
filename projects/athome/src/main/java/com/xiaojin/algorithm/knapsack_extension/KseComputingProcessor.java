package com.xiaojin.algorithm.knapsack_extension;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.*;

import static com.xiaojin.algorithm.knapsack_extension.KsePriority.COMPUTATION;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(COMPUTATION)
public class KseComputingProcessor implements KseProcessor {
    @Override
    public void process(KseContext kseContext) throws ProcessorException {
        List<KseContext.Item> items = kseContext.getItems();
        List<KseContext.Item> itemsReversed = new ArrayList<>(items);
        Collections.reverse(itemsReversed);
        List<KseContext.Ask> asks = kseContext.getAsks();
        Optional<KseContext.Item> maxVolumeOpt = items.stream().max(Comparator.comparingInt(KseContext.Item::getVolume));
        if (maxVolumeOpt.isEmpty()) {
            throw new ProcessorException("Errors");
        }
        int maxVolume = maxVolumeOpt.get().getVolume();
        //volume第一行不用i==0这行，为了方便
        int[][] dpA = new int[items.size()][maxVolume + 1];//顺序的dp表，值为取0---i范围内的最大价值[第一个维度是0-i可选][bag的容积]
        int[][] dpB = new int[items.size()][maxVolume + 1];//逆序的dp表，值为取0---i范围内的最大价值
        computeDp(items, dpA, maxVolume);
        computeDp(itemsReversed, dpB, maxVolume);

        List<Integer> answers = new ArrayList<>(asks.size());
        for (int i = 0; i < asks.size(); i++) {
            answers.add(computeAsk(items, dpA, dpB, asks.get(i)));
        }
        kseContext.setResultAndFinish(answers);
    }

    /***
     * 算法描述：
     * 类似分治算法：
     * a1 a2 a3 a4 a5(delete) a6 a7 a8
     * 相当于将一个数组分成了两个部分A={a1,a2,a3,a4} B={a6,a7,a8}
     * 那么最大的价值应该等于=max{dpA[4,w],dpB[3,w],dpA[4,w-i]+dpB[3,i]} //DpA与DpB分别是正序与逆序的dp二维表格。
     * 类似于分治算法的归并的部分，这部分的时间复杂度为O(n)
     * 情况1：最大值在前半部分 dpA[4,w] -- w为背包容量，4表示可取的item下标范围
     * 情况2：最大值在后半部分 dpB[3,w] -- B因为是逆序，所以它的前3 item就是A的后3个item
     * 说明：之所以分成两部分，是因为不管是A还是B都依赖一个去掉的item来计算这个item后面的表格，如例子中的a5，那么对于dpA来说，index>5的行都是依赖a5的，所以去掉a5后面的表格不能用了，同理dpB也是。
     * 情况3：
     * 将w拆解成两个部分，分别代入dbA与dpB来计算并相加得到跨越删除元素下标左右两边的最大值情况。
     * 如:w=5
     * 1. dpA[4][4]+dpB[3][1]
     * 2. dpA[4][3]+dpB[3][2]
     * 3. dpA[4][2]+dpB[3][3]
     * 4. dpA[4][1]+dpB[3][4]
     *
     * 最后的结果为max{情况1，情况2，情况3}
     * @param items 物品
     * @param dpA 顺序的dp最大值
     * @param dpB 逆序的dp最大值
     * @param ask 一次提问
     * @return 减少一个物品后最大的价值
     */
    private Integer computeAsk(List<KseContext.Item> items, int[][] dpA, int[][] dpB, KseContext.Ask ask) {
        int deleteIndex = ask.getRemoveIndex();
        int bagVolume = ask.getBagVolume();
        int maxInnerValue = 0;
        if (deleteIndex == 0) {
            return dpB[items.size() - 2][bagVolume];
        }
        if (deleteIndex == items.size() - 1) {
            return dpA[items.size() - 2][bagVolume];
        }
        int maxAIndex = deleteIndex - 1;
        /*
         * 注意B的索引是倒着的A的0就是B的size-1
         * items.size -1-deleteIndex是B数组可以查表的元素个数
         * 最大的B索引还要-1
         */
        int maxBIndex = items.size() - deleteIndex - 1 - 1;
        for (int i = 1; i < bagVolume; i++) {
            int tempMax = dpA[maxAIndex][i] + dpB[maxBIndex][bagVolume - i];
            if (tempMax > maxInnerValue) {
                maxInnerValue = tempMax;
            }
        }
        return Math.max(dpA[maxAIndex][bagVolume], Math.max(dpB[maxBIndex][bagVolume], maxInnerValue));
    }

    /***
     * 用红绿灯来求一组item的最大价值。
     * 填充dp这个二维数组
     * f(n,w) = max{f(n-1,w-wi)+vi,f(n-1,w)}
     */
    private void computeDp(List<KseContext.Item> items, int[][] dp, int maxVolume) {
        //最多只能取0--->i个item
        for (int i = 0; i < items.size(); i++) {
            int volI = items.get(i).getVolume();
            int valueI = items.get(i).getValue();
            for (int volume = 1; volume <= maxVolume; volume++) {
                if (i == 0) { //只有一个item
                    dp[i][volume] = volI <= volume ? valueI : 0;
                    continue;
                }
                if (volI < volume) {
                    dp[i][volume] = Math.max(dp[i - 1][volume - volI] + valueI, dp[i - 1][volume]);
                    continue;
                }
                if (volI == volume) {
                    dp[i][volume] = Math.max(valueI, dp[i - 1][volume]);
                    continue;
                }
                dp[i][volume] = dp[i - 1][volume];
            }
        }
    }

    @Override
    public String getProcessorName() {
        return "背包问题扩展 计算中...";
    }
}
