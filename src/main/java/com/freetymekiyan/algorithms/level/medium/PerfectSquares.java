package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum
 * to n.
 * <p>
 * For example,
 * given n = 12, return 3 because 12 = 4 + 4 + 4;
 * given n = 13, return 2 because 13 = 4 + 9.
 * <p>
 * Tags: Dynamic Programming, Breadth-first Search, Math
 * Similar Problems: (E) Count Primes, (M) Ugly Number II
 */
public class PerfectSquares {

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        int res = ps.numSquares1(13);
        System.out.println("res: " + res);
        res = ps.numSquares2(13);
        System.out.println("res: " + res);
    }

    /**
     * A natural number is...
     * <p>
     * 1. a square if and only if each prime factor occurs to an even power in the number's prime factorization.
     * 2. a sum of two squares if and only if each prime factor that's 3 modulo 4 occurs to an even power in the
     * number's prime factorization.
     * 3. a sum of three squares if and only if it's not of the form 4a(8b+7) with integers a and b.
     * 4. a sum of four squares. Period. No condition. You never need more than four.
     */
    public int numSquares1(int n) {
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        for (int a = 0; a * a <= n; a++) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return 1 + (a > 0 ? 1 : 0);
            }
        }
        return 3;
    }

    /**
     * DP, bottom-up
     */
    public int numSquares2(int n) {
        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                res[i] = Math.min(res[i], res[i - j * j] + 1);
            }
        }
        return res[n];
    }
}
