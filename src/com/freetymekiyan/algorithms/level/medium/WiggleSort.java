package com.freetymekiyan.algorithms.level.medium;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 * <p>
 * Company Tags: Google
 * Tags: Array, Sort
 * Similar Problems: (M) Sort Colors, (M) Wiggle Sort II
 */
public class WiggleSort {

    /**
     * The final sorted nums needs to satisfy two conditions:
     * <p>
     * If i is odd, then nums[i] >= nums[i - 1];
     * If i is even, then nums[i] <= nums[i - 1].
     * <p>
     * Proof: suppose if nums[0...i-1] is already wiggle.
     * If i is odd, then nums[i - 2] >= nums[i - 1], nums[i - 1] should <= nums[i]
     * So if nums[i - 1] > nums[i], swap them. And because nums[i - 2] >= nums[i - 1], nums[i - 2] > nums[i].
     * Then nums[i - 2] > nums[i] < nums[i - 1]
     */
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) > 0) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i);
                }
            } else if (i != 0 && nums[i - 1] < nums[i]) {
                swap(nums, i);
            }
        }
    }

    private void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = temp;
    }

    /**
     * Same idea, just a more concise version.
     */
    public void wiggleSortB(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i - 1];
            /*
             * When i is odd, left is true, and if nums[i - 1] > nums[i], we need to swap
             * When i is even, left is false, and if nums[i - 1] < nums[i], we need to swap
             */
            if ((i % 2 == 1) == (a > nums[i])) {
                nums[i - 1] = nums[i];
                nums[i] = a;
            }
        }
    }

}
