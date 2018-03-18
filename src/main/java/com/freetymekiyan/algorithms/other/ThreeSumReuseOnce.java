package com.freetymekiyan.algorithms.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers and a target integer, are there elements a, b, c in S such that a + b + c = target?
 * Find all unique triplets in the array which gives the sum of target.
 * <p>
 * Note: The solution set must not contain duplicate triplets. One of the numbers can be reused once.
 * <p>
 * Example:
 * [3,2,1,4,6,7], target = 10
 * [2,2,6],[3,3,4],[2,4,4] are valid result sets.
 */
public class ThreeSumReuseOnce {

    /**
     * What changed to the original solution if one of the numbers can reused once?
     * 1. When i is set, j can start from i instead of i + 1.
     * 2. During two sum, j and k can be the same.
     * 3. i and j and k cannot all be the same.
     */
    public List<List<Integer>> threeSumReuseOnce(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Input array must exist and have at least 3 numbers.");
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > target) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i;
            if (nums[i] + nums[j] > target) {
                break;
            }
            int k = nums.length - 1;
            while (j <= k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    if (i != j || j != k) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                    while (j <= k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    while (j <= k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (sum > target) {
                    while (j <= k && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    k--;
                } else {
                    while (j <= k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    j++;
                }
            }
        }
        return res;
    }
}