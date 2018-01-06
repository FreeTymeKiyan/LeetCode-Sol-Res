package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 90. Subsets II
 * <p>
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * For example,
 * If nums = [1,2,2], a solution is:
 * <p>
 * | [
 * |   [2],
 * |   [1],
 * |   [1,2,2],
 * |   [2,2],
 * |   [1,2],
 * |   []
 * | ]
 * Company Tags: Facebook
 * Tags: Array, Backtracking
 */
public class Subsets2 {

    /**
     * Backtracking.
     * Sort the array first for skipping duplicates later.
     */
    public List<List<Integer>> subsetsWithDup(int[] num) {
        if (null == num || num.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(num); // Sort first.
        backtrack(subsets, num, 0, new ArrayList<>());
        return subsets;
    }

    /**
     * Backtracking. DFS.
     * Traverse a each node of a subset tree.
     * For each number n in the nums array:
     * | If n is a duplicate of previous number, skip.
     * | Pick it and backtrack.
     * | Remove it.
     */
    private void backtrack(List<List<Integer>> subsets, int[] nums, int pos, List<Integer> set) {
        subsets.add(new ArrayList<>(set)); // Dereference.
        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[i] == nums[i - 1]) { // Check and skip duplicates.
                continue;
            }
            set.add(nums[i]);
            backtrack(subsets, nums, i + 1, set); // Backtrack the rest of the numbers, so i -> i + 1.
            set.remove(set.size() - 1);
        }
    }

    /**
     * Backtracking.
     * Duplicate with previous number will only be added if:
     * The previous number is already in subset.
     */
    private void backtrack2(List<List<Integer>> res, int[] nums, int pos, List<Integer> subset) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(subset)); // Dereference.
            return;
        }
        subset.add(nums[pos]); // Add.
        backtrack2(res, nums, pos + 1, subset); // Backtrack.
        subset.remove(subset.size() - 1); // Reset.
        if (pos > 0 && nums[pos] == nums[pos - 1] && !subset.isEmpty() && nums[pos - 1] == subset
                .get(subset.size() - 1)) {
            return;
        }
        backtrack2(res, nums, pos + 1, subset);
    }

    /**
     * DP. Bottom-up.
     * Build subsets level by level from empty set.
     * For each number n in nums:
     * | If n is not a duplicate:
     * |   Insert n to each previous subsets and create new subsets.
     * | If n is a duplicate:
     * |   Only need to insert n to the subsets that contains a previous duplicate.
     * E.g. [1 2 2]
     * [] => [], [1] => [], [1], [2], [1 2] => [], [1], [2], [1 2], [2 2], [1 2 2]
     * Add 2 to subsets which have 2, which is the latter half of result.
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // Empty set.
        if (null == nums || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums); // Sort first.

        int j, prevSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { // Duplicate.
                j = prevSize; // # of previous sets before last number.
            } else {
                j = 0; // No dup, start from beginning.
            }
            prevSize = res.size(); // # of previous sets.
            // Add to previous sets with same num
            for (; j < prevSize; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }
}