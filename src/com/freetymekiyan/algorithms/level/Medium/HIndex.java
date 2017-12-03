package com.freetymekiyan.algorithms.level.medium;

/**
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
     * Bucket sort.
     * Suppose n is the number of papers.
     * H can be at most n when a person has n papers and all of them have more than n citations.
     * To find a number h that h of his n papers have >= h citations, put papers in buckets.
     * All papers have >= n citations put into bucket n.
     * Papers have i citations put into bucket i.
     * Then count backwards.
     * The first number i that has total papers >= i is the answer.
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int papers = 0;
        for (int i = n; i >= 0; i--) {
            papers += buckets[i];
            if (papers >= i) {
                return i;
            }
        }
        return 0;
    }
}
