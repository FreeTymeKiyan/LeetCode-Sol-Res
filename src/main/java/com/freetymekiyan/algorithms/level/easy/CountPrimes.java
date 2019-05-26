package com.freetymekiyan.algorithms.level.easy;

/**
 * 204. Count Primes
 * <p>
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * <p>
 * Companies: Microsoft, Amazon, Adobe, Google, Apple, Yahoo, Goldman Sachs, Nvidia, Tesla, Tencent, Bloomberg, Capital
 * One
 * <p>
 * Related Topics: Hash Table, Math
 * <p>
 * Similar Questions: (E) Ugly Number, (M) Ugly Number II, (M) Perfect Squares
 */
public class CountPrimes {

  public int countPrimes(int n) {
    if (n <= 2) return 0;

    int res = n >> 1;
    int m = (int) Math.sqrt(n - 1);
    boolean[] notPrime = new boolean[n];

    for (int i = 3; i <= m; i += 2) {
      if (!notPrime[i]) {
        for (int j = i * i, step = i << 1; j < n; j += step) {
          if (!notPrime[j]) {
            notPrime[j] = true;
            --res;
          }
        }
      }
    }

    return res;
  }
}