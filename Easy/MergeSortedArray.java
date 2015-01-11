/**
 * Tags: Array
 */
class MergeSortedArray {
    public static void main(String[] args) {
        
    }
    
    /**
     * Merge from behind
     */
    private static void merge(int A[], int m, int B[], int n) {
        if (n == 0) return;
        
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }
        
        /*still elements in B*/
        while (n > 0) A[n - 1] = B[n-- - 1];
    }
    
}