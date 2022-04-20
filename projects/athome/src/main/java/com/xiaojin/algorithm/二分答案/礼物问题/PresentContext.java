package com.xiaojin.algorithm.二分答案.礼物问题;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class PresentContext extends AlgorithmGeneralContext {
    public PresentContext(String path, int switcher) {
        if (Strings.isNotBlank(path)) {
            this.setInputDataPath(path);
            this.setSwitcher(switcher);
        } else {
            this.setInputDataPath("present/1.txt");
        }
    }

    private List<Integer> items;
    private int nr;

    @AllArgsConstructor
    @Getter
    static class PresentResult {
        private List<Integer> indexes;
        private int nr;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PresentResult that = (PresentResult) o;
            return nr == that.nr && indexesEqual(that.indexes);
        }

        private boolean indexesEqual(List<Integer> targetList) {

            if (targetList == null && this.indexes == null) {
                return true;
            }
            if (targetList == null) {
                return false;
            }
            if (this.indexes == null) {
                return false;
            }

            if (targetList == this.indexes) {
                return true;
            }
            if (targetList.size() != this.indexes.size()) {
                return false;
            }
            for (int i = 0; i < targetList.size() - 1; i++) {
                if (!this.indexes.get(i).equals(targetList.get(i))) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(indexes, nr);
        }
    }
}
