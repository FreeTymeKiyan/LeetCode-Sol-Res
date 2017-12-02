package com.freetymekiyan.algorithms.level.hard;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least
 * one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Note: You must not modify the array (assume the array is read only). You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2). There is only one duplicate number in the array, but it could be
 * repeated more than once.
 *
 * Tags: Array, Two Pointers, Binary Search
 * Similar Problems: (H) First Missing Positive, (M) Single Number, (M) Linked List Cycle II, (M) Missing Number
 */
public class FindDupNum {

    /**
     * Floyd's loop detection
     * Two pointers, O(n)
     */
    public int findDuplicate1(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        int find = 0;
        while (slow != find) {
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }

    /**
     * Binary Search, O(nlogn)
     * count: how many numbers are below the half value
     * if count > mid, dup in lower range
     * else dup in upper range
     */
    public int findDuplicate2(int[] nums) {
        int n = nums.length - 1;
        int l = 0, r = n;
        while (l < r) {
            int m = l + (r - l) / 2;
            int count = countBelow(nums, m);
            if (count > m) r = m;
            else l = m + 1;
        }
        return l;
    }

    public int countBelow(int[] nums, int target){
        int res = 0;
        for (int num : nums)
            if (num <= target) res++;
        return res;
    }
}
