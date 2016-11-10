package com.freetymekiyan.algorithms.level.medium;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * <p>
 * Credits:
 * Special thanks to @mithmatt for adding this problem and creating all test cases.
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
     * QuickSelect. Binary Search. Partition.
     * Use partition algorithm in Quick Sort.
     * Binary search the given array.
     * Each round get the ranking r returned from partition algorithm.
     * If r > k - 1, go to the left.
     * If r < k - 1, go to the right.
     * If r = k - 1, kth largest number found.
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
     * Partition algorithm in quick sort. O(n).
     * Find ranking: how large is a[hi] in the array.
     * Given an array and the range to be partitioned.
     * Initialize the last element as the pivot.
     * Initialize two pointers, i from lower bound, j from higher bound - 1.
     * Move i to the next number <= a[hi], which will be thrown to the end of the array.
     * Move j to the next number > a[hi], which should be at the front of the array.
     * Check whether i and j overlap.
     * If doesn't, swap a[i] and a[j].
     * If overlap, break, and swap a[i] and a[hi] to move pivot to the position it belongs.
     * i is the final position since all elements before i are > a[hi].
     */
    private int partition(int[] a, int lo, int hi) {
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
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Test
    public void testExamples() {
        int res = findKthLargest(new int[]{-1, 2, 0}, 2);
        System.out.println(res);
        res = findKthLargest2(new int[]{2, 1}, 1);
        System.out.println(res);
        res = findKthLargest2(new int[]{1}, 1);
        System.out.println(res);
    }
}
