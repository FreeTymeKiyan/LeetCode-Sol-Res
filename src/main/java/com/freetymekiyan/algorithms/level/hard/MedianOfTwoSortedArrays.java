package com.freetymekiyan.algorithms.level.hard;

/**
 * 4. Median of Two Sorted Arrays
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 * <p>
 * Tags: Binary Search, Array, Divide and Conquer
 * Companies: Facebook
 */
public class MedianOfTwoSortedArrays {

    /**
     * Binary Search.
     * First figure out what we are to search for.
     * There 2 cut points, 1 for each array, i for nums1, j for nums2.
     * Then we have 4 smaller arrays: nums1[0 ~ i - 1], nums2[0 ~ j - 1], nums1[i, m - 1], nums2[j, n - 1]
     * If we name them A, B, C, D, respectively, then the desired values for i and j should make:
     * 1) len(A) + len(B) = len(C) + len(D) => i + j = m + n - i - j or m + n + 1 - i - j, even/odd
     * 2) nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
     * <p>
     * If the # of elements is odd, the median is max(A, B)
     * If even, the median is (max(A, B) + min(C, D)) / 2
     * Note the i and j can be out of array's ranges. Be careful.
     * <p>
     * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/2
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 && n == 0) return 0.0;
        if (m > n) return findMedianSortedArrays(nums2, nums1); // Keep shorter array first. Note the return.

        int i = 0;
        int j = 0;
        int iMin = 0; // i's range is [0, m].
        int iMax = m;
        int mid = (m + n + 1) / 2; // When m+n is odd, there median will be the maximum of left half.
        while (iMin <= iMax) {
            i = (iMin + iMax) / 2;
            j = mid - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
                // i is too small.
                iMin = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                // i is too big.
                iMax = i - 1;
            } else {
                break;
            }
        }

        int leftMax = 0;
        if (i == 0) {
            leftMax = nums2[j - 1];
        } else if (j == 0) {
            leftMax = nums1[i - 1];
        } else {
            leftMax = Math.max(nums1[i - 1], nums2[j - 1]);
        }
        if ((m + n) % 2 == 1) { // Total # of elements is odd.
            return leftMax;
        }
        int rightMin = 0; // Total # of elements is even.
        if (i == m) {
            rightMin = nums2[j];
        } else if (j == n) {
            rightMin = nums1[i];
        } else {
            rightMin = Math.min(nums1[i], nums2[j]);
        }
        return (leftMax + rightMin) / 2.0; // Return double.
    }
}