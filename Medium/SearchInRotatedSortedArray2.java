/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * Tags: Array, Binary Search
 */
class SearchInRotatedSortedArray2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * Clarification: non-descending order
     * Ends up same as sequential search at worst. 
     */
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) return false;
        int l = 0;
        int r = A.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (A[m] == target) return true;
            /*skip*/
            if(A[l] == A[m] && A[m] == A[r]) {
                l++;
                r--;
            } else if(A[l] == A[m]) l = m + 1;
            else if(A[m] == A[r]) r = m;
            else if (A[l] < A[m]) { // left half sorted
                if (A[l] <= target && target < A[m]) r = m - 1;
                } else l = m + 1;
            } else if (A[l] > A[m]) { // right half sorted
                if (A[m] < target && target <= A[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return false;
    }
}