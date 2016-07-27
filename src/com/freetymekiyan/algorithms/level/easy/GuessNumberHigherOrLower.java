package com.freetymekiyan.algorithms.level.easy;

import java.util.Random;

/**
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * <p>
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * <p>
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 * <p>
 * Return 6.
 * <p>
 * Tags: Binary Search
 * Similar Problems: (E) First Bad Version (M) Guess Number Higher or Lower II
 */
public class GuessNumberHigherOrLower {

    private int k;

    public int guessNumber(int n) {
        k = new Random().nextInt(n); // Randomly generate a number to guess
        int l = 1;
        int h = n;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int res = guess(m);
            if (res == 0) {
                return m;
            } else if (res > 0) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l;
    }

    /**
     * The guess API is defined in the parent class GuessGame.
     *
     * @param num, your guess
     * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
     * int guess(int num);
     */
    private int guess(int num) {
        if (num == k) {
            return 0;
        } else if (num < k) {
            return 1;
        }
        return -1;
    }
}
