package com.freetymekiyan.algorithms.level.medium;

/**
 * 740. Delete and Earn
 * <p>
 * Given an array nums of integers, you can perform operations on the array.
 * <p>
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element
 * equal to nums[i] - 1 or nums[i] + 1.
 * <p>
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 * <p>
 * Example 1:
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 * Example 2:
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation:
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 * Note:
 * <p>
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].
 * <p>
 * Related Topics: Dynamic Programming
 * Similar Questions: (E) House Robber
 */
public class DeleteAndEarn {

    /**
     * DP.
     * Number positions do not matter, only the counts matter.
     * When a number is deleted, all its neighbors are deleted.
     * So no other numbers can delete the same number.
     * So deleting one number <=> deleting all of them and earn num * count points.
     * This problem can somehow be converted to House Robbers.
     * Once we notice that a number is either all taken or not, we can convert the array to a points array.
     * [2, 2, 3, 3, 3, 4, 4] -> [0, 0, 4, 9, 4]
     * We cannot pick adjacent values is the same as we cannot rob adjacent houses.
     * Recurrence Relation:
     * The max points can be either
     * 1. Rob the current house, max total points of the house before previous house + current house points
     * 2. Don't rob the current house, same as previous maximum total points.
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) return 0;
        int[] points = new int[10001];
        for (int num : nums) {
            points[num] += num;
        }
        int curr = points[0];
        int prev = 0;
        for (int i = 1; i < points.length; i++) {
            int max = Math.max(curr, prev + points[i]);
            prev = curr;
            curr = max;
        }
        return curr;
    }
}
