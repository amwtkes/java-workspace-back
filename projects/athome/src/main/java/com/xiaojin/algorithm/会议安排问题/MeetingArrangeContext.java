package com.xiaojin.algorithm.会议安排问题;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingArrangeContext extends AlgorithmGeneralContext {
    public MeetingArrangeContext(String path, int switcher) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
            this.setSwitcher(switcher);
        } else {
            this.setInputDataPath("meeting_arrange/1.txt");
        }
    }

    private List<Meeting> meetingList = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    static class Meeting {
        private int begin;
        private int end;

        @Override
        public String toString() {
            return "Meeting{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meeting meeting = (Meeting) o;
            return begin == meeting.begin && end == meeting.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(begin, end);
        }
    }
}
