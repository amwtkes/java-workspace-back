package com.xiaojin.algorithm.会议安排问题;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.xiaojin.algorithm.会议安排问题.MeetingArrangePriority.NAIVE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MeetingArrangeService.class, MeetingArrangeLoadProcessor.class, MeetingArrangeNaiveProcessor.class})
class MeetingArrangeServiceTest {
    @Autowired
    private MeetingArrangeService meetingArrangeService;

    @Test
    public void test() {
        List<MeetingArrangeContext.Meeting> run = meetingArrangeService.run("meeting_arrange/1.txt", NAIVE);
        Assertions.assertIterableEquals(Lists.newArrayList(new MeetingArrangeContext.Meeting(1, 3)), run);

        run = meetingArrangeService.run("meeting_arrange/2.txt", NAIVE);
        Assertions.assertIterableEquals(Lists.newArrayList(new MeetingArrangeContext.Meeting(10, 12), new MeetingArrangeContext.Meeting(3, 10)), run);
    }
}
