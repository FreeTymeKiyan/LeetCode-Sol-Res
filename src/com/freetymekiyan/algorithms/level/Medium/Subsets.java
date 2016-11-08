package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
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

    private Subsets s;

    /**
     * Backtracking.
     * Visit:
     * Backtrack without current number.
     * Then add the number and backtrack again.
     * Reset.
     * Base case:
     * When the end of array is reached, add de-referenced subset to result and return.
     */
    public List<List<Integer>> subsetsA(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackA(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrackA(List<List<Integer>> res, int[] nums, int pos, List<Integer> subset) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        backtrackA(res, nums, pos + 1, subset); // Without current number.
        subset.add(nums[pos]);
        backtrackA(res, nums, pos + 1, subset); // With current number.
        subset.remove(subset.size() - 1); // Reset.
    }

    /**
     * Backtracking.
     */
    public List<List<Integer>> subsetsB(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsB(res, nums, 0, new ArrayList<>());
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
    public void subsetsB(List<List<Integer>> sets, int[] nums, int pos, List<Integer> subset) {
        sets.add(new ArrayList<>(subset)); // Dereference and add current subset to result.
        for (int i = pos; i < nums.length; i++) {
            subset.add(nums[i]); // With nums[i].
            subsetsB(sets, nums, i + 1, subset); // Backtrack to generate add subsets with s[i].
            subset.remove(subset.size() - 1); // Remove s[i], next round there won't be s[i].
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
    public List<List<Integer>> subsetsC(int[] nums) {
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


    @Before
    public void setUp() {
        s = new Subsets();
    }

    @Test
    public void testExamples() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = s.subsetsB(nums);
        System.out.println(res.toString());
    }

    @After
    public void tearDown() {
        s = null;
    }
}
