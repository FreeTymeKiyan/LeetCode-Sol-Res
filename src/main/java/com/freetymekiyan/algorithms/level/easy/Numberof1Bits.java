package com.freetymekiyan.algorithms.level.easy;

/**
 * Number of 1 Bits
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 * Tags Bit Manipulation
 * Similar Problems: (E) Reverse Bits (E) Power of Two (M) Counting Bits
 * @author chenshuna
 */

public class Numberof1Bits {
    public static int hammingWeight(int n) {
        int res = 0;
        while(n != 0){
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print(hammingWeight(13));
    }
}