/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
 * 
 * Tags: Divide and Conquer, Array, Binary Search
 */
class MedianOfTwoSortedArrs {
    public static void main(String[] args) {
        MedianOfTwoSortedArrs m = new MedianOfTwoSortedArrs();
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {2, 4, 5, 6, 7};
        System.out.println(m.findMedianSortedArrays(A, B));
    }
    
    /**
     * Search in shorter array
     * Find 4 possible candidates A[l-1], A[l], B[k-1], B[k-l+1]
     * If total # of items is odd, return the max of A[l-1] and B[k-1], a
     * If total # of items is even, get the min of A[l] and B[k-l+1], b
     * Return the average of a and b
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m) return findMedianSortedArrays(B, A); // shorter array first
        int k = (n + m - 1) / 2; // mid position
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        // find A[l] > B[k - l]
        while (l < r) {
            int midA = l + (r - l) / 2; // A[i], avoid overflow
            int midB = k - midA; // B[j - 1]
            if (A[midA] < B[midB])
                l = midA + 1; // i + 1, r
            else
                r = midA; // l, i
        }
        // A[l-1], A[l], B[k-l], and B[k-l+1] 
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if ((n + m) % 2 == 1) return (double) a; // odd

        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0; // even
    }
}
