package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in
 * their binary representation and return them as an array.
 * <p>
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * <p>
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n)
 * /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other
 * language.
 * Hint:
 * <p>
 * You should make use of what you have produced already.
 * Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
 * Or does the odd/even status of the number help you in calculating the number of 1s?
 * <p>
 * Tags: Dynamic Programming, Bit Manipulation
 * Similar Problems: (E) Number of 1 Bits
 */
public class CountingBits {

    private CountingBits c;

    /**
     * DP.
     * Recurrence Relation: f[i] = f[i / 2] + i % 2.
     * It means that we can decompose the binary string of current integer into two parts:
     * 1) the rightmost bit
     * 2) from the second rightmost bit to the leftmost bit
     * For example: if i = 5, the binary string is 101, "10" + "1"
     * So the number of bits relates to the # of bits of i / 2, and whether it's odd or even.
     * If it's even, it has the same number of bits as "10", which is 1.
     * If it's odd, it has one more, which is 2.
     */
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i / 2] + i % 2;
        }
        return res;
    }

    @Before
    public void setUp() {
        c = new CountingBits();
    }

    @Test
    public void testExamples() {
        Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, c.countBits(5));
    }

    @After
    public void tearDown() {
        c = null;
    }

}
