package com.xiaojin.algorithm.会议安排问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtime.processor.defaultprocessor.DefaultProcessorResult;
import runtime.processor.defaultprocessor.DefaultProcessorService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingArrangeService extends DefaultProcessorService {
    private final List<MeetingArrangeProcessor> processors;

    public List<MeetingArrangeContext.Meeting> run(String path, int switcher) {
        MeetingArrangeContext meetingArrangeContext = new MeetingArrangeContext(path, switcher);
        DefaultProcessorResult<Object> objectDefaultProcessorResult = this.runProcessors(processors, meetingArrangeContext);
        return (List<MeetingArrangeContext.Meeting>) objectDefaultProcessorResult.getResult();
    }
}
