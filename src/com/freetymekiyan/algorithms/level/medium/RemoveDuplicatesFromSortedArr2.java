package com.freetymekiyan.algorithms.level.medium;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't
 * matter what you leave beyond the new length.
 * <p>
 * Company Tags: Facebook
 * Tags: Array, Two Pointers
 */
class RemoveDupFromSortedArr2 {

    public static void main(String[] args) {

    }

    /**
     * skip if length <=2
     * compare current element with second last element
     */
    public int removeDuplicates(int[] A) {
        if (A == null) {
            return 0;
        }
        int n = A.length;
        if (n <= 2) {
            return n; // no need to deal with n<=2 case.
        }
        int len = 2, i = 2;
        while (i < n) {
            // compare cuurent with second last element
            if (A[i] != A[len - 2]) {
                A[len++] = A[i];
            }
            i++;
        }
        return len;
    }
}