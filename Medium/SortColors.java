/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 * Tags: Array, Two Pointers, Sort
 */
class SortColors {
    public static void main(String[] args) {
        // normal case
        int[] A = {0, 1, 0, 1, 2, 1, 0};
        // other test cases
        // int[] A = {1, 2, 0};
        // int[] A = {2};
        // int[] A = {2, 2};
        sortColors(A);
    }
    
    /**
     * One-pass count sorting
     * Remember the count of red, and count of red + white
     */
    public static void onePassSortColors(int[] A) {
        int i = -1; // red count
        int j = -1; // red + white count
        
        for (int k = 0; k < A.length; k++) {
            int v = A[k];
            A[k] = 2;
            if (v == 0) {
                A[++j] = 1;
                A[++i] = 0;
            } else if (v == 1) A[++j] = 1;
        }
    }

    /**
     * two-pass count sorting
     */
    public static void sortColors(int[] A) {
        int red   = 0;
        int white = 0;
        int blue  = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                red++;
            } else if (A[i] == 1) {
                white++;
            } else {
                blue++;
            }
        }
        int i = 0;
        while (i < red) {
            A[i++] = 0;
        }
        while (i < white + red) {
            A[i++] = 1;
        }
        while (i < white + red + blue) {
            A[i++] = 2;
        }
        
        for (int k = 0; k < A.length; k++) {
            System.out.println(A[k]);
        }
    }
}
