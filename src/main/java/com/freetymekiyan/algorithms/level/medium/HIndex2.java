package com.freetymekiyan.algorithms.level.medium;

/**
 * 275. H-Index II
 * <p>
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
     * If citations[mid] = length - mid, return citations[mid].
     * If citations[mid] > length - mid, paper not enough, mid should be smaller.
     * If citations[mid] < length - mid, too many papers, mid should be larger.
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int l = 0;
        int h = citations.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (citations[m] == citations.length - m) {
                return citations[m];
            } else if (citations[m] > citations.length - m) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return citations.length - l;
    }

    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int l = 0;
        int h = citations.length;
        int mid;
        while (l < h) {
            mid = l + (h - l) / 2;
            if (citations[mid] == citations.length - mid) {
                return citations[mid];
            } else if (citations[mid] > citations.length - mid) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return citations.length - h;
    }
}