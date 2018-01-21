package com.freetymekiyan.algorithms.level.medium;

/**
 * 413. Arithmetic Slices
 * <p>
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any
 * two consecutive elements is the same.
 * <p>
 * For example, these are arithmetic sequence:
 * <p>
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 * <p>
 * 1, 1, 2, 5, 7
 * <p>
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such
 * that 0 <= P < Q < N.
 * <p>
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * <p>
 * The function should return the number of arithmetic slices in the array A.
 * <p>
 * Example:
 * <p>
 * A = [1, 2, 3, 4]
 * <p>
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 * <p>
 * Related Topics: Math, Dynamic Programming
 * Similar Questions: (H) Arithmetic Slices II - Subsequence
 */
public class ArithmeticSlices {

    /**
     * If we find an arithmetic array of length n, the number of slices it has is:
     * (n - 2) * (n - 1) / 2
     * E.g. [1, 2, 3, 4], n = 4, # of arithmetic slices = (4-2)*(4-1) / 2 = 3.
     * With this formula, we can go through the array, find arithmetic slices, maintain a total # of slices.
     * For each number of index i in A:
     * Search for the arithmetic slice's end j, which is initialized as i + 2.
     * While j is within the array and A[j-2], A[j-1] and A[j] are arithmetic, increment j.
     * As j stops when the difference no longer equals, the length would be j - i.
     * If length < 3, not enough elements, increment i by 1 to the next integer.
     * If length >= 3, add (len - 1) * (len - 2) / 2 to total.
     * i should start from j-1 which is at the end of current slice.
     */
    public int numberOfArithmeticSlices(int[] A) {
        int num = 0;
        for (int i = 0; i < A.length - 2; ) {
            int j = i + 2;
            while (j < A.length && A[j - 1] - A[j] == A[j - 2] - A[j - 1]) j++;
            int len = j - i;
            if (len < 3) {
                i++;
                continue;
            }
            num += (len - 1) * (len - 2) / 2;
            i = j - 1;
        }
        return num;
    }

    /**
     * Same idea. Slightly different implementation.
     * We skip the first 2 integers in A and start directly from the 3rd.
     * Record the count of integers that can form an arithmetic slices.
     * If this is part of an arithmetic slice, count plus 1.
     * If it is not, that means an arithmetic slice has ended:
     * | count is actually total length - 2. So the formula becomes count * (count + 1) / 2.
     * | Add it to total.
     * | Reset count for next slice.
     * In the end, imagine the end of array has a valid slice.
     * Then we can have one more slice that is not added.
     * Add it before returning.
     */
    public int numberOfArithmeticSlices2(int[] A) {
        int num = 0;
        int count = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                count++;
            } else {
                num += count * (count + 1) / 2;
                count = 0;
            }
        }
        num += count * (count + 1) / 2;
        return num;
    }

    /**
     * DP. Space Optimized.
     * State:
     * dp[i]: # of arithmetic slices with integer A[i].
     * Recurrence Relation:
     * Suppose we have x arithmetic slices in A[0...i-1].
     * If A[i] forms another arithmetic slice, how many new arithmetic slices will we add?
     * Each of the x slices can be combined with A[i]. This is x.
     * The one in the end which only has 2 integers now can be a new arithmetic slice. This is 1.
     * So x+1 new slices.
     * If A[i] - A[i-1] = A[i-1] - A[i-2], dp[i] = 1 + dp[i-1].
     * If not, dp[i] = 0.
     * Base case:
     * dp[0] = 0, dp[1] = 0.
     * <p>
     * The array can be reduced to a single integer since we only need dp[i-1] for dp[i].
     * The return value is the total of all the arithmetic slices.
     */
    public int numberOfArithmeticSlices3(int[] A) {
        int num = 0;
        int pre = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                pre++;
                num += (pre);
            } else {
                pre = 0;
            }
        }
        return num;
    }
}