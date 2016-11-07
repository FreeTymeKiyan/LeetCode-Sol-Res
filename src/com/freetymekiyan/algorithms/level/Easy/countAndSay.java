package com.freetymekiyan.algorithms.level.easy;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * <p>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * <p>
 * Note: The sequence of integers will be represented as a string.
 * <p>
 * Company Tags: Facebook
 * Tags: String
 * Similar Problems: (M) Encode and Decode Strings
 */

class CountAndSay {

    /**
     * String. Simulate the process.
     * Generate the next sequence.
     * Repeat n-1 times.
     */
    public String countAndSay(int n) {
        String res = "1";
        while (--n > 0) {
            StringBuilder seq = new StringBuilder();
            char[] prev = res.toCharArray();
            for (int i = 0; i < prev.length; i++) {
                int count = 1; // Get count of same characters.
                while (i + 1 < prev.length && prev[i] == prev[i + 1]) {
                    count++;
                    i++;
                }
                seq.append(count).append(prev[i]);
            }
            res = seq.toString();
        }
        return res;
    }
}