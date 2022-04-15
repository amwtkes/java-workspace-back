package com.xiaojin.algorithm.会议安排问题;

import com.xiaojin.algorithm.base.ContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.base.ContextHelper.getInputStringListFromClassPathFile;
import static com.xiaojin.algorithm.会议安排问题.MeetingArrangePriority.LOAD;

@Component
@SortOrder(LOAD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingArrangeLoadProcessor implements MeetingArrangeProcessor {
    private final ResourceLoader resourceLoader;

    @Override
    public void process(MeetingArrangeContext meetingArrangeContext) throws ProcessorException {
        try {
            List<String> inputStrings = getInputStringListFromClassPathFile(resourceLoader, meetingArrangeContext.getInputDataPath());
            if (inputStrings.size() <= 0) {
                throw new ProcessorException("Input is empty!");
            }
            for (String line : inputStrings) {
                ArrayList<String> meetings = ContextHelper.splitter(line, "Integer.MAX_VALUE");
                for (String meeting : meetings) {
                    meeting = meeting.replace("]", "");
                    meeting = meeting.replace("[", "");
                    String[] rawMeetingTime = meeting.split("-");
                    MeetingArrangeContext.Meeting m = new MeetingArrangeContext.Meeting(Integer.parseInt(rawMeetingTime[0]), Integer.parseInt(rawMeetingTime[1]));
                    meetingArrangeContext.getMeetingList().add(m);
                }
            }
        } catch (IOException e) {
            throw new ProcessorException(e.toString());
        }
    }

    @Override
    public String getProcessorName() {
        return "会议安排 loading...";
    }
}
