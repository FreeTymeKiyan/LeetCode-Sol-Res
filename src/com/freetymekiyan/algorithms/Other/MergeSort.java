/**
 * Merge sort an integer arrays
 * 
 * Tags: Sort
 */
class MergeSort {
    public static void main(String[] args) {
        MergeSort m = new MergeSort();
        int[] A = { 1, 4, 2, 8, 5 };
        for (int n : A) System.out.print(n + ",");
        System.out.println();
        m.sort(A, 0, A.length - 1);
        for (int n : A) System.out.print(n + ",");
    }
    
    /**
     * If range exists
     * Get middle index
     * Sort first half, from low to middle
     * Sort second half, from middle + 1 to high
     * Merge these two halves
     */
    public void sort(int[] A, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            sort(A, low, middle);
            sort(A, middle + 1, high);
            merge(A, low, middle, high);
        }
    }
    
    /**
     * Copy items from low to high to a helper array
     * Init 2 pointers 
     * Compare value of 2 pointers, and overwrite original array, and move on
     * Stop when left reach middle or right reach high
     * Right side already in original array, so copy items remain in left side
     * to original array
     */
    private void merge(int[] A, int low, int middle, int high) {
        int[] helper = new int[A.length];
        for (int i = low; i <= high; i++) {
            helper[i] = A[i];
        }
        
        int left = low;
        int right = middle + 1;
        int curIdx = low;
        
        while (left <= middle && right <= high) {
            if (helper[left] <= helper[right]) {
                A[curIdx] = helper[left];
                left++;
            } else {
                A[curIdx] = helper[right];
                right++;
            }
            curIdx++;
        }
        
        int remain = middle - left; // items remain in left part
        for (int i = 0; i <= remain; i++) {
            A[curIdx + i] = helper[left + i]; // move to origin array
        }
    }
}
