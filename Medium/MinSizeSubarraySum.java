/**
 * Given an array of n positive integers and a positive integer s, find the 
 * minimal length of a subarray of which the sum ≥ s. If there isn't one, return 
 * 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of 
 * which the time complexity is O(n log n).
 * 
 * Tags: Array, Two Pointers, Binary Search
 *
 * Similar Problems: (H) Minimum Window Substring, (E) Maximum Size Subarray Sum 
 *                  Equals k
 */
class MinSizeSubarraySum {

    /**
     * O(n)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int start = 0;
        int end = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while (end < nums.length) {
            sum += nums[end++];
            while (sum >= s) {
                min = Math.min(min, end - start);
                sum -= nums[start++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    /**
     * O(nlogn)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int i = 1;
        int j = nums.length;
        int min = 0;
        while (i <= j) {
            int mid = (j - i) / 2 + i;
            if (isWindowExist(mid, nums, s)) {
                j = mid - 1;
                min = mid;
            } else i = mid + 1;
        }
        return min;
    }
    
    private boolean isWindowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size) sum -= nums[i - size];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }
}