package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification
 * <p>
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
     * | Init a length as -1, since the first word doesn't have a space.
     * | Start from i, add word length + 1 to length as long as w is still within array and length within maxWidth.
     * Then append the words and generate line.
     * Start from the first word.
     * | Append the first word, since it doesn't have space before it. Other words do.
     * | Calculate number of spaces and extra spaces.
     * |    space -> 1, at least one space, by default.
     * |    extra -> 0,
     * |    If there is more than 1 word, and w != words.length, meaning it's not the last line
     * |      space = remain total spaces / intervals between words + 1(at least 1 space)
     * |      extra = remain total spaces % intervals between words
     * Append the rest of the words. The first one is appended already.
     * | Append spaces and extra spaces first. Then append the word.
     * Deal with padding spaces.
     * | There can be only one word in a line where spaces are not insert in the steps before.
     * Add current line to result.
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        for (int i = 0, j; i < words.length; i = j) {
            int len = -1;
            for (j = i; j < words.length && len + 1 + words[j].length() <= maxWidth; j++) {
                len += (1 + words[j].length());
            }

            StringBuilder line = new StringBuilder();
            line.append(words[i]); // First word doesn't have prepending space. Append it first.
            int spaces = 1; // Left justified.
            int extra = 0;
            if (j != i + 1 && j < words.length) { // Fully justified.
                int totalSpaces = maxWidth - len;
                int intervals = j - i - 1; // Intervals can be zero when j = i + 1, only 1 word.
                spaces = totalSpaces / intervals + 1; // Adding 1 to include default space.
                extra = totalSpaces % intervals;
            }
            for (int k = i + 1; k < j; k++) {
                for (int s = spaces; s > 0; s--) line.append(' ');
                if (extra > 0) line.append(' ');
                extra--;
                line.append(words[k]);
            }
            for (int r = maxWidth - line.length(); r > 0; r--) { // Add remaining spaces to the end of line.
                line.append(' ');
            }
            lines.add(line.toString());
        }
        return lines;
    }
}