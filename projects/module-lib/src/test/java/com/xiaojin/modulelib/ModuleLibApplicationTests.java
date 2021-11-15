package com.xiaojin.modulelib;

import com.xiaojin.lib.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ModuleLibApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testA() {
        A.Print("");
    }

    @Test
    void testTransferString() {
        assertThat("Transform problem!", A.transformString("ut"), is("UT"));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> A.transformString(null));
        Assertions.assertEquals("args is should not be null or empty!", illegalArgumentException.getMessage());
    }
}
