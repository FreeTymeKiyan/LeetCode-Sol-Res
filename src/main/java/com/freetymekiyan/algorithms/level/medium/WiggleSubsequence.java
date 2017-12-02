package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence
 * with fewer than two elements is trivially a wiggle sequence.
 * <p>
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and
 * negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two
 * differences are positive and the second because its last difference is zero.
 * <p>
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence
 * is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the
 * remaining elements in their original order.
 * <p>
 * Examples:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 * <p>
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length. One is [1,17,5,10,13,10,16,8].
 * <p>
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * Follow up:
 * Can you do it in O(n) time?
 * <p>
 * Tags: Dynamic Programming, Greedy
 */
public class WiggleSubsequence {

    private WiggleSubsequence w;

    /**
     * DP, Greedy.
     * Use two vars to record the count of increasing-first and decreasing-first sequences.
     * Then loop through the array from second number.
     * If current number is larger than previous one, it means we can extend our last decreasing sequence length by 1.
     * So inc = dec + 1.
     * Otherwise, dec = inc + 1.
     * Return the max of these two counts.
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int inc = 1;
        int dec = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc = dec + 1;
            } else if (nums[i] < nums[i - 1]) {
                dec = inc + 1;
            }
        }
        return Math.max(inc, dec);
    }


    @Before
    public void setUp() {
        w = new WiggleSubsequence();
    }

    @Test
    public void testExamples() {
        int[] nums = {1, 7, 4, 9, 2, 5};
        Assert.assertEquals(6, w.wiggleMaxLength(nums));
        nums = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        Assert.assertEquals(7, w.wiggleMaxLength(nums));
        nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertEquals(2, w.wiggleMaxLength(nums));
    }

    @After
    public void tearDown() {
        w = null;
    }

}
