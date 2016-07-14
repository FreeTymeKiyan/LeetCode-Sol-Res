/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 * 
 * Note:
 * Your solution should be in logarithmic complexity.
 * 
 * Tags: Array, Binary Search
 */
class FindPeak {
    
    public static void main(String[] args) {
        int[] num = {1, 2, 1, 3, 1, 4, 1};
        System.out.println(new FindPeak().findPeakElement(num));
    }
    
    /**
     * Binary search for a peak. Other peaks can be ignored. 
     */
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0) return 0;
        int n = num.length;
        if (n <= 1) return 0;
        // handle the first and last element in num[]
        if (num[0] > num[1]) return 0;
        if (num[n - 1] > num[n - 2]) return n - 1;
        
        int left = 1, right = n - 2;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1]) return mid;
            else if (num[mid] > num[mid + 1]) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}