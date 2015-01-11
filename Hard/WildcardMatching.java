/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 *
 * My own examples:
 * isMatch("aab", "a*a*b") → true
 * isMatch("a", "a*") → true
 *
 * Tags: DP, Backtracking, Greedy, String
 */
class WildcardMatching {
    public static void main(String[] args) {
        /*input validation*/
        // System.out.println(isMatch("", "a")); // false
        // System.out.println(isMatch("a", "")); // false
        // System.out.println(isMatch("", "")); // true
        // System.out.println(isMatch(null, null)); // true
        // System.out.println(isMatch("a", null)); // false
        // System.out.println(isMatch(null, "null")); // false
        /*letters*/
        // System.out.println(isMatch("a", "aa")); // false
        // System.out.println(isMatch("aa", "a")); // false
        // System.out.println(isMatch("aa", "aa")); // true
        // System.out.println(isMatch("aa", "ab")); // false
        /**s*/
        // System.out.println(isMatch("aa", "*")); // true
        /*?s*/
        // System.out.println(isMatch("aa", "?")); // false
        // System.out.println(isMatch("a", "?")); // true
        /*letters and *s*/
        // System.out.println(isMatch("abc", "a*")); // true
        // System.out.println(isMatch("ab", "a*")); // true
        // System.out.println(isMatch("ab", "*a")); // false
        // System.out.println(isMatch("a", "a*")); // true
        // System.out.println(isMatch("bcsa", "*a")); // true
        // System.out.println(isMatch("bcs", "*a")); // false
        // System.out.println(isMatch("bbbbbbbbbb", "*bbbbb")); // true

        /* * and ? */
        // System.out.println(isMatch("b", "*?*?")); // false
    }

    /**
     * Two pointers
     * remember the index of * and matched sequence
     * advance only pattern pointer when * is found
     * match the sequence after * in pattern with the rest of the string
     */
    public static boolean isMatch(String str, String pattern) {
        if (str == null && pattern == null) return true;
        if (str == null || pattern == null) return false;
        int s = 0, p = 0, match = 0, astroIdx = -1;
        while (s < str.length()){
            // move both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only move pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                astroIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, move string pointer
            else if (astroIdx != -1){
                p = astroIdx + 1; // move to next of *
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') p++;
        return p == pattern.length();
    }

    /**
     * validate input
     * seperate letter, * and ?
     * how can be a match? letters only, * only, ? only
     * letters and *, letters and ?, * and ?
     * letters, * and ?
     * check current char, return the result of current and latter ones
     */
    public static boolean isMatch(String s, String p) {
        // System.out.println(s + " | " + p);
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        if (s.equals("") || p.equals("")) return hasAstro || p.equals("*") || p.equals(s);
        for (int i = 0; i < p.length(); i++) {
            char fromP = p.charAt(i);
            char fromS = s.charAt(i);
            if (fromP == '*') { // problem here with astroid
                return isMatch(s, p.substring(i + 1));
            } else if (fromP == '?'|| fromP == fromS) {
                return isMatch(s.substring(i + 1), p.substring(i + 1));
            } else {
                return false;
            }
        }
        return true;
    }
    
}
