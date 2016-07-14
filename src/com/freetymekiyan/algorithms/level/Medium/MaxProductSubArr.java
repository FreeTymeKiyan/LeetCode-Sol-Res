/**
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * 
 * Tags: Array, DP
 */
class MaxProductSubArr {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * DP, update according to A[i]
     * f(k) = Largest product subarray, from index 0 up to k.
     * g(k) = Smallest product subarray, from index 0 up to k.
     * 
     * f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
     * g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )
     */
    public int maxProduct(int[] A) {
        if (A == null || A.length == 0) return 0;

        int max = A[0], min = A[0], res = A[0];
        for (int i = 1; i < A.length; i++) {
            int preMax = max, preMin = min; // results of last loop
            max = Math.max(Math.max(A[i], preMax * A[i]), preMin * A[i]);
            min = Math.min(Math.min(A[i], preMax * A[i]), preMin * A[i]);
            res = Math.max(max, res);
        }
        return res;
    }
}
