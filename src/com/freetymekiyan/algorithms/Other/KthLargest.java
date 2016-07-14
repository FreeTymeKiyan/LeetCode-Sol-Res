import java.util.*;

/**
 * k largest(or smallest) elements in an array
 * 
 * Tags: Array, Sort, Heap
 */
class KthLargest {
    public static void main(String[] args) {
        KthLargest K = new KthLargest();
        // int[] A = { 1, 3, 5, 2, 4, 6 };
        int[] A = {1, 23, 12, 9, 30, 2, 50};
        for (int k = 1; k <= A.length; k++) {
            System.out.println(K.findKthLargest(A, k));
            // System.out.println(K.findKthLargestB(A, k));
        }
    }
    
    /**
     * Priority Queue
     * O(n) + k * O(logn)
     */
    public int findKthLargest(int[] A, int k) {
        if (k <= 0 || k > A.length) return -1;
        Queue<Integer> q = new PriorityQueue<Integer>(A.length, Collections.reverseOrder());
        for (int n : A) q.add(n);
        int res = 0;
        for (int i = 0; i < k; i++) res = q.poll();
        return res;
    }
    
    /**
     * QuickSelect
     * Use partition algorithm in Quick Sort
     * Compare partition index with k - 1
     * If index > k - 1, means upper bound can be index - 1
     * If index < k - 1, means lower bound can be index + 1
     * If index == k - 1, return that number
     */
    public int findKthLargestB(int[] A, int k) {
        if (k <= 0 || k > A.length) return -1;
        
        int l = 0; // initialize
        int r = A.length - 1;
        int index;
        
        while (l < r) {
            index = partition(A, l, r);
            if (index > k - 1) {
                r = index - 1;
            } else if (index < k - 1) {
                l = index + 1;
            } else {
                return A[index];
            } 
        }
        return A[l];
    }
    
    /**
     * Choose mid value as pivot
     * Move two pointers
     * Swap and move on
     * Return left pointer
     */
    private int partition(int[] a, int left, int right) {
        int pivot = a[left + (right - left) / 2];
        while(left <= right) {
            while(a[left] > pivot) left++;
            while(a[right] < pivot) right--;
            if (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
