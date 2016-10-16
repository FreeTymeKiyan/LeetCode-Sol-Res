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
     * Base case:
     * When start reaches the end of array, add set to result and return.
     * Visit:
     * Make a copy of current set. Recurse without adding current number.
     * Add current number to the copy. Recurse with copy.
     */
    public List<List<Integer>> subsetsA(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsA(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void subsetsA(int[] nums, int start, List<Integer> subset, List<List<Integer>> res) {
        if (start == nums.length) {
            res.add(subset);
            return;
        }
        List<Integer> copy = new ArrayList<>(subset);
        subsetsA(nums, start + 1, subset, res);
        copy.add(nums[start]);
        subsetsA(nums, start + 1, copy, res);
    }

    /**
     * Backtracking.
     * Recurrence relation:
     * The all subsets consist of two parts for each number in the original set:
     * 1) All the subsets with current number.
     * 2) All the subsets without current number.
     */
    public List<List<Integer>> subsetsB(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsB(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void subsetsB(int[] s, int start, List<Integer> subset, List<List<Integer>> sets) {
        sets.add(new ArrayList<>(subset)); // Dereference
        for (int i = start; i < s.length; i++) {
            subset.add(s[i]); // With s[i]
            subsetsB(s, i + 1, subset, sets); // Backtrack to generate add subsets with s[i]
            subset.remove(subset.size() - 1); // Remove s[i], next round there won't be s[i]
        }
    }

    /**
     * Iterative.
     * Build from empty set to the next subsets.
     * By add each subset the current num, new subsets are generated.
     * Then add new subsets to all subsets and generate next round.
     * Stop when we iterate through the array.
     * <p>
     * [] -> [] [1]
     * [] [1] -> [] [1] [2] [1, 2]
     */
    public List<List<Integer>> subsetsC(int[] nums) {
        List<List<Integer>> subset = new ArrayList<>();
        subset.add(new ArrayList<>()); // Empty set
        for (int i = 0; i < nums.length; i++) {
            int n = subset.size();
            for (int j = 0; j < n; j++) {
                List<Integer> set = new ArrayList<>(subset.get(j)); // Dereference
                set.add(nums[i]);
                subset.add(set);
            }
        }
        return subset;
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
