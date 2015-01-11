/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Tags: Array, Binary Search
 */
class SearchRotatedSortedArr {
    public static void main(String[] args) {
        
    }
    
    /**
     * Binary Search. 
     * Check which half is sorted.
     * If target is within that half, search in that half. 
     * If not, search in the other half. 
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target) return m;
            if (A[m] >= A[l]) { // left half sorted
                if (target >= A[l] && target < A[m]) {
                    r = m - 1;
                } else l = m + 1;
            } else { // right half sorted
                if (target > A[m] && target <= A[r]) {
                    l = m + 1;
                } else r = m - 1;
            }
        }
        return -1;
    }
}