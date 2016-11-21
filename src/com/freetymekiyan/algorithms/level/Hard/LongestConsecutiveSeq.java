package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
     * Set. O(n^2) Time worst case.
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

    /**
     * Union Find. O(n) Time.
     * Take consecutive sequence as connected component.
     * Use a map to store value to index mapping.
     * For each number in nums:
     * | If already in map, skip.
     * | Put number and index in map.
     * | If nums[i]+1 is also in map, union it with i.
     * | If nums[i]-1 is also in map, union it with i.
     * Go through the ids in union find to get the max count.
     */
    public int longestConsecutiveB(int[] nums) {
        UF uf = new UF(nums.length);
        Map<Integer, Integer> locs = new HashMap<>(); // Get index with value.
        for (int i = 0; i < nums.length; i++) {
            if (locs.containsKey(nums[i])) {
                continue;
            }
            locs.put(nums[i], i);
            if (locs.containsKey(nums[i] + 1)) {
                uf.union(i, locs.get(nums[i] + 1)); // Use index as cc id.
            }
            if (locs.containsKey(nums[i] - 1)) {
                uf.union(i, locs.get(nums[i] - 1));
            }
        }
        return uf.maxUnion();
    }

    @Test
    public void testExamples() {
        int[] a = {100, 4, 200, 1, 3, 2};
        Assert.assertEquals(4, longestConsecutive(a));
    }

    private class UF {

        private int[] ids;

        public UF(int n) {
            ids = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }
        }

        private int find(int i) {
            while (i != ids[i]) {
                ids[i] = ids[ids[i]];
                i = ids[i];
            }
            return i;
        }

        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return;
            }
            ids[i] = j;
        }

        /**
         * Returns the maximum size of a connected component.
         * O(n) Time. O(n) Space.
         */
        public int maxUnion() {
            int[] count = new int[ids.length]; // # of nodes for each cc.
            int max = 0;
            for (int i = 0; i < ids.length; i++) {
                count[find(i)]++;
                max = Math.max(max, count[find(i)]);
            }
            return max;
        }
    }
}
