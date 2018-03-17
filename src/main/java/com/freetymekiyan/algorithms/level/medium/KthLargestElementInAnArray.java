package com.freetymekiyan.algorithms.level.medium;

import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * <p>
 * Company Tags: Facebook, Amazon, Microsoft, Apple, Bloomberg, Pocket Gems
 * Tags: Heap, Divide and Conquer
 * Similar Problems: (M) Wiggle Sort II, (M) Top K Frequent Elements, (E) Third Maximum Number
 */
public class KthLargestElementInAnArray {

    /**
     * Heap. Priority queue. O(nlogk) Time, O(k) Space.
     * Create a min heap as a window.
     * For each number in the array, add it to the heap.
     * If heap size > k, poll from the heap.
     * Return the top of the heap at the end.
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * QuickSelect. Binary Search. Partition. O(nlogn) Time.
     * Use partition algorithm in Quick Sort, which gives us the pivot's index.
     * With that, we can then know which part of the array the kth largest element is in.
     * Each round get the rth largest ranking returned by the partition algorithm.
     * If r > k - 1, ranking is too large, the pivot is too small, should search in [lo, r-1].
     * If r < k - 1, ranking is too large, the pivot is too large, should search in [r+1, high].
     * If r = k - 1, the kth largest number is found.
     */
    public int findKthLargest2(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) { // Here, lo must be smaller than hi as partition's input.
            int r = partition(nums, lo, hi);
            if (r < k - 1) { // k - 1, not k, because the ranking is 0-based.
                lo = r + 1;
            } else if (r > k - 1) {
                hi = r - 1;
            } else {
                break;
            }
        }
        return nums[k - 1];
    }

    /**
     * Partition algorithm in quick sort. O(n) Time.
     * Find ranking: how large is a[hi] in the array.
     * Given an array and the range to be partitioned.
     * Initialize the last element as the pivot.
     * Initialize two pointers, i from lower bound, j from higher bound - 1.
     * Move i to the next number <= a[hi], which will be thrown to the end of the array.
     * Move j to the next number > a[hi], which should be in the front of the array.
     * Check whether i and j overlap.
     * If doesn't, swap a[i] and a[j].
     * If overlap, break, and swap a[i] and a[hi] to move pivot to the position it belongs.
     * i is the final position since all elements before i are > a[hi].
     */
    private int partition(int[] a, int lo, int hi) {
        /*
         * Last element as pivot makes sure that when we swap with i, i is either:
         * 1. the pivot
         * 2. the element that's <= pivot, which guarantees all elements before i are > pivot.
         * The last swap will always be correct.
         */
        int pivot = a[hi];
        int i = lo;
        int j = hi - 1;
        while (true) {
            while (i < hi && a[i] > pivot) {
                i++;
            }
            while (j >= lo && a[j] <= pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, hi, i);
        return i;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * Try using start as pivot. What changes need to be done?
     * mid now returns the ranking, not the index.
     * So it can directly compare with k instead of k-1.
     * But when we return we must convert mid to the correct index.
     * Since mid also means how many numbers are from pivot to the end, index = nums.length - mid.
     */
    public int findKthLargest3(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = partition2(nums, lo, hi);
            if (mid == k) {
                break;
            } else if (mid > k) {
                lo = nums.length - mid + 1;
            } else {
                hi = nums.length - mid - 1;
            }
        }
        return nums[nums.length - k];
    }

    /**
     * Using start as pivot, not end.
     * Swap with the pointer moving backward, j.
     * So j's right should only have numbers > pivot.
     */
    private int partition2(int[] nums, int s, int e) {
        int p = nums[s];
        int i = s + 1;
        int j = e;
        while (true) {
            while (i < e && nums[i] <= p) i++;
            while (j > s && nums[j] > p) j--;
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, s, j);
        return nums.length - j;
    }
}