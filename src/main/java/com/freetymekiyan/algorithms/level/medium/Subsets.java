package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets.
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * For example,
 * If nums = [1,2,3], a solution is:
 * <p>
 * | [
 * |   [3],
 * |   [1],
 * |   [2],
 * |   [1,2,3],
 * |   [1,3],
 * |   [2,3],
 * |   [1,2],
 * |   []
 * | ]
 * Company Tags: Amazon, Uber, Facebook
 * Tags: Array, Backtracking, Bit Manipulation
 * Similar Problems: (M) Generalized Abbreviation
 */
public class Subsets {

    /**
     * Backtracking.
     * Visit:
     * Backtrack without current number.
     * Then add the number and backtrack again.
     * Reset.
     * Base case:
     * When the end of array is reached, add de-referenced subset to result and return.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, int pos, List<Integer> subset) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        backtrack(res, nums, pos + 1, subset); // Without current number.
        subset.add(nums[pos]);
        backtrack(res, nums, pos + 1, subset); // With current number.
        subset.remove(subset.size() - 1); // Reset.
    }

    /**
     * Backtracking.
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsets2(res, nums, 0, new ArrayList<>());
        return res;
    }

    /**
     * DFS.
     * Add current subset to result first.
     * Put current number in subset.
     * Then backtrack with the rest of the numbers.
     * Reset by remove last number in subset.
     * Next iteration will move to next number then all subsets will not have this number.
     */
    public void subsets2(List<List<Integer>> subsets, int[] nums, int pos, List<Integer> set) {
        subsets.add(new ArrayList<>(set)); // Dereference and add current subset to result.
        for (int i = pos; i < nums.length; i++) {
            set.add(nums[i]);
            subsets2(subsets, nums, i + 1, set); // Backtrack to generate add subsets with s[i].
            set.remove(set.size() - 1); // Remove s[i], next round there won't be s[i].
        }
    }

    /**
     * Iterative.
     * Build from empty set to the next subsets.
     * Adding each subset the current num, new subsets are generated.
     * Then add new subsets to all subsets and generate next round.
     * Stop when we iterate through the array.
     * E.g.:
     * [] -> [] [1]
     * [] [1] -> [] [1] [2] [1, 2]
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>()); // Add empty set.
        for (int i = 0; i < nums.length; i++) {
            int n = subsets.size();
            for (int j = 0; j < n; j++) {
                List<Integer> set = new ArrayList<>(subsets.get(j)); // Dereference.
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }
}