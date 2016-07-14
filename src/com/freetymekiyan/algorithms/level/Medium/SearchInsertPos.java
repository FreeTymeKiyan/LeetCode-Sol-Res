/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * 
 * Tags: Array, Binary Search
 */
class SearchInsertPos {
    public static void main(String[] args) {
        
    }
    
    /**
     * Binary search
     * r = m - 1, l = m + 1
     */
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target) return m;
            else if (A[m] > target) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}