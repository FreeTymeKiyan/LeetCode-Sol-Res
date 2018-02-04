package com.freetymekiyan.algorithms.other;

/**
 * Given two sorted integer arrays, nums1 and nums2. Find the minimum distances of the numbers in them.
 * <p>
 * E.g.
 * Input:
 * nums1: [1, 4, 7]
 * nums2: [0, 2, 10]
 * Output:
 * 1. Since 2 - 1 is 1. OR 1 - 0 = 1.
 */
public class MinimumDistanceOfTwoSortedArrays {

    /**
     * Brute-force. O(mn) Time.
     * Generate all distances of each numbers pair.
     * Maintain a minimum as result.
     * <p>
     * BUD analysis. Bottleneck? Unnecessary? Duplicate?
     * The unnecessary part is that since the arrays are sorted, the distance will change in a trend that:
     * 1. Decrease to some point
     * 2. Then increase
     * If for each number in one array, we can search for the closest in the other. O(mlog(n)).
     * <p>
     * Can we do better? What can we imply more from the sorted condition?
     * Since we are calculating the difference of two numbers, we may notice that increasing the bigger number only
     * makes the difference bigger.
     * So we increase the smaller number.
     * For each pair, nums1[i] and nums2[j]:
     * If nums1[i] > nums2[j], we know nums1[i+1] >= nums1[i], the distance won't be smaller, so we should move j.
     * If nums1[i] < nums2[j], we know nums2[j+1] >= nums2[j], so we should move i.
     * If nums1[i] = nums2[j], the min distance will be 0.
     * Very similar to merge 2 sorted arrays. O(m + n) Time.
     */
    public int getMinDistance(int[] nums1, int[] nums2) {
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            min = Math.min(min, Math.abs(nums1[i] - nums2[j]));
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                return 0;
            }

        }
        return min;
    }
}