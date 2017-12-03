package com.freetymekiyan.algorithms.level.hard;

/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Note:
 * Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.
 * <p>
 * Examples:
 * <p>
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * Output:
 * 18
 * <p>
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * <p>
 * Company Tags: Baidu, Facebook
 * Tags: Binary Search, Dynamic Programming
 */
public class SplitArrayLargestSum {

    /**
     * Binary Search.
     * The max of the result is the sum of all the input numbers.
     * The min of the result is the max num of the input numbers.
     * Given a result, validate it by checking whether it generates m cuts.
     * Given the 3 conditions above we can do a binary search.
     * Use long for range to avoid overflow since it's sum of array.
     */
    public int splitArray(int[] nums, int m) {
        // Find sum and max.
        long sum = 0; // May overflow.
        int max = 0;
        for (int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }
        // Binary search the minimum sum limit.
        long lo = max;
        long hi = sum;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (validate(nums, mid, m)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return (int) lo; // Convert long to int.
    }

    /**
     * Validate whether given the max exceeds m subarrays.
     * If it needs more than m, return false. Otherwise return true.
     * Use an integer sum for current subarray sum.
     * Use an integer count for the # of subarrays.
     * For each number n in nums:
     * | Update sum to sum + n.
     * | If sum > max:
     * |   sum = n
     * |   count++
     * |   If count > m, return false.
     * Return true.
     */
    private boolean validate(int[] nums, long max, int m) {
        int sum = 0;
        int count = 1; // IMPORTANT! Already have 1 array.
        for (int n : nums) {
            sum += n;
            if (sum > max) {
                sum = n;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }

}
