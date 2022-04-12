package com.xiaojin.algorithm.dps.LCS.processors1;

import com.xiaojin.algorithm.dps.LCS.base.Lcs1Context;
import com.xiaojin.algorithm.dps.LCS.base.Lcs1Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.xiaojin.algorithm.dps.LCS.processors1.Lcs1Priority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs1ComputationProcessor implements Lcs1Processor {
    @Override
    public void process(Lcs1Context lcs1Context) throws ProcessorException {
        lcs1Context.assertInputNotBeNull();
        List<Integer> items = lcs1Context.getItems();
        Map<Integer, Integer> map = lcs1Context.getMap();

        Integer maxLength = 0;
        Integer maxLengthKey = 0;
        for (Integer e : items) {
            /**
             * 判断是否已经处理过这个值了，如果处理过了，就略过。
             */
            if (map.containsKey(e)) {
                continue;
            }
            /**
             判断前一个数是否已经存在过了
             如果已经存在,则将这个值更新到前一个值得key上，覆盖。然后value（长度）+1
             * */
            if (map.containsKey(e - 1)) {
                Integer updateLength = (map.get(e - 1) + 1);
                map.put(e, updateLength);
                map.remove(e - 1);
                if (updateLength > maxLength) {
                    maxLength = updateLength;
                    maxLengthKey = e;
                }
            } else {
                /**
                 * 如果e前面的值没有，则连不成串，就加入map，初始化长度为1.
                 */
                map.put(e, 1);
                if (maxLength < 1) {
                    maxLength = 1;
                    maxLengthKey = e;
                }
            }
        }
        List<Integer> resultList = genResultList(map, maxLength, maxLengthKey);
        lcs1Context.setResultAndFinish(resultList);
    }

    private List<Integer> genResultList(Map<Integer, Integer> map, Integer maxLength, Integer maxLengthKey) throws ProcessorException {
        if (!map.containsKey(maxLengthKey) || map.get(maxLengthKey) != maxLength) {
            throw new ProcessorException("答案没有在map中！maxLength:" + maxLength + " maxLengthKey:" + maxLengthKey);
        }
        List<Integer> ret = new ArrayList<>(maxLength);
        for (int i = maxLength - 1; i >= 0; i--) {
            ret.add(maxLengthKey - i);
        }
        return ret;
    }

    @Override
    public String getProcessorName() {
        return "Lcs1 computation";
    }
}
