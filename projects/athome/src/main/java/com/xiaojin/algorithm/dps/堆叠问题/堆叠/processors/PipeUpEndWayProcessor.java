package com.xiaojin.algorithm.dps.堆叠问题.堆叠.processors;

import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpContext;
import com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.List;

import static com.xiaojin.algorithm.dps.堆叠问题.堆叠.PipeUpPriority.END;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
@SortOrder(END)
public class PipeUpEndWayProcessor implements PipeUpProcessor {
    @Override
    public void process(PipeUpContext pipeUpContext) throws ProcessorException {
        if (pipeUpContext.getSwitcher() != END) {
            System.out.println("不使用END方法！");
            return;
        }
        System.out.println("使用end方法！");
        List<Integer> items = pipeUpContext.getItems();
    }

    @Override
    public String getProcessorName() {
        return "堆叠问题-end方法...";
    }
}
