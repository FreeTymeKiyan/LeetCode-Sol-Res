package com.freetymekiyan.algorithms.level.easy;

/**
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Company Tags: Microsoft, Bloomberg, Uber, Facebook, Yahoo
 * Tags: Math, String
 * Similar Problems: (M) Integer to Roman
 */
public class RomanToInteger {

    char[] roman = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    int[] val = {1, 5, 10, 50, 100, 500, 1000};

    /**
     * String, Math.
     * First need to know the letter to value mapping.
     * Then need to clarify whether the input string can mean negative, or there is only uppercase.
     * <p>
     * For each character from the end to front:
     * | Add the value to result according to the character.
     * | Only when for C=100 X=10 I=1, need to compare current number with 500, 50 and 5.
     * | If result is larger or equal, they mean negative values.
     * | Subtract them from result.
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'M':
                    res += 1000;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'C':
                    res += 100 * (res >= 500 ? -1 : 1); // For CD or CM.
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'X':
                    res += 10 * (res >= 50 ? -1 : 1); // For XL or XC.
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'I':
                    res += (res >= 5 ? -1 : 1); // For IV or IX.
                    break;
                default:
                    break;
            }
        }
        return res;
    }
}
