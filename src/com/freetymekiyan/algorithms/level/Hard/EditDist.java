/**
 * Given two words word1 and word2, find the minimum number of steps required
 * to convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * 
 * Tags: DP, String
 */
class EditDist {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * DP, O(nm) Time, O(nm) Space
     * Searching for a path (sequence of edits) from the start string to the
     * final string
     * For two strings, X of length n, Y of length m
     * Define D(i,j): the edit distance between X[1..i] and Y[1..j]
     * The edit distance between X and Y is thus D(n,m)
     * 
     * Initialization: D(i,0) = i, D(0,j) = j
     * 1. D(i, j) = min(D(i - 1, j) + 1, D(i, j - 1) + 1, D(i - 1, j - 1) + 0
     * or 1), 0 is X(i) = Y(j), 1 if X(i) != Y(j)
     * D(N, M) is distance 
     * 
     * Note that f[i][j] only depends on f[i-1][j-1], f[i-1][j] and f[i][j-1],
     * therefore we can reduce the space to O(n) by using only the (i-1)th
     * array and previous updated element(f[i][j-1]).
     */
    public static int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] d = new int[m + 1][n + 1];
        d[0][0] = 0;
        for (int i = 1; i < m + 1; i++) d[i][0] = i;
        for (int j = 1; j < n + 1; j++) d[0][j] = j;
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                d[i][j] = Math.min(Math.min(d[i][j - 1] + 1, d[i - 1][j] + 1), word1.charAt(i - 1) == word2.charAt(j - 1) ? d[i - 1][j - 1] : d[i - 1][j - 1] + 1);
            }
        }
        
        return d[m][n];
    }
    
    /**
     * Optimal DP. Reduce table to a row. 
     */
    public static int minDistanceOptimal(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        int[] d = new int[n + 1];
        d[0] = 0;
        for (int j = 1; j < n + 1; j++) d[j] = j;
        
        for (int i = 1; i < m + 1; i++) {
            int prev = d[0];
            d[0] += 1;
            for (int j = 1; j < n + 1; j++) {
                int temp = d[j];
                d[j] = Math.min(Math.min(d[j - 1] + 1, d[j] + 1), word1.charAt(i - 1) == word2.charAt(j - 1) ? prev : prev + 1);
                prev = temp;
            }
        }
        
        return d[n];
    }
}