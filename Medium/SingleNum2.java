import java.util.*;

/**
 * Given an array of integers, every element appears three times except for
 * one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * 
 * Tags: Bit Manipulation
 */
class SingleNum2 {
    
    public static void main(String[] args) {
        int[] A = {1, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7};
        System.out.println(singleNum(A));
    }
    
    /**
     * Use ones to store those nums only appeared once
     * twos to store those nums appeared twice
     */
    public static int singleNum(int[] A) {
        int ones = 0, twos = 0;
        for (int i = 0; i < A.length; i++) {
            ones = (ones ^ A[i]) & ~twos; // in ones not in twos
            twos = (twos ^ A[i]) & ~ones; // in twos not in ones
        }
        return ones; // only appeared once
    }
}
