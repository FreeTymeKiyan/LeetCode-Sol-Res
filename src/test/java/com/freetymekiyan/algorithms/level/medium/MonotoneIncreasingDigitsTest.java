package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.testng.annotations.Test;

public class MonotoneIncreasingDigitsTest {

    @Test
    public void testMonotoneIncreasingDigitsExamples() {
        int N = 10;
        int output = 9;
        MonotoneIncreasingDigits m = new MonotoneIncreasingDigits();
        Assert.assertEquals(m.monotoneIncreasingDigits(N), output);
        N = 1234;
        output = 1234;
        Assert.assertEquals(m.monotoneIncreasingDigits(N), output);
        N = 332;
        output = 299;
        Assert.assertEquals(m.monotoneIncreasingDigits(N), output);
    }
}