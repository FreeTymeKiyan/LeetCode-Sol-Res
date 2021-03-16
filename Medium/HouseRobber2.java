/**
 * Note: This is an extension of House Robber.
 *
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will
 * not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house
 * is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the
 * previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 *
 * Tags: Dynamic Programming
 * Similar Problems: (E) House Robber, (M) Paint House, (E) Paint Fence, (M) House Robber III
 */
public class HouseRobber2 {

    /**
     * rob a circle can be divided into rob 2 rows
     * 1. from 1st house to second last house
     * 2. from 2nd house to last house
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(robRow(nums, 0, nums.length - 2), robRow(nums, 1, nums.length - 1));
    }

    private int robRow(int[] nums, int start, int end) {
        int include = 0, exclude = 0;
        for (int j = start; j <= end; j++) {
            int i = include, e = exclude;
            include = e + nums[j]; // include current number
            exclude = Math.max(e, i); // the bigger of previous include and previous exclude
        }
        return Math.max(include, exclude);
    }

    public static void main(String[] args) {
        HouseRobber2 hr = new HouseRobber2();
        int res = hr.rob(new int[]{1, 2, 3, 4, 5});
        System.out.println("rob: " + res);
    }
}
