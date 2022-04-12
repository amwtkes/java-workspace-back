package com.xiaojin.algorithm.dps.palindrome.processor2;

import com.xiaojin.algorithm.base.AlgorithmGeneralContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@Data
public class P2Context extends AlgorithmGeneralContext {
    public P2Context(String path) {
        if (Strings.isBlank(path)) {
            this.setInputDataPath("palindrome/1.txt");
            return;
        }
        this.setInputDataPath(path);
    }

    /**
     * A的含义
     * 以i为结尾（包含a[i]）的最长回文的leftIndex
     * 算法将中心法（见p1）的过程拆成两个阶段，第一个阶段先求A[0-n]的值
     * 第二个阶段将A[]中的最大值找到，这个解就是整个问题的解
     *
     * A[i] =
     * if a[i] == a[A[i-1]-1] 形成更大的回文串，并且这个是a[0->i]最长的回文串。证明见md
     *      A[i] = A[i-1]-1
     * if not 则A[i]只可能比A[i-1]大，也就是更靠右，因为a[i] != a[A[i-1]-1]
     * 压住a[i]，从A[i-1]这个位置开始，向前，依次判断跟a[i]是否相等.直到找到新的回文，这个跟中心法是一样的。
     * 所以这步是n^2的
     */
    private int A[];
}
