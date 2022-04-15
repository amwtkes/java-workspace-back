package com.xiaojin.algorithm.会议安排问题;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.List;

import static com.xiaojin.algorithm.会议安排问题.MeetingArrangePriority.NAIVE;

@Component
@SortOrder(NAIVE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingArrangeNaiveProcessor implements MeetingArrangeProcessor {
    @Override
    public void process(MeetingArrangeContext meetingArrangeContext) throws ProcessorException {
        if (meetingArrangeContext.getSwitcher() != NAIVE) {
            System.out.println("不走naive");
            return;
        }

        List<MeetingArrangeContext.Meeting> meetings = meetingArrangeContext.getMeetingList();
        int size = meetings.size();
        List<MeetingArrangeContext.Meeting> ret = new ArrayList<>();
        //通过将最大的时间计算出来，形成以最小时间间隔（1）为单位的数组，用来记录从后向前的会议占用情况。
        //让meeting的开始与结束时间能够直接计算，这个数组的下标从1开始算起。
        boolean[] occupyArray = new boolean[getMaxEndValue(meetings) + 1];
        for (int i = size - 1; i >= 0; i--) {
            MeetingArrangeContext.Meeting tmpMeeting = meetings.get(i);
            boolean isNotOccupied = true;
            //
            for (int j = tmpMeeting.getBegin(); j < tmpMeeting.getEnd(); j++) {
                if (occupyArray[j]) {
                    isNotOccupied = false;
                }
                occupyArray[j] = true;
            }
            if (isNotOccupied) {
                ret.add(tmpMeeting);
            }
        }
        meetingArrangeContext.setResultNotFinish(ret);
    }

    private int getMaxEndValue(List<MeetingArrangeContext.Meeting> meetings) {
        int maxTime = 0;
        for (MeetingArrangeContext.Meeting meeting : meetings) {
            if (meeting.getEnd() > maxTime) {
                maxTime = meeting.getEnd();
            }
        }
        return maxTime;
    }

    @Override
    public String getProcessorName() {
        return "会议安排问题 naiving...";
    }
}
