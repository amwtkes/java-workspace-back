package com.xiaojin.algorithm.LCS;

import com.xiaojin.algorithm.LCS.base.Lcs1Processor;
import com.xiaojin.algorithm.LCS.base.Lcs2Processor;
import com.xiaojin.algorithm.LCS.processor3.Lcs3Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LcsService {
    private final List<Lcs1Processor> lcs1Processors;
    private final List<Lcs2Processor> lcs2Processors;
    private final List<Lcs3Processor> lcs3Processors;
}
