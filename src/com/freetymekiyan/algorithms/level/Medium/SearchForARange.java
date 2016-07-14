/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * Tags: Array, Binary Search
 */
class SearchForARange {
    public static void main(String[] args) {
        SearchForARange s = new SearchForARange();
        int[] A = { 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] range = s.searchRange(A, 3);
        System.out.println(range[0] + " ~ " + range[1]);
    }
    
    /**
     * Suppose we have a binary search helper method
     * With array, start index, end index, and target as arguments
     * We can first search for the target in the whole array
     * If found, then search for its starting position
     * Then search for its ending position
     * Update range with search result and return
     */
    public int[] searchRange(int[] A, int target) {
        int[] range = {-1, -1};
        if (A == null || A.length == 0) return range;
        int index = binarySearch(A, 0, A.length - 1, target);
        if (index != -1) {
            int left = index;
            int right = index;
            range[0] = left; // if no more occurrence, set left and right first
            range[1] = right;
            while ((left = binarySearch(A, 0, left - 1, target)) != -1) range[0] = left;
            while ((right = binarySearch(A, right + 1, A.length - 1, target))  != -1) range[1] = right;
        }
        return range;
    }
    
    private int binarySearch(int[] A, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target) return mid;
            else if (A[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
