package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RomanToIntegerTest {
    private static final String[] ROMANS = {"DCXXI", "DCXXIV", "I"};
    private static final int[] INTEGERS = {621, 624, 1};

    @Test
    public void testRomanToInt() {
        RomanToInteger r = new RomanToInteger();
        for (int i = 0; i < ROMANS.length; i++) {
            String s = ROMANS[i];
            Assert.assertEquals(r.romanToInt(s), INTEGERS[i]);
        }
    }
}