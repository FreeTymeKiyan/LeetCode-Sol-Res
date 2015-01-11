/**
 * Given a string s1, we may represent it as a binary tree by partitioning it
 * to two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two
 * children.
 * 
 * For example, if we choose the node "gr" and swap its two children, it
 * produces a scrambled string "rgeat".
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it
 * produces a scrambled string "rgtae".
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a
 * scrambled string of s1.
 * 
 * Tags: DP, String
 */
class ScrambleStr {
    public static void main(String[] args) {
        
    }
    
    /**
     * DP
     * f[n][i][j] means isScramble(s1[i: i+n], s2[j: j+n])
     * f[n][i][j] = f[k][i][j] && f[n - k][i+k][j+k] 
     *                 || f[k][i][j+n-k] && f[n-k][i+k][j]
     */
    public boolean isScramble(String s1, String s2) {
       if (s1.length() != s2.length()){ return false;
       if (s1.length() == 0 || s1.equals(s2)) return true;
       
       int n = s1.length();
       boolean[][][] res = new boolean[n][n][n];
       for (int i = 0; i < n; i++)
           for (int j = 0; j < n; j++)
               res[0][i][j] = s1.charAt(i) == s2.charAt(j);
       
       for (int len = 2; len <= n; len++) {
           for (int i = n - len; i>= 0; i--) {
               for (int j = n - len;  j>=0; j--) {
                   boolean r = false;
                   for (int k = 1; k < len && r == false; k++) {
                       r = (res[k-1][i][j] && res[len-k-1][i+k][j+k]) || (res[k-1][i][j+len-k] && res[len-k-1][i+k][j]);
                   }
                   res[len-1][i][j] = r;
               }
           }
       }       
       return res[n-1][0][0];
    }
    
    /**
     * separate s1 into two parts, namely --s11--, --------s12--------
     * separate s2 into two parts, namely --s21--, --------s22--------, and
     * test the corresponding part (s11 and s21 && s12 and s22) with isScramble.
     * separate s2 into two parts, namely --------s23--------, --s24--, and
     * test the corresponding part (s11 and s24 && s12 and s23) with isScramble.
     * 
     * Note that before testing each sub-part with isScramble, anagram is used
     * first to test if the corresponding parts are anagrams. If not, skip
     * directly.
     */
    static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        /*check anagram*/
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if (!Arrays.equals(c1, c2)) return false; // not anagram, can't be scramble
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) return true;
        }
        return false; // didn't pass the test
    }
    
}