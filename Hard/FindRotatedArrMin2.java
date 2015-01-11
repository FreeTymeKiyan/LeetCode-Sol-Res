/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * The array may contain duplicates.
 * 
 * Tags: Array, Binary Search
 */
class FindRotatedArrMin2 {
    public static void main(String[] args) {
        // int[] num = { 2, 3, 3, 4, 5, 6, 7, 0, 0, 0, 1, 1, 2, 2, 2 };
        // int[] num = { 3, 3, 1 };
        int[] num = { 10, 1, 10, 10, 10 };
        System.out.println(findMin(num));
    }
    
    /**
     * skip all the indentical on the left elements
     */
    static int findMin(int[] num) {
        return binarysearch(num, 0, num.length - 1);
    }
    
    static int binarysearch(int [] num, int l, int r) {
        int k = l;
        // move cursor to the first element that is not the same with the last one
        while (k <= r && num[k] == num[r]) {
            k++;
        }
        if (k > r) {
            return num[l];
        }
        l = k;
        if (num[l] < num[r]) {
            return num[l];
        }
        int mid = (l + r) / 2;
        if (num[mid] >= num[l]) {
            return binarysearch(num, mid + 1, r);
        } else {
            return binarysearch(num, l, mid);
        }
    }
}
