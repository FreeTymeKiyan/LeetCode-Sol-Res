/**
 * Given two sorted arrays A, B of size m and n respectively. Find the k-th
 * smallest element in the union of A and B. You can assume that there are no
 * duplicate elements.
 * 
 * Tags: Array, 
 */
class KthSmallestSortedArrays {
    public static void main(String[] args) {
        int[] A = { 1, 2, 3, 4, 5, 6 };
        int[] B = { 2, 3, 4, 5, 6, 7 };
        System.out.println(kthSmallest(0, A, B));
        System.out.println(kthSmallest(1, A, B));
        System.out.println(kthSmallest(10, A, B));
        System.out.println(kthSmallest(11, A, B));
        System.out.println(kthSmallest(12, A, B));
        System.out.println(kthSmallest(13, A, B));
    }

    /**
     * Binary Search of A and B
     * O(lg m + lg n)
     * make i + j = k - 1
     * If Bj-1 < Ai < Bj, then Ai must be the k-th smallest,
     * or else if Ai-1 < Bj < Ai, then Bj must be the k-th smallest.
     */
    public static int kthSmallestRec(int k, int[] A, int[] B) {
        /*keep smaller array first one*/
        int n = A.length;
        int m = B.length;
        if (n > m)
            return kthSmallestRec(k, B, A);

        int k = (n + m - 1) / 2;
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        while (l < r) {
            int midA = (l + r) / 2;
            int midB = k - midA;
            if (A[midA] < B[midB])
                l = midA + 1;
            else
                r = midA;
        }

        // after binary search, we almost get the median because it must be between these 4 numbers: A[l-1], A[l], B[k-l], and B[k-l+1]
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if (((n + m) & 1) == 1) // total number is odd
           return (double) a;

        /*total number is even*/
        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0; // median of 2
    }

    /**
     * Two pointers start from the head of A & B
     * O(k)
     */
    public static int kthSmallest(int k, int[] A, int[] B) {
        k -= 1;
        int lenA = A.length;
        int lenB = B.length;
        if (k < 0 || k > lenA + lenB - 1) return -1; // out of range, 1 <= k <= A.length + B.length
        if (k == 0) return A[0] < B[0] ? A[0] : B[0];
        if (k == lenA + lenB - 1) return A[lenA - 1] < B[lenB - 1] ? B[lenB - 1] : A[lenA - 1];

        int i = 0;
        int j = 0;
        while (i + j < k) {
            if (A[i] < B[j] && i < A.length) {
                i++;
            } else if (A[i] >= B[j] && j < B.length){
                j++;
            }
            if (i + j == k && A[i] < B[j]) {
                return A[i];
            } else if (i + j == k && A[i] >= B[j]) {
                return B[j];
            }
        }
        return -1;
    }
}
