/**
 * Find duplicates in O(n) time and O(1) extra space
 * 
 * Given an array of n elements which contains elements from 0 to n-1, with any
 * of these numbers appearing any number of times. Find these repeating numbers
 * in O(n) and using only constant memory space.
 * 
 * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer
 * should be 1, 3 and 6.
 * 
 * Tags: Array
 */
class FindDup {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 1, 3, 6, 6};
        new FindDup().printRepeating(num);
        
    }
    
    /**
     * Check the sign of A[abs(A[i])]
     * if   positive then make it negative by A[abs(A[i])] = -A[abs(A[i])]
     * else this element (ith element of list) is a duplication
     */
    public void printRepeating(int[] num) {
        if (num == null || num.length == 0) return;
        for (int i = 0; i < num.length; i++) {
            if (num[Math.abs(num[i])] < 0) { // duplicate
                System.out.println(Math.abs(num[i])); // print dups
            } else { // set flag
                num[Math.abs(num[i])] = -num[Math.abs(num[i])]; // mark
            }
        }
    }
}
