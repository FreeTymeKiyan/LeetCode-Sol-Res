/**
 * Given a string S and a string T, count the number of distinct subsequences
 * of T in S.
 * 
 * Explain: the number of distinct subsequences of S equal to T
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 
 * Tags: DP, String
 */
class DistinctSubsequences {
    public static void main(String[] args) {
        
    }
    
    /**
     * DP, 2d array as table, Time O(mn), Space O(mn)
     * We keep a m*n matrix and scanning through string S, while
     * m = T.length() + 1 and n = S.length() + 1
     * and each cell in matrix dp[i][j] means the number of distinct
     * subsequences of T.substr(1...i) in S(1...j)
     * 
     * Initialization, dp[0][0] = 1, 
     * dp[0][j] = 1, means T is empty, and there is always 1 substring
     * and dp[i][0] = 0, means S is empty
     * 
     * dp[i][j] = dp[i][j-1]        (from S[1...j - 1] no S[j])
     *           + (dp[i-1][j-1]    (S[j] == T[i] and we are going to use S[j])
     *               or 0)          (S[j] != T[i] so we could not use S[j])
     */
    public int numDistinct(String s, String t) {
        if (s == null || t == null) return 0;
        int m = t.length();
        int n = s.length();
        if (m > n) return 0;
        
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) dp[0][i] = 1;
        
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                dp[i][j] = dp[i][j - 1] + (t.charAt(i - 1) == s.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
        
        return dp[m][n];
    }
    
    /**
     * Space optimized, 1D array, build row by row
     */
    public int numDistinctOptimal(String s, String t) {
        if (s == null || t == null) return 0;
        int m = t.length();
        int n = s.length();
        if (m > n) return 0;
        
        int[] dp = new int[m + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++)
            for (int j = m; j >= 1; j--)
                // same: path[i] = path[i] + (T[i-1] == S[j-1] ? path[i-1] : 0);
                if (t.charAt(j - 1) == s.charAt(i - 1)) dp[j] += dp[j - 1];
        return dp[m];
    }
}