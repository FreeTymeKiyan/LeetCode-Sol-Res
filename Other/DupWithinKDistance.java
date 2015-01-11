import java.util.*;

/**
 * Given an unsorted array that may contain duplicates. Also given a number k
 * which is smaller than size of array. Write a function that returns true if
 * array contains duplicates within k distance.
 * 
 * Examples:
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 4, 1, 2, 3, 4}
 * Output: false
 * All duplicates are more than k distance away.
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 1, 4, 5}
 * Output: true
 * 1 is repeated at distance 3.
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 4, 5}
 * Output: false
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 4, 4}
 * Output: true
 * 
 * Tags: Array, Hashtable
 */
class DupWithinKDistance {
    public static void main(String[] args) {
        int arr[] = {10, 5, 3, 4, 3, 5, 6};
        System.out.println(checkDuplicatesWithinK(arr, 3));
    }
    
    /**
     * O(n) Time, O(n) Space
     * Use Set to store elements within k
     * Go through the array
     * If not in map, put it and its index in map
     * If in map, return true
     * Otherwise, add arr[i] to map and remove arr[i - k]
     */
    public static boolean checkDuplicatesWithinK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length < k) return false;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) return true;
            set.add(arr[i]);
            if (i >= k) set.remove(arr[i - k]);
        }
        return false;
    }
    
    /**
     * Simple Solution, 2 loops, O(kn)
     */
    public boolean checkDupWithinKB(int[] arr, int k) {
        return false;
    }
}