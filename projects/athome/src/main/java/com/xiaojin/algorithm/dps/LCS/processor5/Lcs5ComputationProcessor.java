package com.xiaojin.algorithm.dps.LCS.processor5;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runtime.processor.annotation.SortOrder;
import runtime.processor.baseprocessor.ProcessorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import static com.xiaojin.algorithm.dps.LCS.processor5.Lcs5Priority.COMPUTATION;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SortOrder(COMPUTATION)
public class Lcs5ComputationProcessor implements Lcs5Processor {
    @Override
    public void process(Lcs5Context lcs5Context) throws ProcessorException {
        lcs5Context.assertInputNotBeNull();
        List<Integer> items = lcs5Context.getItems();
        lcs5Context.setA(new int[items.size()]);
        int[] A = lcs5Context.getA();
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                A[i] = 1;
                continue;
            }
            if (items.get(i - 1) <= items.get(i)) {
                A[i] = A[i - 1] + 1;
            } else {
                A[i] = 1;
            }
        }
        OptionalInt max = Arrays.stream(A).max();
        if (max.isEmpty()) {
            throw new ProcessorException("Something went wrong!");
        }
        int maxLength = max.getAsInt();
        int maxIndex = getMaxIndex(A, maxLength);

        List<Integer> ret = new ArrayList<>(maxLength);
        markFunction(ret, items, maxIndex, maxLength);
        lcs5Context.setResultAndFinish(ret);
    }

    private void markFunction(List<Integer> ret, List<Integer> items, int maxIndex, int maxLength) {
        for (int i = maxIndex - maxLength + 1; i <= maxIndex; i++) {
            ret.add(items.get(i));
        }
    }

    private int getMaxIndex(int[] A, int maxLength) {
        int maxIndex = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == maxLength) {
                maxIndex = i;
                break;
            }
        }
        return maxIndex;
    }

    @Override
    public String getProcessorName() {
        return "Lcs5 computation";
    }
}
