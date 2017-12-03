package com.freetymekiyan.algorithms.other;

/**
 * Given a program, remove comments from it.
 * <p>
 * The idea is to maintain two flag variables, one to indicate that a single line comment is started, another to
 * indicate that a multiline comment is started. When a flag is set, we look for the end of comment and ignore all
 * characters between start and end.
 */
public class RemoveCommentsFromCode {

    /**
     * Two kinds of comments: single-line and multi-line.
     * Create two boolean flags for them.
     * For i from 0 to code.length() - 1:
     * | If hasSingle && reach the end of line:
     * |   Set hasSingle to false.
     * | If hasMulti && found the ending:
     * |   Set hasMulti to false.
     * |   i++.
     * | If hasSingle || hasMulti, in comments:
     * |   Just continue to ignore the characters.
     * | If the start of single is found:
     * |   hasSingle = true. i++.
     * | If the start of multi is found:
     * |   hasMulti = true. i++
     * | Else:
     * |   Not comment. Append to result.
     * Return result.
     */
    public String removeComments(String code) {
        int n = code.length();
        StringBuilder res = new StringBuilder();
        // Flags to indicate that single line and multpile line comments
        // have started or not.
        boolean hasSingle = false;
        boolean hasMulti = false;
        // Traverse the given program
        for (int i = 0; i < n; i++) {
            // If single line comment flag is on, then check for end of it
            if (hasSingle && code.charAt(i) == '\n') {
                hasSingle = false;
            } else if (hasMulti && code.charAt(i) == '*' && code.charAt(i + 1) == '/') {
                // If multiple line comment is on, then check for end of it
                hasMulti = false;
                i++;
            } else if (hasSingle || hasMulti) {
                // If this character is in a comment, ignore it
                continue;
            } else if (code.charAt(i) == '/' && code.charAt(i + 1) == '/') {
                // Check for beginning of comments and set the appropriate flags
                hasSingle = true;
                i++;
            } else if (code.charAt(i) == '/' && code.charAt(i + 1) == '*') {
                hasMulti = true;
                i++;
            } else {
                // If current character is a non-comment character, append it to res
                res.append(code.charAt(i));
            }
        }
        return res.toString();
    }

}
