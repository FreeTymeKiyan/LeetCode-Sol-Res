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
     * Heap. Merge.
     * Just like how we merge 2 sorted arrays, we can do the same to N arrays.
     * The only difference is that a min-heap is needed to find the next minimum.
     * Note that the median depends on whether we have odd or even total numbers, N.
     * If N is odd, median is the number at N / 2 + 1.
     * E.g. N = 7, N/2 = 3, the 4th number is the median.
     * If N is even, median is the average of N / 2 and N / 2 + 1.
     * E.g. N = 8, N/2 = 4, the average of 4th and 5th is the median.
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

        double median = 0;
        for (int i = 0; i < n / 2; i++) { // Loop n / 2 times.
            // Get min from heap, then add its next to heap if there is one.
            int[] min = pq.poll();
            if (min[1] < arrs[min[0]].length - 1) {
                min[1]++;
                min[2] = arrs[min[0]][min[1]];
                pq.offer(min);
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