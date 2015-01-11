/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * Tags: Array
 */
class FirstMissingPositive {
    public static void main(String[] args) {
        int[] A = {1,2,0};
        System.out.println(new FirstMissingPositive().firstMissingPositive(A));
    }
    
    /**
     * Position of integer n should be n - 1 if sorted
     * Correct form [1, 2, 3, 4, ..., #, n]
     * If not in position swap it with A[A[p]-1]
     */
    public static int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) return 1;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int num = A[i];
            while (A[i] <= n && A[i] > 0 && A[num - 1] != num) {
                A[i] = A[num - 1];
                A[num - 1] = num;
                num = A[i];
            }
        }
        for (int i = 0; i < n; i++) 
            if (A[i] != i + 1) return i + 1;
        return n + 1; // nothing in middle losing, return largest
    }
}