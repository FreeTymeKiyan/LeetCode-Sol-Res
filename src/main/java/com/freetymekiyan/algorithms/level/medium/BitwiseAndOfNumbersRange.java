package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
 * inclusive.
 * <p>
 * For example, given the range [5, 7], you should return 4.
 * <p>
 * Tags: Bit Manipulation
 */
public class BitwiseAndOfNumbersRange {

    private BitwiseAndOfNumbersRange b;

    /**
     * The AND result of the last bit of odd and even numbers should be 0.
     * So if m and n are not equal, check the last bit of them.
     * Count the number of iterations.
     * When m and n are equal, it means there are no more odd and even pairs within the range.
     * m has been divided by 2 for count times, so the result it m * count.
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int moveFactor = 1;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }

    @Before
    public void setUp() {
        b = new BitwiseAndOfNumbersRange();
    }

    @Test
    public void testEdgeCases() {
        Assert.assertEquals(0, b.rangeBitwiseAnd(0, 0));
        Assert.assertEquals(0, b.rangeBitwiseAnd(0, 1));
        Assert.assertEquals(0, b.rangeBitwiseAnd(0, Integer.MAX_VALUE));
        Assert.assertEquals(Integer.MAX_VALUE, b.rangeBitwiseAnd(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testExamples() {
        Assert.assertEquals(0, b.rangeBitwiseAnd(1, 7));
        Assert.assertEquals(4, b.rangeBitwiseAnd(5, 7));
        Assert.assertEquals(6, b.rangeBitwiseAnd(6, 7));
    }

    @After
    public void tearDown() {
        b = null;
    }

}
