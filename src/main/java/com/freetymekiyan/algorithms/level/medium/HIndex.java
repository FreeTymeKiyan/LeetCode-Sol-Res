package com.freetymekiyan.algorithms.level.medium;

/**
 * 274. H-Index
 * <p>
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least
 * h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and
 * the remaining two with no more than 3 citations each, his h-index is 3.
 * <p>
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * <p>
 * Hint:
 * <p>
 * An easy approach is to sort the array first.
 * What are the possible values of h-index?
 * A faster approach is to use extra space.
 * <p>
 * Company Tags: Bloomberg, Google, Facebook
 * Tags: Hash Table, Sort
 * Similar Problems: (M) H-Index II
 */
public class HIndex {

    /**
     * We actually want to know how many papers are there with >= some h.
     * Given an h, we can query some data structure to achieve that.
     * Let's assume there is a method m that takes h and outputs m(h).
     * m(i) = # of papers that have at least i citations.
     * If c(i) is the # of papers that have exactly i citations,
     * m(i) = c(i) + c(i+1) + ... + c(N), 0 <= i <= N.
     * When we calculate m(i), we would calculate c(N) multiple times, which is duplicate.
     * So we can store i->c(i) somewhere. That leads to a Map<Integer, Integer>.
     * And H-Index can be at most N when a person has N papers and all of them have >= N citations.
     * Now we know 0 <= i <= N. That further simplifies the map to an integer array.
     * We build this array. Go backwards to accumulate the # of papers.
     * When the # of papers >= i, return i.
     * If there is no such i, return 0.
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1];
        for (int c : citations) {
            if (c > n) {
                counts[n]++;
            } else {
                counts[c]++;
            }
        }
        int papers = 0;
        for (int i = n; i >= 0; i--) {
            papers += counts[i];
            if (papers >= i) {
                return i;
            }
        }
        return 0;
    }
}