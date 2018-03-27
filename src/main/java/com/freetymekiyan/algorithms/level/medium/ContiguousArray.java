package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 * <p>
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 * <p>
 * Related Topics: Hash Table
 * Similar Questions: (M) Maximum Size Subarray Sum Equals k
 */
public class ContiguousArray {

    /**
     * Hash Table.
     * Use an integer, count, to track the difference between # of 0s and # of 1s.
     * Whenever the difference is the same, there are equal # of 0s and 1s in between.
     * The count ranges from -n (all 1s) to n (all 0s).
     * So we can create a 2*n + 1 long array, as a map, to store the first index of this count.
     * When there is another count, we can subtract and check the length.
     * Note that when there is no element in the array, count = 0 still holds.
     * This is a special case that when 0s and 1s are equal from the beginning to current index.
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] countToIndex = new int[2 * n + 1];
        Arrays.fill(countToIndex, -2); // Fills with -2 since countToIndex[n] is -1.
        countToIndex[n] = -1; // When # of 0s and # of 1s are the same from beginning to i, the length should be i + 1.
        int maxLength = 0;
        int diffCnt = 0;
        for (int i = 0; i < n; i++) {
            diffCnt += (nums[i] == 0 ? 1 : -1);
            if (countToIndex[diffCnt + n] > -2) {
                maxLength = Math.max(maxLength, i - countToIndex[diffCnt + n]);
            } else {  // No such count appeared yet.
                countToIndex[diffCnt + n] = i;
            }
        }
        return maxLength;
    }

    /**
     * Hash Table.
     * Use map directly.
     */
    public int findMaxLength2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int count = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 0 ? 1 : -1);
            if (map.containsKey(count)) {
                maxLength = Math.max(maxLength, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxLength;
    }
}
