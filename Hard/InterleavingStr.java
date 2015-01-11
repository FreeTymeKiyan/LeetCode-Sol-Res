/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * 
 * Tags: DP, String
 */
class InterleavingStr {
    public static void main(String[] args) {
        // String s1 = "aabcc";
        // String s2 = "dbbca";
        // String s3 = "aadbbcbcac";
        // String s4 = "aadbbbaccc";
        // System.out.println(isInterleave(s1, s2, s3));
        // System.out.println(isInterleave(s1, s2, s4));
        String s1 = "ab";
        String s2 = "bc";
        String s3 = "babc";
        System.out.println(isInterleave(s1, s2, s3));
        System.out.println(isInterleaveOptimal(s1, s2, s3));
    }
    
    /**
     * DP, bottom-up, Time: O(nm), and Space: O(nm)
     * quick check, length of s3 should be the sum of s1 and s2
     * 
     * 1. i == 0 && j == 0, dp[i][j] is true initially
     * 2. first row, i == 0, dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1)
     * == s3.charAt(j - 1)
     * 3. first col, j == 0, dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1)
     * == s3.charAt(i - 1)
     * 4. dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j
     * - 1))|| (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
     * 
     * final result should dp[a][b]
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        int a = s1.length();
        int b = s2.length();
        if (s3.length() != a + b) return false;
        boolean[][] dp = new boolean[a + 1][b + 1];

        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                if (i == 0 && j == 0) dp[i][j] = true;
                else if (i == 0) dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                else if (j == 0) dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                else dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[a][b];
    }
    
    /**
     * DP, space optimized, O(m)
     * space could optimize since optimal[i+1][] only depends on optimal[i][],
     */
    public static boolean isInterleaveOptimal(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        int a = s1.length();
        int b = s2.length();
        if (s3.length() != a + b) return false;
        boolean[] dp = new boolean[b + 1];
        
        /*i == 0 && j == 0*/
        dp[0] = true;
        for (int j = 0; j < b; j++) // s1 empty, s2 matches s3
            if (dp[j] && s2.charAt(j) == s3.charAt(j)) dp[j + 1] = true;

        for (int i = 0; i < a; i++) { // from 
            /*nothing from s2*/
            if (dp[0] && s1.charAt(i) == s3.charAt(i)) dp[0] = true;
            else dp[0] = false;
            for (int j = 0; j < b; j++) { // select jth char
                /*from s1 or from s2*/
                if (dp[j + 1] && (s1.charAt(i) == s3.charAt(i + j + 1)) ||
                    (dp[j] && s2.charAt(j) == s3.charAt(i + j + 1)) { // dp[j+1] means dp[i][j+1], result of last row, dp[j] means dp[i+1][j], result of this row last col
                    dp[j + 1] = true;
                } else dp[j + 1] = false;
            }
        }
        return dp[b];
    }
}
