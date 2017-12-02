package com.freetymekiyan.algorithms.level.medium;

/**
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer
 * given in the form of an array.
 * <p>
 * Example1:
 * <p>
 * a = 2
 * b = [3]
 * <p>
 * Result: 8
 * Example2:
 * <p>
 * a = 2
 * b = [1,0]
 * <p>
 * Result: 1024
 * <p>
 * Tags: Math
 * Similar Problems: (M) Pow(x, n)
 */
public class SuperPow {


    public static final int BASE = 1337;

    /**
     * Math.
     * ab % k = (a%k)(b%k)%k.
     * For example, if a = 2, b = [1, 1]
     * 2^11 % 1337 = (2^10 % 1337) * (2 % 1337) % 1337
     * Then 2^10 can be further decomposed into (2 % 1337)^10 % 1337
     * https://discuss.leetcode.com/topic/50489/c-clean-and-short-solution
     */
    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length);
    }

    private int superPow(int a, int[] b, int length) {
        if (length == 0) {
            return 1;
        }
        int lastDigit = b[length - 1];
        length--;
        return powMod(superPow(a, b, length), 10) * powMod(a, lastDigit) % BASE;
    }

    /**
     * a^k % 1337
     * = (a % 1337)^k % 1337
     */
    private int powMod(int a, int k) {
        a %= BASE;
        int res = 1;
        for (int i = 0; i < k; i++) {
            res = (res * a) % BASE; // Avoid overflow
        }
        return res;
    }

}
