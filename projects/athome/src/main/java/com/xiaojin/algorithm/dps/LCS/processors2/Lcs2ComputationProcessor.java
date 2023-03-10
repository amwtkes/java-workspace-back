package com.xiaojin.algorithm.dps.LCS.processors2;

import com.xiaojin.algorithm.dps.LCS.base.Lcs1Context;
import com.xiaojin.algorithm.dps.LCS.base.Lcs2Context;
import com.xiaojin.algorithm.dps.LCS.base.Lcs2Processor;
import com.xiaojin.algorithm.dps.LCS.processors1.Lcs1ComputationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.xiaojin.algorithm.dps.LCS.processors2.Lcs2Priority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Lcs2ComputationProcessor implements Lcs2Processor {
    private final Lcs1ComputationProcessor lcs1ComputationProcessor;

    @Override
    public void process(Lcs2Context lcs2Context) throws ProcessorException {
        lcs2Context.assertInputNotBeNull();
        Lcs1Context lcs1Context = new Lcs1Context();
        lcs1Context.setItems(toSetList(lcs2Context));
        lcs1Context.setInput(lcs2Context.getInput());
        lcs1ComputationProcessor.process(lcs1Context);

        Map<Integer, Integer> map = lcs1Context.getMap();
        Map<Integer, Integer> mergedMap = mergeMap(map);
        Optional<Map.Entry<Integer, Integer>> max = mergedMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue));
        if (max.isEmpty()) {
            throw new ProcessorException("error!");
        }

        Integer maxLength = max.get().getValue();
        Integer maxLengthKey = max.get().getKey();

        List<Integer> resultList = genResultList(mergedMap, maxLength, maxLengthKey);
        lcs2Context.setResultAndFinish(resultList);
    }

    private List<Integer> toSetList(Lcs2Context lcs2Context) {
        List<Integer> items = lcs2Context.getItems();
        Set<Integer> sets = new HashSet<>();
        List<Integer> ret = new ArrayList<>();
        items.forEach(integer -> {
            if (!sets.contains(integer)) {
                ret.add(integer);
                sets.add(integer);
            }
        });
        return ret;
    }

    private Map<Integer, Integer> mergeMap(Map<Integer, Integer> map) {
        Map<Integer, Integer> ret = new ConcurrentHashMap<>(map);
        map.forEach((key, value) -> {
            /**
             * ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? 5???4???3???2
             * map??????
             * 5 1
             * 4 1
             * 3 1
             * 2 1
             * ???????????????key???5??? - value???1??????????????????????????????????????????????????????????????????5-1=4???
             * ??????????????????????????????4??????????????????????????????????????????????????????
             * 5 1
             * 4 1
             * ????????????
             * 5 2
             * ???????????????????????????
             */
            Integer valueTemp = value;
            Integer previousKey = key - valueTemp;
            while (ret.containsKey(previousKey)) {
                ret.put(key, valueTemp + ret.get(previousKey));
                ret.remove(previousKey);
                previousKey = key - ret.get(key);
                valueTemp = ret.get(key);
            }
        });
        return ret;
    }

    private List<Integer> genResultList(Map<Integer, Integer> map, Integer maxLength, Integer maxLengthKey) throws ProcessorException {
        if (!map.containsKey(maxLengthKey) || map.get(maxLengthKey) != maxLength) {
            throw new ProcessorException("???????????????map??????maxLength:" + maxLength + " maxLengthKey:" + maxLengthKey);
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
