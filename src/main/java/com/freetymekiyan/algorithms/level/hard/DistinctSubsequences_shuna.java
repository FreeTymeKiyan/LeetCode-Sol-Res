package com.freetymekiyan.algorithms.level.hard;

/**
* Distinct Subsequences
* Given a string S and a string T, count the number of distinct subsequences of T in S.
* A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
* Here is an example:
* S = "rabbbit", T = "rabbit" Return 3.
* Tags: Dynamic Programming, String
*
* Note : DP
* @author chenshuna
*/

class DistinctSubsequences_shuna {
    public static int numDistinct(String s, String t) {
        int row = s.length() + 1;
        int col = t.length() + 1;
        if(row == 1){
            return 0;
        }
        if(col == 1){
            return 1;
        }
        int[][] dp = new int[row][col]; 
        dp[0][0] = 1;
        for(int i = 1; i < row; i++){
            dp[i][0] = 1;
            for(int j = 1; j < col; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                else
                    dp[i][j] = dp[i -1][j];
            }
        }
        return dp[row - 1][col - 1];
    }
    
    public static void main(String[] args) {
        System.out.print(numDistinct("rabbbit", "rabbit"));
    }
}