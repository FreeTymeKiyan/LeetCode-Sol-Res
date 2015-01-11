/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * 
 * Tags: Array, Binary Search
 */
class FindRotatedArrMin {
    public static void main(String[] args) {
        int[] num = { 3, 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(findMin(num));
    }
    
    static int findMin(int[] num) {
        int left = 0;
        int right = num.length - 1;
        if (num[left] < num[right]) return num[0]; // check whether rotated
        int mid = 0;
        /*binary search*/
        while (right - left > 1) { // make sure there is an ele in between 
            mid = left + (right - left) / 2;
            if (num[left] < num[mid]) { // increasing
                left = mid; 
            } else { 
                right = mid; // decreasing
            }
        }
        return num[right]; // return the element at right
    }
    
}