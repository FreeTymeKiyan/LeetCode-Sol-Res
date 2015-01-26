/**
 * Given a sorted array, and re-arrange it to wiggle style in one pass.
 * i.e. 
 * [1] A0 >= A1 <= A2 >= A3 .... .... An.
 * [2] A0 <= A1 >= A2 <= A3 .... .... An.
 * 
 * Tags: Sort, Array
 */
class WiggleSort {
    public static void main(String[] args) {
        
    }
    
    /**
     * Swap neighbors
     * A0 >= A1 <= A2 >= A3 .... .... An.
     */
    public void wiggleSort(int[] A) {
        if (A == null || A.length == 0) return;
        for (int i = 0; i < A.length - 1; i += 2) {
            swap(A, i, i + 1);
        }
    }
    
    /**
     * A0 <= A1 >= A2 <= A3 .... .... An.
     */
    public void wiggleSort(int[] A) {
        if (A == null || A.length == 0) return;
        for (int i = 1; i < A.length - 1; i += 2) {
            swap(A, i, i + 1);
        }
    }
}