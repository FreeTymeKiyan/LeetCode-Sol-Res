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
        
    }
    
    /**
     * Do binary search on left and right
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