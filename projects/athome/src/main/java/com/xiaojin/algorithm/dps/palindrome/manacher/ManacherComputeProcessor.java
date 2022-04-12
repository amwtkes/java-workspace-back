package com.xiaojin.algorithm.dps.palindrome.manacher;

import com.xiaojin.algorithm.dps.palindrome.PalindromeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.dps.palindrome.manacher.ManacherPriority.COMPUTATION;

@Component
@SortOrder(COMPUTATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManacherComputeProcessor implements ManacherProcessor {
    enum Flag {
        In,//Ri'在Rc'以内
        Beyond,//Ri'超越了Rc'
        Equal, // Ri'与Rc'相同
        Out,
    }

    @Override
    public void process(ManacherContext manacherContext) throws ProcessorException {
        manacherContext.assertInputNotBeNull();
        String sourceStr = manacherContext.getInput();
        int n = sourceStr.length();
        manacherContext.setDp(new int[n]);

        //以第i个元素为对称index（Ci）的对称半径的(Ri) index
        int[] dpCenterIndexAndRIndex = manacherContext.getDp();

        //R最长半径的index，C为R的对称中心index
        int R = 0, C = 0;
        dpCenterIndexAndRIndex[0] = 0;
        for (int i = 1; i < n; i++) {
            //在R外面 或者等于R
            Flag status = getStatus(dpCenterIndexAndRIndex, R, C, i);
            if (status == Flag.Out || status == Flag.Equal) {
                for (int j = 1; j < n; j++) {
                    //左边界，有边界与不相等 都要停止
                    if (i - j < 0 || i + j > n - 1 || sourceStr.charAt(i - j) != sourceStr.charAt(i + j)) {
                        break;
                    }
                    if (sourceStr.charAt(i - j) == sourceStr.charAt(i + j)) {
                        R = i + j;
                        C = i;
                        dpCenterIndexAndRIndex[i] = i + j;
                    }
                }
            }
            //i在R内部
            if (status == Flag.In) {
                int iMirrorIndex = getMirrorI(C, i);
                //i`的半径Ri`的index(右侧)
                int iMirrorRIndex = dpCenterIndexAndRIndex[iMirrorIndex];
                //i的半径长度
                int iR = iMirrorRIndex - iMirrorIndex;
                dpCenterIndexAndRIndex[i] = i + iR;
                continue;
            }
            //到外侧，超越了
            if (status == Flag.Out) {
                dpCenterIndexAndRIndex[i] = R;
            }
        }

        //汇总
        int maxRight = 0, maxLeft = 0, maxDiameter = 0;
        for (int i = 0; i < dpCenterIndexAndRIndex.length; i++) {
            int tempRi = dpCenterIndexAndRIndex[i] - i;//不包括i自己
            int diameterI = tempRi * 2 + 1;
            if (diameterI > maxDiameter) {
                maxDiameter = diameterI;
                maxLeft = i - tempRi;
                maxRight = i + tempRi;
            }
        }
        String retStr = sourceStr.substring(maxLeft, maxRight + 1);
        retStr = PalindromeHelper.deleteSharpOfAString(retStr);
        manacherContext.setResultAndFinish(retStr);
    }

    private Flag getStatus(int dp[], int R, int C, int currentI) throws ProcessorException {
        if (currentI < R) {
            //i相对于C的对称点 i` 的index
            int iMirrorIndex = getMirrorI(C, currentI);
            //i`的半径Ri`的index(右侧)
            int iMirrorRIndex = dp[iMirrorIndex];
            //i的半径长度
            int iR = iMirrorRIndex - iMirrorIndex;
            int iMirrorLIndex = iMirrorRIndex - iR;
            //R的右侧对称点index
            int LMirrorIndex = getMirrorI(C, R);

            //i`在内部
            if (iMirrorLIndex > LMirrorIndex) {
                return Flag.In;
            } else if (LMirrorIndex > iMirrorLIndex) {
                return Flag.Beyond;
            } else {
                return Flag.Equal;
            }
        }
        return Flag.Out;
    }

    private int getMirrorI(int cIndex, int rIndex) throws ProcessorException {
        if (cIndex < 0 || rIndex < 0 || cIndex > rIndex) {
            throw new ProcessorException("wrong parameter. cIndex:" + cIndex + " rIndex:" + rIndex);
        }
        int r = rIndex - cIndex;
        int lIndex = cIndex - r;
        if (lIndex < 0) {
            throw new ProcessorException("wrong parameter. cIndex:" + cIndex + " rIndex:" + rIndex);
        }
        return lIndex;
    }

    @Override
    public String getProcessorName() {
        return "Manacher computing...";
    }
}
