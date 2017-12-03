package com.freetymekiyan.algorithms.level.medium;

/**
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * <p>
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray
 * A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * <p>
 * Return the modified array after all k operations were executed.
 * <p>
 * Example:
 * <p>
 * Given:
 * <p>
 * length = 5,
 * updates = [
 * [1,  3,  2],
 * [2,  4,  3],
 * [0,  2, -2]
 * ]
 * <p>
 * Output:
 * <p>
 * [-2, 0, 3, 5, 3]
 * Explanation:
 * <p>
 * Initial state:
 * [ 0, 0, 0, 0, 0 ]
 * <p>
 * After applying operation [1, 3, 2]:
 * [ 0, 2, 2, 2, 0 ]
 * <p>
 * After applying operation [2, 4, 3]:
 * [ 0, 2, 5, 5, 3 ]
 * <p>
 * After applying operation [0, 2, -2]:
 * [-2, 0, 3, 5, 3 ]
 * <p>
 * Hint:
 * <p>
 * Thinking of using advanced data structures? You are thinking it too complicated.
 * For each update operation, do you really need to update all elements between i and j?
 * Update only the first and end element is sufficient.
 * The optimal time complexity is O(k + n) and uses O(1) extra space.
 * <p>
 * Company Tags: Google
 * Tags: Array
 */
public class RangeAddition {

    /**
     * Instead of update all values in the range, we update the start index with the value.
     * And the index after end index with negative value. (Safely ignore the last index here.)
     * After all updates, the value of current index will be the sum of all values from start to current index.
     * <p>
     * Why?
     * During the sum, each value will be added to the position.
     * By setting the negative value, it removes the value from the indices after.
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] u : updates) {
            int start = u[0];
            int end = u[1];
            int val = u[2];

            res[start] += val;
            if (end + 1 < length) {
                res[end + 1] -= val;
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }

}
