/**
 * Given a random array, re-arrange it to wiggle style. 
 * i.e. 
 * [1] A0 >= A1 <= A2 >= A3 .... .... An.
 * [2] A0 <= A1 >= A2 <= A3 .... .... An.
 * 
 * Tags: Sort, Array
 */
class WiggleSortUnsorted {
    public static void main(String[] args) {
        int[] A = { 1, 2, 8, 9, 3, 5};
        new WiggleSortUnsorted().wiggleSort(A);
        for (int i = 0; i < A.length; i++) {
            System.out.print(i == A.length - 1 ? A[i] : A[i] + ", ");
        }
        System.out.println();
    }
    
    /**
     * One-pass Solution.
     * First 2 elements already sorted, start from second element
     * For A[n-1], A[n], A[n+1], n >= 1, n < length - 1
     * Suppose sorted sequence are m1, m2, m3
     * If A[n-1] > A[n], thus A[n-2] < A[n-1], A[n-1]=m3, A[n]=m1, A[n+1]=m2
     * So A[n-2] < A[n-1] > A[n] < A[n+1]
     * If A[n-1] < A[n], thus A[n-2] > A[n-1], A[n-1]=m1, A[n]=m3, A[n+1]=m2
     * So A[n-2] > A[n-1] < A[n] > A[n+1]
     */
    public void wiggleSort(int[] A) {
        if (A == null || A.length == 0) return;
        for (int i = 1; i < A.length - 1; i++) {
            int m1 = Math.min(A[i - 1], Math.min(A[i], A[i + 1])); // min 
            int m3 = Math.max(A[i - 1], Math.max(A[i], A[i + 1])); // max
            int m2 = A[i - 1] + A[i] + A[i + 1] - m1 - m3; // middle
            if (A[i - 1] > A[i]) {
                A[i - 1] = m3;
                A[i] = m1;
                A[i + 1] = m2;
            } else {
                A[i - 1] = m1;
                A[i] = m3;
                A[i + 1] = m2;
            }
        }
    }
}
