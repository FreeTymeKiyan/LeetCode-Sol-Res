package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
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
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(num); // Sort first
        backtrack(res, new ArrayList<>(), num, 0);
        return res;
    }

    /**
     * Backtrack the array.
     * Each call will generate a subset, dereference and add to the final result.
     * Then for the rest of the numbers, backtrack each of them one by one.
     * Remember to check duplicates by comparing current number and previous number.
     * After the first position.
     */
    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] num, int pos) {
        res.add(new ArrayList<>(list)); // Dereference.
        for (int i = pos; i < num.length; i++) {
            if (i != pos && num[i] == num[i - 1]) { // Check and skip duplicates
                continue;
            }
            list.add(num[i]); // Add.
            backtrack(res, list, num, i + 1); // Backtrack down.
            list.remove(list.size() - 1); // Reset.
        }
    }

    /**
     * Build subsets level by level from empty set.
     * If a number from S is the first one of the numbers with the same value,
     * it can be inserted to each previous subset and create new non-duplicate subsets.
     * If a number from S is a duplicated number of some value,
     * It only needs to extend subsets which contains its predecessor.
     * <p>
     * [1 2 2]
     * [ ], [1], [2], [1 2]
     * [1 2 2], [2 2] (add 2 to subsets which have 2)
     */
    public List<List<Integer>> subsetsWithDup2(int[] num) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // Empty set
        if (null == num || num.length == 0) {
            return res;
        }
        Arrays.sort(num); // Sort first

        int j, prevSize = 0;
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) // Dup number
            {
                j = prevSize; // # of previous sets before last number
            } else {
                j = 0;
            }
            prevSize = res.size(); // # of previous sets
            /* Add to previous sets with same num */
            for (; j < prevSize; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(num[i]);
                res.add(temp);
            }
        }
        return res;
    }
}