package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an encoded string, return it's decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * <p>
 * Examples:
 * <p>
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * <p>
 * Tags: Depth-first Search, Stack
 */
public class DecodeString {

    /**
     * Stack.
     * Use two stacks to save current level information:
     * One is the # of repeat times, the other is the pattern so far.
     * For nested brackets, think about current level and the previous level.
     * When enter from previous level to current level, we save the information of previous level.
     * Both # of times to repeat and the pattern accumulated so far.
     * When current level is finished, pop count and last pattern from the stack.
     * Concatenate current level decode result with previous level.
     */
    public String decodeString(String s) {
        Deque<String> res = new ArrayDeque<>();
        Deque<Integer> count = new ArrayDeque<>();
        String decode = "";
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else if (c == '[') {
                count.push(num);
                num = 0;
                res.push(decode);
                decode = "";
            } else if (c == ']') {
                String tmp = decode;
                decode = res.pop();
                for (int j = count.pop(); j > 0; j--) {
                    decode += tmp;
                }
            } else {
                decode += c;
            }
        }
        return decode;
    }

}
