/**
 * Find the minimum positive number K for an array such that for each item in 
 * the array, adding or substracting a number from [0, K], to make the array in 
 * strictly ascending order. 
 * 
 * Example:
 * [10, 2, 20]
 * the min K is 5, a possible result is [10-5, 2+4, 20]
 * 
 * Tags: DP
 */
class MinKStrictAscending {
    public static void main(String[] args) {
        int[] A = { 10, 2, 20 };
        int[] B = { 5, 4, 3, 2, 8 };
        MinKStrictAscending m = new MinKStrictAscending();
        System.out.println(m.minKStrictAscending(A));
        System.out.println(m.minKStrictAscending(B));
    }
    
    /**
     * DP
     * Keep track of previous minimum possible value and k while iterating over
     * the array
     * If A[i] <= prev - k, which means k is not big enough, calculate 
     * correction value and update k and prev. 
     * If A[i] > prev - k, even > prev + k, update prev to A[i] - k
     * If prev + k >= A[i] > prev - k, which means it's within prev's reach, 
     * just do prev++ for next item in array
     */
    private int minKStrictAscending(int[] A) {
        if (A == null || A.length == 0) return 0;
        int k = 0;
        int prev = A[0]; // previous minimum possible value
        for (int i = 1; i < A.length; i++) {
            if (prev >= A[i] + k) { // A[i] too small, current k is not enough
                int correction = (prev - (A[i] + k)) / 2 + 1;
                k += correction;
                prev -= correction;
                prev++;
            } else if (prev < A[i] - k) { // out of prev's range
                prev = A[i] - k;
            } else { // within prev's range
                prev++;
            }
        }
        return k;
    }
    
}
