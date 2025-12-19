package com.apps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class calcTest {

    @Test
    void test() {
        //fail("Not yet implemented");
        calc calculator = new calc();
        int result = calculator.integerDivision(4,2);
        assertEquals(3,result, () -> "4" + "/" + "2" + "did not produce " + 2);
    }

}
