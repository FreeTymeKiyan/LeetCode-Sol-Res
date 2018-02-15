package com.freetymekiyan.algorithms.level.easy;

/**
 * 246. Strobogrammatic Number
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 * <p>
 * Companies: Google
 * Related Topics: Hash Table, Math
 * Similar Questions: (M) Strobogrammatic Number II, (H) Strobogrammatic Number III
 */
public class StrobogrammaticNumber {

    /**
     * Math.
     * Take a look at all digits from 0 to 9.
     * 0,1,8 are strobogrammatic no matter what.
     * 6 and 9 can form a strobogrammatic pair, which means they must be center symmetrical.
     * Other digits just cannot be strobogrammatic.
     */
    public boolean isStrobogrammatic(String num) {
        int len = num.length() / 2;
        for (int i = 0; i < len; i++) {
            char d = num.charAt(i);
            if (d == '0' || d == '1' || d == '8') {
                continue;
            } else if (d == '6') {
                if (num.charAt(num.length() - 1 - i) != '9') return false;
            } else if (d == '9') {
                if (num.charAt(num.length() - 1 - i) != '6') return false;
            } else {
                return false;
            }
        }
        return true;
    }
}