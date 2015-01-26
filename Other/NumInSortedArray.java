/**
 * Find how many times a number shows in an sorted array
 * 
 * Tags: Array
 */
class NumInSortedArray {
    public static void main(String[] args) {
        int[] A = {1, 1, 1, 2, 3, 4, 5, 6};
        int target = 1; // 0, 7, 1, 2
        System.out.println(numOfTimes(A, target));
    }
    
    /**
     * Binary search and expand
     * Deal with the corner cases for both ends of the array
     */
    public static int numOfTimes(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int l = 0;
        int r = A.length - 1;
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target) break;
            else if (A[m] < target) l = m + 1;
            else r = m - 1;
        }
        if (l > r) return 0;
        
        int start = m;
        int end = m;
        while (end + 1 < A.length && A[end] == A[end + 1]) end++;
        while (start - 1 >= 0 && A[start] == A[start - 1]) start--;
        return end - start + 1;
    }
}
