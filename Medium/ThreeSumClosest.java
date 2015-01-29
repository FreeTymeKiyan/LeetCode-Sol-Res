import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the 
 * sum is closest to a given number, target. Return the sum of the three 
 * integers. You may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * Tags: Arrays, Two pointers
 */
class ThreeSumClosest {
    public static void main(String[] args) {
        
    }
    
    /**
     * Sort and initialize min
     * use two pointers to manipulate sums
     * update min when closer
     * return when min equals to target or all done
     */
    public int threeSumClosest(int[] num, int target) {
        int closest = 0;
        if (num == null) return closest;
        Arrays.sort(num);
        for (int i = 0; i < num.length && i < 3; i++) closest += num[i];
        if (num.length < 3) return closest;
        for (int i = 0; i < num.length - 2; i++) {
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                int sum = num[i] + num[j] + num[k];
                if (Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum; // sum is even closer
                    if (closest == target) return closest; // sum == target
                }
                if (sum > target) k--;
                else j++;
            }
        }
        return closest;
    }
}
