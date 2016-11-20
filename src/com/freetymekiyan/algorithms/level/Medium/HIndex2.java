package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * <p>
 * Hint:
 * Expected runtime complexity is in O(log n) and the input is sorted.
 * <p>
 * Company Tags: Facebook
 * Tags: Binary Search
 * Similar Problems: (M) H-Index
 */
public class HIndex2 {

    /**
     * Binary Search.
     * Think about the definition of h index: h papers that have >= h citations.
     * If randomly pick an index in the citations array, mid.
     * The # of papers have >= h citations is: array length - mid.
     * If citations[mid] = length - mid, return mid.
     * If citations[mid] > length - mid, paper not enough, mid should be smaller.
     * If citations[mid] < length - mid, too many papers, mid should be larger.
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int lo = 0;
        int hi = citations.length;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (citations[mid] >= citations.length - mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return citations.length - lo; // Cannot be citations[lo] since search might fail and lo can out of bounds.
    }

    @Test
    public void testExamples() {
        HIndex2 h2 = new HIndex2();
        int ans;

        // [] -> 0
        int[] citations1 = new int[0];
        ans = h2.hIndex(citations1);
        Assert.assertEquals(0, ans);

        // [1] -> 1
        int[] citations2 = new int[]{1};
        ans = h2.hIndex(citations2);
        Assert.assertEquals(1, ans);

        // [1.2.3.4.5] -> 3
        int[] citations3 = new int[]{1, 2, 3, 4, 5};
        ans = h2.hIndex(citations3);
        Assert.assertEquals(3, ans);

        // [5,6,7,8,9] -> 5
        int[] citations4 = new int[]{5, 6, 7, 8, 9};
        ans = h2.hIndex(citations4);
        Assert.assertEquals(5, ans);
    }
}
