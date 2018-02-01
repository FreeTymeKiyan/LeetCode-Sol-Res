package com.freetymekiyan.algorithms.other;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given n sorted arrays, find the median of them.
 * <p>
 * Companies: Facebook
 */
public class MedianOfNSortedArrays {

    /**
     * Recall median of 2 sorted arrays. Binary search. O(logn).
     * Cannot use binary search anymore here.
     * <p>
     * Assuming the arrays are sorted strictly ascending.
     * <p>
     * We may put all the first numbers of an array to a min heap.
     * This min heap will return the next minimum value.
     * And maintain an array of n pointers, each points to one array.
     * Need to know which array this value belongs to.
     * Increment the index of that array.
     * If the pointer is out of bounds, then no more numbers of that array will be added to heap.
     * <p>
     * A linear scan to find out which array the minimum value belongs to is expansive.
     * Can we return which array and the index together with the minimum value?
     * Sounds like another array.
     */
    public double getMedian(int[][] arrs) {
        if (arrs.length == 0) return 0;
        // int[] is {row, column, value}
        PriorityQueue<int[]> pq = new PriorityQueue<>(arrs.length, Comparator.comparingInt(a -> a[2]));
        int n = 0; // Total number of integers.
        for (int i = 0; i < arrs.length; i++) {
            pq.offer(new int[]{i, 0, arrs[i][0]});
            n += arrs[i].length;
        }

        /*
         * Note that median's definition is:
         * If total number is odd, median is the number right in the middle.
         * If total number is even, median is the average of the 2 numbers in the middle.
         */
        double median = 0;
        /*
         * When n = 2k+1, n/2 = k, loop runs k times. The next number is the median.
         * E.g. n = 7, n/2 = 3, the 4th number is the median.
         * When n = 2k, n/2 = k, loop runs k times.
         * E.g. n = 8, n/2 = 4, the average of 4th and 5th is the median.
         */
        for (int i = 0; i < n / 2; i++) {
            int[] min = pq.poll();
            if (min[1] < arrs[min[0]].length - 1) {
                min[1]++; // Move pointer one step back.
                min[2] = arrs[min[0]][min[1]]; // Update value.
                pq.offer(min); // Add to heap.
            }
            if (i == n / 2 - 2 && n % 2 == 0) { // When n is even, record the last 2 values.
                median = min[2];
            } else if (i == n / 2 - 1 && n % 2 == 0) {
                median = (median + min[2]) / 2;
            } else if (i == n / 2 - 1 && n % 2 == 1) { // When n is odd, poll one more value at the last iteration.
                median = pq.poll()[2];
            }
        }
        return median;
    }
}