package com.xiaojin.algorithm.会议安排问题;

import com.xiaojin.algorithm.datastructure.tree.SegmentTree;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.*;

import static com.xiaojin.algorithm.会议安排问题.MeetingArrangePriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingArrangeComputationProcessor implements MeetingArrangeProcessor {
    @Override
    public void process(MeetingArrangeContext meetingArrangeContext) throws ProcessorException {
        if (meetingArrangeContext.getSwitcher() != COMPUTATION) {
            System.out.println("不是用的线段树");
            return;
        }
        List<MeetingArrangeContext.Meeting> meetingList = meetingArrangeContext.getMeetingList();
        Optional<MeetingArrangeContext.Meeting> maxTime = meetingList.stream().max(Comparator.comparingInt(MeetingArrangeContext.Meeting::getEnd));
        if (maxTime.isEmpty()) {
            throw new ProcessorException("no max time!");
        }
        int[] arr = new int[maxTime.get().getEnd()];
        Arrays.fill(arr, 0);
        SegmentTree segmentTree = new SegmentTree(arr);

        /*
         * 从后向前遍历所有会议！顺序很重要
         * 每遍历一个区间，如果query结果是0，说明没有被占用，就add到1；
         * 如果query结果不是0，就有占用，则肯定是被后面的会议替换掉，不加入结果队列，但是也必须将这个区间加1
         * 因为，不加1的话，后面会漏掉被覆盖的区间。比如：[1,3] [2,4] [3,5]
         * 【3，5】遮住【2，4】如果不把【2，4】标记，【1，3】会成为答案的一部分，其实应该不行。
         */
        List<MeetingArrangeContext.Meeting> ret = new ArrayList<>();
        for (int i = meetingList.size() - 1; i >= 0; i--) {
            MeetingArrangeContext.Meeting meeting = meetingList.get(i);
            //l是从1开始的索引，最大长度的索引是arr.length
            long value = segmentTree.query(meeting.getBegin(), meeting.getEnd(), 1, arr.length, 1);
            segmentTree.add(meeting.getBegin(), meeting.getEnd(), 1, 1, arr.length, 1);
            if (value == 0L) {
                ret.add(meeting);
            }
        }
        meetingArrangeContext.setResultAndFinish(ret);
    }

    @Override
    public String getProcessorName() {
        return "会议安排 线段树...";
    }
}
