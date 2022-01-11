package com.xiaojin.algorithm.p_recursive.race_n;

import com.xiaojin.algorithm.base.AlgorithmRunInfo;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsContext;
import com.xiaojin.algorithm.p_recursive.base.ClimbStairsProcessor;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.CommonProcessorConst;
import runtime.processor.baseprocessor.ProcessorException;

import static com.xiaojin.algorithm.base.ContextHelper.isNumeric;

@Component
@SortOrder(CommonProcessorConst.PROCESSOR_IGNORED)
public class InitProcessor implements ClimbStairsProcessor {
    private long eclipseTime = 0L;

    @Override
    public AlgorithmRunInfo getRunInfo() {
        return AlgorithmRunInfo.builder()
                .algorithmGeneralProcessor(this)
                .eclipseTime(this.eclipseTime)
                .info("有n级台阶，每次能够上k级，问有多少种不同的攀登方法？")
                .build();
    }

    @Override
    public void process(ClimbStairsContext climbStairsContext) throws ProcessorException {
        int holder;
        if (climbStairsContext.getScanner() != null) {
            while (true) {
                System.out.println("输入台阶级数：");
                String next = climbStairsContext.getScanner().next();
                if (isNumeric(next)) {
                    holder = Integer.parseInt(next);
                    break;
                } else {
                    System.out.println("非法字符，继续输入数字！");
                }
            }
            climbStairsContext.setStairsNumber(holder);

            while (true) {
                System.out.println("输入每次可以上级数：");
                String next = climbStairsContext.getScanner().next();
                if (isNumeric(next)) {
                    holder = Integer.parseInt(next);
                    break;
                } else {
                    System.out.println("非法字符，继续输入数字！");
                }
            }
            climbStairsContext.setClimbSpan(holder);
            System.out.println("输入完毕！");
        }
    }

    @Override
    public String getProcessorName() {
        return "爬楼梯问题-- 初始化";
    }
}
