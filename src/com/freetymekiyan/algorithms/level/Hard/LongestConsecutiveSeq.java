package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Company Tags: Google, Facebook
 * Tags: Array, Union Find
 * Similar Problems: (M) Binary Tree Longest Consecutive Sequence
 */
public class LongestConsecutiveSeq {

    /**
     * Set.
     * Add numbers into a set first.
     * Then for each number i:
     * 1) Make sure it's the start of a streak by check i - 1.
     * 2) If it's the start, check i + 1, i + 2 ... till reach j where it's not in the set.
     * Update max length by j - i.
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int max = 1;
        for (int i : set) {
            if (set.contains(i - 1)) {
                continue;
            }
            int j = i + 1;
            while (set.contains(j)) {
                j++;
            }
            max = Math.max(j - i, max);
        }
        return max;
    }

    @Test
    public void testExamples() {
        int[] a = {100, 4, 200, 1, 3, 2};
        Assert.assertEquals(4, longestConsecutive(a));
    }
}
