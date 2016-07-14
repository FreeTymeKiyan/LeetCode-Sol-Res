/**
 * Quick sort an integer arrays
 * 
 * Tags: Sort
 */
class QuickSort {
    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] A = { 1, 4, 2, 8, 5};
        q.sort(A, 0, A.length - 1);
        for (int n : A) System.out.print(n + ",");
    }
    
    /**
     * Partition the array according to middle index
     * Sort left half, from left to index - 1
     * Sort right half, from index to right
     */
    public void sort(int[] A, int left, int right) {
        int index = partition(A, left, right);
        if (left < index - 1) sort(A, left, index - 1);
        if (index < right) sort(A, index, right);
    }
    
    /**
     * Choose pivot
     * Init 2 pointers from both ends to do partition
     * Move left pointer if A[left] is smaller than pivot(skip smaller)
     * Move right pointer if A[right] is bigger than pivot(skip bigger)
     * Check if left <= right, if so, swap left and right, move them
     * Return left index
     */
    private int partition(int[] A, int left, int right) {
        int pivot = A[left + (right - left) / 2];
        while (left <= right) {
            while (A[left] < pivot) left++;
            while (A[right] > pivot) right--;
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
