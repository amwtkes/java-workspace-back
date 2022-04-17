package com.xiaojin.algorithm.会议安排问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.会议安排问题.MeetingArrangePriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingArrangeComputationProcessor implements MeetingArrangeProcessor {
    @Override
    public void process(MeetingArrangeContext meetingArrangeContext) throws ProcessorException {

    }

    @Override
    public String getProcessorName() {
        return "会议安排 线段树...";
    }
}
