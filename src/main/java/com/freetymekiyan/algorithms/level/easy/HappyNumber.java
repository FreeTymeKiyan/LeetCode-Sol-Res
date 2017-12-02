package com.freetymekiyan.algorithms.level.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number
 * by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it
 * loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy
 * numbers.
 *
 * Example:
 * 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * Tags: Hash Table Math
 * Similar Problems: (E) addRecursive Digits (E) Ugly Number
 */
public class HappyNumber {

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        for (int i = 10; i < 20; i++) {
            System.out.println(i + ": " + hn.isHappy(i));
            System.out.println(i + ": " + hn.isHappy2(i));
        }
    }

    /**
     * loop detection like linked list
     */
    public boolean isHappy(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(digitSquareSum(fast));
        } while (slow != fast);
        return slow == 1;
    }

    /**
     * loop detection using Set, use more space
     */
    public boolean isHappy2(int n) {
        if (n < 1) return false;

        int num = n;
        Set<Integer> results = new HashSet<>();
        while (!results.contains(num)) {
            results.add(num);
            num = digitSquareSum(num);
        }
        
        return num == 1;
    }

    public int digitSquareSum(int n) {
        int res = 0;
        int digit;
        for (; n > 0; n /= 10) {
            digit = n % 10;
            res += digit * digit;
        }
        return res;
    }
}
