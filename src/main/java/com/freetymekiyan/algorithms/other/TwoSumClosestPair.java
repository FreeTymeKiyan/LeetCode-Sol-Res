package com.freetymekiyan.algorithms.other;

import java.util.Arrays;

/**
 * Given an integer array, find a pair from it that their sum is closest to 0.
 * <p>
 * Created by kiyan on 6/3/16.
 */
public class TwoSumClosestPair {

    public int[] closest(int[] nums) {
        int closest = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (Math.abs(sum) < closest) {
                    closest = sum;
                    res[0] = nums[i];
                    res[1] = nums[j];
                }
            }
        }
        return res;
    }

    public int[] closestB(int[] nums) {
        if (nums == null || nums.length <= 1) return null;
        if (nums.length == 2) return new int[]{nums[0], nums[1]};

        Arrays.sort(nums);
        int[] res = new int[2];
        int i = 0;
        int j = nums.length - 1;
        int closest = Integer.MAX_VALUE;
        while (i < j) {
            int sum = nums[i] + nums[j];

            if (Math.abs(sum) < closest) {
                closest = sum;
                res[0] = nums[i];
                res[1] = nums[j];
            }

            if (sum > 0) j--;
            else if (sum < 0) i++;
            else return res;
        }
        return res;
    }
}
