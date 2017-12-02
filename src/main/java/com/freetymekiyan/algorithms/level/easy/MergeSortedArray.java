package com.freetymekiyan.algorithms.level.easy;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from
 * nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 * <p>
 * Company Tags: Microsoft, Bloomberg, Facebook
 * Tags: Array, Two Pointers
 * Similar Problems: (E) Merge Two Sorted Lists
 */
public class MergeSortedArray {

    /**
     * Array. Two Pointers.
     * One pointer i at the end of nums1. Another pointer j at the end of nums2.
     * Compare their values and put the larger one at the end of nums1.
     * The index is m+n+1.
     * If m == 0, nums1 is fully merged, merge the rest of nums2.
     */
    private void merge(int nums1[], int m, int nums2[], int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0 && j >= 0; k--) {
            nums1[k] = (i < 0 || nums1[i] < nums2[j]) ? nums2[j--] : nums1[i--];
        }
    }
}
