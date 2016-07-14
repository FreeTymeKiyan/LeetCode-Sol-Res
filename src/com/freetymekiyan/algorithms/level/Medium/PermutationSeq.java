import java.util.*;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * Tags: Backtracking, Math
 */
class PermutationSeq {
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 1)); // 123
        System.out.println(getPermutation(3, 2)); // 132
        System.out.println(getPermutation(3, 3)); // 213
        System.out.println(getPermutation(3, 4)); // 231
        System.out.println(getPermutation(3, 5)); // 312
        System.out.println(getPermutation(3, 6)); // 321
    }
    
    /**
     * Initialize a list of digits to build the result
     * Build from first character 
     */
    public static String getPermutation(int n, int k) {
        k = k - 1;
        int factor = 1;
        for (int i = 1; i < n; i++) factor *= i;
        List<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) digits.add(i + 1);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int index = k / factor;
            sb.append(digits.get(index));
            digits.remove(index); // remove used digit
            k = k % factor;
            if (i < n - 1) factor = factor / (n - 1 - i);
        }

        return sb.toString();
    }
    
    /**
     * Divide into subgroups and locate it
     */
    public static String getPermutationB(int n, int k) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) nums.add(i + 1);
        return helper(nums, n, k - 1); // note it's k - 1 here, start from 0
    }
    
    /**
     * Get relative digit from list
     * First digit's index in list is k / factorial(n-1)
     * Get the digit, remove that digit from list and update k
     * Concatenate digit with following digits
     */
    public static String helper(ArrayList<Integer> nums, int n, int k) {
        if (n == 1) return nums.get(0).toString();
        int index = k / factorial(n - 1);
        String digit = nums.get(index).toString();
        nums.remove(index);
        k = k % factorial(n - 1);
        return digit + helper(nums, n - 1, k);
    }
    
    private static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1); // can be dp
    }
}
