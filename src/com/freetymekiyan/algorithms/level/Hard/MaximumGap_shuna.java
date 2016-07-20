package com.freetymekiyan.algorithms.level.hard;

import java.util.Arrays;

/**
 * 164. Maximum Gap
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Tags: Sort
 * <p>
 * Analysis:
 * The key point is linear time/space: Bucket Sort
 *
 * @author chenshuna
 */

class MaximumGap_Shuna {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int length = nums.length;
        int maxGap = 0;
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        if (min == max) {
            return 0;
        }
        int bucketSize = (max - min) / (length - 1);
        if (bucketSize == 0) {
            bucketSize = 1;
        }
        int bucketCount = (max - min) / bucketSize + 1;
        int[] left = new int[bucketCount];
        int[] right = new int[bucketCount];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        for (int i = 0; i < length; i++) {
            int loc = (nums[i] - min) / bucketSize;
            if (left[loc] == -1) {
                left[loc] = nums[i];
                right[loc] = nums[i];
            } else {
                left[loc] = Math.min(left[loc], nums[i]);
                right[loc] = Math.max(right[loc], nums[i]);
            }
        }
        int locLeft;
        int locRight = -1;
        for (int i = 0; i < bucketCount; i++) {
            if (left[i] != -1 && locRight == -1) {
                locRight = right[i];
            } else if (left[i] != -1 && locRight != -1) {
                locLeft = left[i];
                maxGap = Math.max(maxGap, locLeft - locRight);
                locRight = right[i];
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        System.out.print(maximumGap(new int[]{1, 6, 7, 3, 4, 19}));
    }
}