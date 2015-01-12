import java.util.*;

/**
 * Given an array of integers, every element appears twice except for one. Find
 * that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * 
 * Tags: Hashtable, Bit Manipulation
 */
class SingleNum {
    
    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};
        System.out.println(singleNum(A));
        System.out.println(singleNumNoSpace(A));
    }
    
    /**
     * Without extra space
     * XOR of two equal numbers is 0 : a^a=0. This is the main idea of the
     * algorithm.
     */
    public static int singleNumNoSpace(int[] A) {
        int res = 0;
        for (int i = 0; i < A.length; i++) res ^= A[i];
        return res;
    }
    
    /**
     * hashtable, store the value and remove when appears second time
     * the only number left is the one
     */
    public static int singleNum(int[] A) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (!map.containsKey(A[i])) map.put(A[i], 1);
            else map.remove(A[i]);
        }
        int res = 0;
        for (Integer key : map.keySet()) res = key;
        return res;
    }
}
