/**
 * Given a string, find the minimum number of characters to be inserted to 
 * convert it to palindrome.
 * 
 * Tags: Recursive, DP
 */
class MinInsertionsToFormPalindrome {
    public static void main(String[] args) {
        String s = "geeks";
        // System.out.println(new MinInsertionsToFormPalindrome().minInsertions(s, 0, s.length() - 1));
        System.out.println(new MinInsertionsToFormPalindrome().minInsertionsDP(s));
    }
    
    /**
     * DP, bottom-up
     * Fill a table in diagonal direction
     */
    int minInsertionsDP(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int l = 0, h = gap; h < n; l++, h++) {
                dp[l][h] = s.charAt(l) == s.charAt(h) ? dp[l+1][h-1] : Math.min(dp[l][h-1], dp[l+1][h]) + 1;
            }
        }
        return dp[0][n-1];
    }
    
    /**
     * Recursive
     * Let the input string be str[l……h]. 
     * The problem can be broken down into 3 parts:
     * 1. Find the minimum # of insertions in the substring str[l+1,…….h].
     * 2. Find the minimum # of insertions in the substring str[l…….h-1].
     * 3. Find the minimum # of insertions in the substring str[l+1……h-1].
     * 
     * The minimum # of insertions in the string str[l…..h] can be given as:
     * If 
     *  str[l] is equal to str[h], minInsertions(str[l+1…..h-1]) 
     * Otherwise, 
     *  min(minInsertions(str[l…..h-1]), minInsertions(str[l+1…..h])) + 1
     */
    int minInsertions(String s, int l, int h) {
        // Base Cases
        if (l > h) return Integer.MAX_VALUE;
        if (l == h) return 0;
        if (l == h - 1) return (s.charAt(l) == s.charAt(h)) ? 0 : 1;
        // Check if the first and last characters are same. On the basis of the
        // comparison result, decide which subrpoblem(s) to call
        return s.charAt(l) == s.charAt(h) ? minInsertions(s, l + 1, h - 1) 
            : Math.min(minInsertions(s, l, h-1), minInsertions(s, l+1, h)) + 1;
    }
}