package com.freetymekiyan.algorithms.level.hard;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1], return 6.
 * <p>
 * Tags: Array, Stack, Two pointers
 */
class TrappingRainWater {
    public static void main(String[] args) {

    }

    /**
     * Calculate the area of all and blocks
     * Then return all - block to get the trapped water
     * <p>
     * Start from two sides
     * If min of A[l] and A[r] is larger than current level
     * Update all and curLevel
     * Then move lower pointer towards center
     */
    public int trap(int[] A) {
        if (A == null || A.length == 0) return 0;
        int l = 0;
        int r = A.length - 1;
        int level = 0;
        int all = 0;
        int block = 0;

        while (l <= r) { // note l <= r to include the last block
            // update area
            int curLevel = Math.min(A[l], A[r]);
            if (curLevel > level) {
                all += (curLevel - level) * (r - l + 1);
                level = curLevel;
            }
            // move index and update block
            if (A[l] < A[r]) block += A[l++];
            else block += A[r--];
        }
        return all - block;
    }

    public int trap2(int[] height) {
        int n = height.length;

        if (n < 3) return 0;

        // scan from both left and right
        int left = 0, right = n - 1, highest = 0, water = 0;
        while (left < right) {
            int curr = height[left] < height[right] ? height[left++] : height[right--];
            if (curr < highest) {
                // add trapped water
                water += highest - curr;
            } else {
                highest = curr; // update highest level
            }
        }

        return water;
    }
}