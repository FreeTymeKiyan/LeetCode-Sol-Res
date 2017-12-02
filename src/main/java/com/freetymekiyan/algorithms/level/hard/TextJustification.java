package com.freetymekiyan.algorithms.level.hard;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully
 * (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
 * spaces ' ' when necessary so that each line has exactly L characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * <p>
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * <p>
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * <p>
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 * <p>
 * Company Tags: LinkedIn, Airbnb, Facebook
 * Tags: String
 */
public class TextJustification {

    /**
     * String.
     * First figure out how many words to fit current line.
     * | Init a length as -1, to exclude the last space.
     * | Start from i, add word length + 1 to length as long as w is still within array and length within maxWidth.
     * Then append the words and generate line.
     * Start from the first word.
     * | Append the first word.
     * | Calculate number of spaces and extra spaces.
     * |    space -> 1, at least one space
     * |    extra -> 0,
     * |    If w moved and w != words.length, meaning not the last line
     * |      space = remain length / intervals between words + 1(at least 1 space)
     * |      extra = remain length % intervals between words
     * Append the rest of the words. The first one is appended already.
     * | Append spaces and extra spaces first. Then append word.
     * Deal with padding spaces if it is the last line.
     * | If maxWidth > current line length, it's the last line.
     * |   Pad spaces at the end.
     * Add current line to res.
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        for (int i = 0, w; i < words.length; i = w) {
            /* Find the number of words to fit this line */
            int len = -1; // Length of current line. Init as -1 to remove last space.
            for (w = i; w < words.length && len + words[w].length() + 1 <= maxWidth; w++) {
                len += words[w].length() + 1; // 1 is an extra space in between words.
            }

            StringBuilder line = new StringBuilder(words[i]); // Append first word.
            /* Calculate number of spaces, number of extra in for each space */
            int space = 1; // Number of interval between words. # of words - 1.
            int extra = 0; // Extra spaces.
            if (w != i + 1 && w != words.length) { // w moved and not last line.
                space = (maxWidth - len) / (w - 1 - i) + 1; // w - i - 1 is actually (w - 1) - i + 1 - 1.
                extra = (maxWidth - len) % (w - 1 - i); // Extra is the modular.
            }
            /* Append the rest of the words */
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) { // Append space first.
                    line.append(' ');
                }
                if (extra-- > 0) {
                    line.append(' ');
                }
                line.append(words[j]); // Append the word.
            }
            /* Deal with last line's padding spaces */
            int remain = maxWidth - line.length(); // If not last line, remain will be 0.
            while (remain-- > 0) { // Last line.
                line.append(' ');
            }
            res.add(line.toString());
        }
        return res;
    }

    @Test
    public void testExamples() {
        fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        fullJustify(new String[]{""}, 2);
    }
}