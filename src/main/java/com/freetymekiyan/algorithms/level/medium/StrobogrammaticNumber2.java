package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 247. Strobogrammatic Number II
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Find all strobogrammatic numbers that are of length = n.
 * <p>
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 * <p>
 * Companies: Google
 * Related Topics: Math, Recursion
 * Similar Questions: (E) Strobogrammatic Number, (H) Strobogrammatic Number III
 */
public class StrobogrammaticNumber2 {

    private static final Map<Integer, Integer> PAIRS = Map.of(0, 0, 1, 1, 8, 8, 6, 9, 9, 6);

    /**
     * Recursive.
     * 0, 1, 8 are absolutely strobogrammatic. 6, 9 form a pair.
     * When given n > 2, 0 cannot be put at the outside most position.
     * Recurrence relation:
     * number of length n contains 2 parts: 1. The outside pair. 2. The inside number of length n - 2.
     * Adding each possible pairs to the inside numbers, number of length n can be derived.
     * Whether {0,0} can be used is distinguished by a boolean, indicating whether it's the outside most.
     * Base case:
     * When n is 1, return 0, 1, 8.
     * When n is 0, return an empty string.
     */
    public List<String> findStrobogrammatic(int n) {
        return findStrobogrammatic(n, true);
    }

    private List<String> findStrobogrammatic(int n, boolean isOutside) {
        if (n == 0) {
            return List.of("");
        }
        if (n == 1) {
            return List.of("0", "1", "8");
        }
        List<String> innerList = findStrobogrammatic(n - 2, false);
        List<String> result = new ArrayList<>();
        for (String s : innerList) {
            for (Map.Entry<Integer, Integer> e : PAIRS.entrySet()) {
                if (isOutside && e.getKey() == 0) {
                    continue;
                }
                result.add(e.getKey() + s + e.getValue());
            }
        }
        return result;
    }

    /**
     * Recursive.
     * Build the number from outside to inside.
     * Each position can have multiple choices.
     * A few edge cases:
     * 1. The first digit cannot be 0, unless it's single digit (i.e. n = 1).
     * 2. 6 and 9 can only be used as a pair.
     * Add the built number to the result when all digits are filled.
     */
    public List<String> findStrobogrammatic2(int n) {
        List<String> result = new ArrayList<>();
        dfs(n, 0, new char[n], result);
        return result;
    }

    public void dfs(int n, int left, char[] number, List<String> result) {
        if (left > n - 1 - left) {
            String s = new String(number);
            result.add(s);
            return;
        }
        if (left != 0 || n == 1) {
            number[left] = '0';
            number[n - 1 - left] = '0';
            dfs(n, left + 1, number, result);
        }
        number[left] = '1';
        number[n - 1 - left] = '1';
        dfs(n, left + 1, number, result);
        number[left] = '8';
        number[n - 1 - left] = '8';
        dfs(n, left + 1, number, result);
        if (left != n - 1 - left) {
            number[left] = '6';
            number[n - 1 - left] = '9';
            dfs(n, left + 1, number, result);
            number[left] = '9';
            number[n - 1 - left] = '6';
            dfs(n, left + 1, number, result);
        }
    }
}