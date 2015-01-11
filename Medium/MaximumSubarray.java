/**
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * 
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 * 
 * Tags: Divide and Conquer, Array, DP
 */
class MaximumSubarray {
    public static void main(String[] args) {
        int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(maxSubArray(A));
    }
    
    /**
     * DP, O(n)
     * currentMax = max(currentMax + A[i], A[i])
     * that means if A[i] < 0, currentMax won't update
     * maxSubArr = max(currentMax, maxSubArr)
     */
    static int maxSubArray(int[] A) {
        if (A == null || A.length == 0) return 0;
        int curMax = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) { // note that i starts from 1
            curMax = Math.max(curMax + A[i], A[i]);
            max = Math.max(curMax, max);
        }
        return max;
    }
    
    /**
     * DP, O(n) Time, O(n) Space
     */
    int maxSubArrayB(int[] A) {
        if (A == null || A.length == 0) return 0;
        int[] s = new int[A.length]; 
        s[0] = A[0];
        int max = A[0]; 
        for (int i = 1; i < n; i++) {
           s[i] = s[i - 1] > 0 ? (A[i] + s[i - 1]) : A[i];
           max = Math.max(max, s[i]); 
        }
        return max;
    }

    /**
     * Divide and Conquer, w/ middle, or wo/ middle in the left, or in the right
     */
    static int maxSubArrayDC(int[] A) {
        
    }
}
