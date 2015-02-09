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
     * prev is the current minimum-possible value of the last-seen element 
     * given that value of K
     */
    private int minKStrictAscending(int[] A) {
        if (A == null || A.length == 0) return 0;
        int k = 0;
        int prev = A[0];
        for (int i = 1; i < A.length; i++) {
            if (prev >= A[i] + k) { // out of range, A[i] too small for k
                int correction = (prev - (A[i] + k)) / 2 + 1;
                k += correction;
                prev -= correction; // to min possible value
                prev++;
            } else if (prev < A[i] - k) { // prev + k < A[i], out of prev
                prev = A[i] - k;
            } else { // prev + k >= A[i] > prev - k, within prev
                prev++;
            }
        }
        return k;
    }
    
}
