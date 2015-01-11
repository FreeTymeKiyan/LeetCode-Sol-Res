/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 *
 * Tags: Math, Binary Search
 */
class Sqrt {
    public static void main(String[] args) {
        int[] nums = {  -1, 1, 2, 4, 9, 16, 25 };
        for (int i = 0; i < nums.length; i++) {
            System.out.println(sqrt(nums[i]));
        }
    }

    /**
     * Validate input first
     * Binary Search from 1 ~ x
     * 
     * Negative?
     * Perfect square?
     * Note possible overflows when mid * mid or (left + right) / 2.
     */
    public static int sqrt(int x) {
        if (x < 0) return -1; // if (x <= 0) return x;
        if (x == 0) return 0;
        int left = 1; // search range
        int right = x;
        int mid;

        while (left <= right) { // can equal
            mid = left + (right - left) / 2; // left + right can overflow
            if (mid == x / mid) return mid; // mid * mid can overflow
            else if (mid > x / mid) right = mid - 1; // not right = mid
            else left = mid + 1; // break equal
        }

        return right;
    }
}