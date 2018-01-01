package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * <p>
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
     * 1) Make sure it's the start of a sequence by check i - 1.
     * 2) If it's the start, check i + 1, i + 2 ... till reach j where it's not in the set.
     * Update max length by j - i.
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int max = 0;
        for (int n : set) {
            if (set.contains(n - 1)) { // Not a start.
                continue;
            }
            int e = n + 1;
            while (set.contains(e)) { // Find the end.
                e++;
            }
            max = e - n > max ? e - n : max;
        }
        return max;
    }

    /**
     * Union Find. O(n) Time.
     * View consecutive sequence as connected component.
     * Use a map to store value to connected component id mapping.
     * For each number n in nums:
     * | If already in map, skip.
     * | Put number and index in map.
     * | If nums[i]+1 is also in map, union it with i.
     * | If nums[i]-1 is also in map, union it with i.
     * Go through the ids in union find to get the max count.
     */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] ids = new int[nums.length];
        for (int i = 0; i < ids.length; i++) ids[i] = i;

        Map<Integer, Integer> valueToIds = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // Value already in map is already connected to some CC.
            // If we union it again, it would be duplicate.
            if (valueToIds.containsKey(nums[i])) continue;

            valueToIds.put(nums[i], i);
            if (valueToIds.containsKey(nums[i] + 1)) {
                union(ids, i, valueToIds.get(nums[i] + 1));
            }
            if (valueToIds.containsKey(nums[i] - 1)) {
                union(ids, i, valueToIds.get(nums[i] - 1));
            }
        }
        int max = 0;
        int[] count = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            int root = find(ids, ids[i]);
            count[root]++;
            if (count[root] > max) max = count[root];
        }
        return max;
    }

    private void union(int[] ids, int p, int q) {
        int r1 = find(ids, p);
        int r2 = find(ids, q);
        if (r1 != r2) {
            ids[r1] = r2;
        }
    }

    private int find(int[] ids, int i) {
        while (i != ids[i]) {
            ids[i] = ids[ids[i]];
            i = ids[i];
        }
        return i;
    }
}