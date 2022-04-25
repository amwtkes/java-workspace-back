package com.xiaojin.algorithm.堆.topk_min_sum_subsequence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.堆.topk_min_sum_subsequence.TmssPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
/**
 * 1- 树的结构跟二项式系数的增长是一致的。Cn1+Cn2+Cn3+....+Cnn = 2^n-1
 * 2- 树的第n层表示包含第n个元素的所有排列组合。第n层的节点数为2^(n-1)个= 第n-1层的所有组合(即包含n-1这个元素的所有组合)+ 不包含n-1的所有组合 + 只包含第n个元素的组合
 * 3- 如何构造这课树？根据2这个公式
 * 3.1 - 将第n-1层的所有组合(即包含n-1这个元素的所有组合).将第n-1层的每个元素后面+上n这个元素，构成新的子序列
 * 3.2 - 将第n-1层所有组合最后一个元素去掉，也就是去掉n-1这个元素后（就是2这个公式上的不包含n-1这个元素的所有集合） +上第n这个元素
 * 4. - 这是一个小顶堆，可以在lgn的时间内完成插入操作
 * 5. - 只要弹出一个，并将这个元素的index按照3这个算法，添加两个进堆，循环k次，即可得解。
 */
public class TmssComputationProcessor implements TmssProcessor {
    @Override
    public void process(TmssContext tmssContext) throws ProcessorException {

    }

    @Override
    public String getProcessorName() {
        return "前k小子序列和 堆算法...";
    }
}
