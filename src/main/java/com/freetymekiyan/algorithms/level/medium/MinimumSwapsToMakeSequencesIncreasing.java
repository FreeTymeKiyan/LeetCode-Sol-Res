package com.freetymekiyan.algorithms.level.medium;

/**
 * 801. Minimum Swaps To Make Sequences Increasing
 * <p>
 * We have two integer sequences A and B of the same non-zero length.
 * <p>
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their
 * respective sequences.
 * <p>
 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and
 * only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 * <p>
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that
 * the given input always makes it possible.
 * <p>
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Note:
 * <p>
 * A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 * <p>
 * Related Topics: Dynamic Programming
 * Company: Facebook
 */
public class MinimumSwapsToMakeSequencesIncreasing {

    /**
     * Dynamic Programming.
     * The result is guaranteed to be possible.
     * So either both are already increasing, or the increasing can be fixed with swapping.
     * Model all possible situations:
     * 1. If A[i-1] < A[i] and B[i-1] < B[i], may either don't swap or both swap.
     * 2. If A[i-1] < B[i] and B[i-1] < A[i], may swap only once.
     * 3. For all other situations, swap is impossible.
     * States:
     * One for if A[i-1] and B[i-1] were swapped, S, one for if they were not, N.
     * Since it affects which numbers A[i] and B[i] should compare to.
     * Recurrence Relation:
     * If A[i-1] < A[i] and B[i-1] < B[i]:
     * newN = min(oldN, newN), newS = min(oldS + 1, newS)
     * If A[i-1] < B[i] and B[i-1] < A[i]:
     * newN = min(newN, oldS), newS = min(oldN + 1, newS)
     * Base Case:
     * Length 1, N = 0, S = 1.
     */
    public int minSwap(int[] A, int[] B) {
        int len = A.length;
        int s = 1; // A[0] and B[0] are swapped.
        int n = 0; // No swap.
        for (int i = 1; i < len; i++) {
            int newS = Integer.MAX_VALUE;
            int newN = Integer.MAX_VALUE;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                newS = Math.min(newS, s + 1); // Either cannot swap or swap both i-1 and i.
                newN = Math.min(newN, n); // Either cannot swap or don't swap.
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                newS = Math.min(n + 1, newS); // Either swap i and not i-1, or itself.
                newN = Math.min(s, newN); // Either swap i-1 and not i, or itself.
            }
            s = newS;
            n = newN;
        }
        return Math.min(s, n); // Return the min of the two cases.
    }
}