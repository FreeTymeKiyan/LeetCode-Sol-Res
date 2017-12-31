package com.freetymekiyan.algorithms.level.easy;

/**
 * 168. Excel Sheet Column Title
 * <p>
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * <p>
 * Company Tags: Microsoft, Facebook, Zenefits
 * Tags: Math
 * Similar Problems: (E) Excel Sheet Column Number
 */
public class ExcelSheetColumnTitle {

    /**
     * Iterative.
     * Map 0 to A, 1 to B, ..., 25 to Z.
     * So we need to decrease n by 1 first.
     * For each iteration, reduce n by 1.
     * Convert n to a character and add to result.
     * Then n / 26 to get the number for next iteration.
     * Stop when n is 0.
     */
    public static String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            n--; // Offset. Map 0 to A, 1 to B .. 25 to Z instead.
            res.insert(0, (char) ('A' + n % 26)); // Insert one character.
            n /= 26; // Get next number.
        }
        return res.toString();
    }

    /**
     * Recursive.
     * Recurrence relation:
     * Convert the rest of the characters + Convert current char.
     * Base case:
     * n <= 0, return abn empty string.
     */
    public String convertToTitle2(int n) {
        return n <= 0 ? "" : convertToTitle2(--n / 26) + (char) ('A' + (n % 26));
    }

    /**
     * Recursive.
     * More readable.
     */
    public String convertToTitle3(int n) {
        if (n <= 0) return "";
        n--;
        char c = (char) ('A' + (n % 26));
        return convertToTitle3(n / 26) + c;
    }
}