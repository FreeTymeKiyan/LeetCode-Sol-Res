/**
 * Given an an unsorted array, sort the given array.
 * You are allowed to do only following operation on array
 * flip(arr, i): Reverse array from 0 to i
 * 
 * Tags: Sort
 */
class PancakeSorting {
    public static void main(String[] args) {
        PancakeSorting p = new PancakeSorting();
        int[] A = {23, 10, 20, 11, 12, 6, 7};
        p.pancakeSort(A);
        for (int n : A) System.out.print(n + ", ");
    }
    
    /**
     * Find max from from start to end
     * If max is not at the end, filp it to first and flip it to end
     * Reduce array size by one 
     * Stop till size reduced to 1
     */
    public void pancakeSort(int[] A) {
        if (A == null || A.length <= 1) return;
        
        for (int i = A.length; i > 1; i--) { // i is current size
            int mi = findMax(A, i);
            if (mi != i) {
                flip(A, mi);
                flip(A, i - 1);
            }
        }
    }
    
    private void flip(int[] A, int i) {
        int temp, start = 0;
        while (start < i) {
            temp = A[start];
            A[start] = A[i];
            A[i] = temp;
            start++;
            i--;
        }
    }
    
    private int findMax(int[] A, int size) {
        int mi = 0;
        for (int i = 0; i < size; i++) {
            if (A[i] > A[mi]) mi = i;
        }
        return mi;
    }
}
