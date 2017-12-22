package com.freetymekiyan.algorithms.level.hard;

/**
 * 410. Split Array Largest Sum
 * <p>
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
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
 * Related Topics: Binary Search, Dynamic Programming
 */
public class SplitArrayLargestSum {

    /**
     * Binary Search.
     * This idea starts from the result.
     * If we notice that the output can only be in a range:
     * | The max is the sum of all the numbers in the input array.
     * | The min is the maximum of the input numbers.
     * Now, if we propose an output, and try to divide the array, it may or may not fit into m subarrays.
     * If it fits, we further propose a smaller output.
     * If it doesn't, we further propose a bigger output.
     * In the end, we will get to an output that can generate m arrays and cannot be smaller.
     * Note that use long for the sum to avoid overflow.
     */
    public int splitArray(int[] nums, int m) {
        // Generate possible output range.
        long sum = 0; // May overflow.
        int max = 0;
        for (int n : nums) {
            sum += n;
            max = n > max ? n : max;
        }
        // Binary search the minimum output.
        long lo = max;
        long hi = sum;
        while (lo < hi) {
            long output = lo + (hi - lo) / 2;
            if (validate(nums, m, output)) {
                hi = output;
            } else {
                lo = output + 1;
            }
        }
        return (int) lo; // Convert long to int.
    }

    /**
     * Validate whether given the max sum we can generate <= m subarrays.
     * If it needs more than m, return false. Otherwise return true.
     * Use a long sum for current subarray sum.
     * Use an integer count for the # of subarrays. Initialized as 1.
     * For each number n in nums:
     * | Add n to current sum.
     * | If sum > max sum, current subarray sum exceeds the limit:
     * |   reset sum to n as the subarray sum of the next subarray.
     * |   subarray count increments by 1.
     * |   If count > m, we need more than m subarrays, return false.
     * Return true.
     */
    private boolean validate(int[] nums, int m, long maxSum) {
        long sum = 0; // Current subarray sum.
        int count = 1; // IMPORTANT! Already have 1 array even without cut.
        for (int n : nums) {
            sum += n;
            if (sum > maxSum) { // Cannot be >=. Equal is still valid and does NOT need one more cut.
                sum = n; // The number n is the first element of the next subarray.
                count++;
                if (count > m) { // Early exit. Or after the total subarray count generated, check at return count <= m.
                    return false;
                }
            }
        }
        return true;
    }

}