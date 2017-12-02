package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * <p>
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * <p>
 * Tags: Sort
 * Similar Problems: (M) Sort Colors, (M) Kth Largest Element in an Array, (M) Wiggle Sort
 */
public class WiggleSort2 {

    private WiggleSort2 w;

    /**
     * Quick select + Three-way partition.
     * Find the median with quick select.
     * Then put smaller ones on even indices.
     * Put larger ones on odd indices.
     * https://discuss.leetcode.com/topic/41464/step-by-step-explanation-of-index-mapping-in-java/11
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = quickSelect((n + 1) / 2, nums);
        int left = 0;
        int i = 0;
        int right = n - 1;
        while (i <= right) {
            if (nums[newIndex(i, n)] > median) {
                swap(nums, newIndex(left, n), newIndex(i, n));
                left++;
                i++;
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right, n), newIndex(i, n));
                right--;
            } else {
                i++;
            }
        }
    }

    /**
     * Create an index mapping.
     * [0, 1, 2, 3, 4, 5] -> [1, 3, 5, 0, 2, 4]
     * [0, 1, 2, 3, 4, 5, 6] -> [1, 3, 5, 0, 2, 4, 6]
     */
    private int newIndex(int i, int n) {
        return (2 * i + 1) % (n | 1);
    }

    /**
     * Return the ranking position of k in nums.
     */
    private int quickSelect(int k, int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int pos = l + (r - l) / 2;
            pos = partition(nums, l, r, pos);
            if (pos == k) {
                return nums[pos];
            } else if (pos < k) {
                l = pos + 1;
            } else {
                r = pos - 1;
            }
        }
        return nums[l];
    }

    private int partition(int[] nums, int l, int r, int pivot) {
        int val = nums[pivot];
        swap(nums, pivot, r); // Move pivot to the end
        int storeIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[i] < val) { // Move numbers smaller than pivot to the front
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, r);
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Before
    public void setUp() {
        w = new WiggleSort2();
    }

    @Test
    public void testExamples() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(w.quickSelect((nums.length) / 2, nums));
    }

    @After
    public void tearDown() {
        w = null;
    }

}
