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
     * divide into subgroups and locate it
     */
    public static String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        return helper(nums, n, k - 1); // note it's k - 1 here, start from 0
    }
    
    public static String helper(ArrayList<Integer> nums, int n, int k) {
        if (1 == n) return nums.get(0).toString();
        int index = k / factorial(n - 1);
        String ch = nums.get(index).toString();
        nums.remove(index);
        k = k % factorial(n - 1);
        return ch + helper(nums, n - 1, k);
    }
    
    private static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1); // can be dp
    }
}
