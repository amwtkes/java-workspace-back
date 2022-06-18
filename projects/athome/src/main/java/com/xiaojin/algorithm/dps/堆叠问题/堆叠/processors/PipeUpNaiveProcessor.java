package com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors;

import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpContext;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.NAIVE;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(NAIVE)
public class PipeUpNaiveProcessor implements PipeUpProcessor {
    @Override
    public void process(PipeUpContext pipeUpContext) throws ProcessorException {
        if (pipeUpContext.getSwitcher() != NAIVE) {
            System.out.println("不使用naive方法！");
            return;
        }
        System.out.println("使用naive方法！");
        List<Integer> items = pipeUpContext.getItems();
    }

    @Override
    public String getProcessorName() {
        return "堆叠问题-蛮力算法...";
    }
}
