import java.util.*;

/**
 * Find duplications from two sorted array
 * 
 * Tags: Array, Two Pointers
 */
class Dup2SortedArr {
    public static void main(String[] args) {
        int[] A = { 1, 2, 3, 4, 5, 6 };
        int[] B = { 3, 4, 5, 6, 7, 8 };
        Dup2SortedArr d = new Dup2SortedArr();
        System.out.println(d.duplicates(A, B));
    }
    
    /**
     * Two pointers for the 2 arrays
     */
    public List<Integer> duplicates(int[] A, int[] B) {
        List<Integer> res = new ArrayList<Integer>();
        if (A == null || B == null || A.length == 0 || B.length == 0) return res;
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                i++;
            } else if (A[i] > B[j]) {
                j++;
            } else {
                res.add(A[i]);
                i++;
                j++;
            }
        }
        return res;
    }
}