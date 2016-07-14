import java.util.*;

/**
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced
 * using 1 cut.
 * 
 * Tags: DP
 */
class PalindromePartition {
    
    public static void main(String[] args) {
        /*test palindrome*/
        // System.out.println(isPalindrome("a"));
        // System.out.println(isPalindrome("aa"));
        // System.out.println(isPalindrome("aaa"));
        // System.out.println(isPalindrome("aab"));
        // System.out.println(isPalindrome("aabb"));
        // System.out.println(isPalindrome("abba"));
        /*test minCut*/
        System.out.println(minCut("a"));
        System.out.println(minCut("aa"));
        System.out.println(minCut("aab"));
        System.out.println(minCut("aabbcc"));
        System.out.println(minCut("aabbccdd"));
        System.out.println(minCut("abcdcba"));
        System.out.println(minCut("abcd"));
    }
    
    /**
     * Each cut at i+j is calculated by scanning (i-j)'s minimum cut + 1 if
     * s[i-j, i+j] is a palindrome. 
     */
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] cuts = new int[len + 1]; // store results
        for (int i = 0; i <= len; i++) cuts[i] = i - 1; // max cuts
        for (int i = 0; i < len; i++) {
            // odd palin
            for (int j = 0; i - j >= 0 && i + j < len && s.charAt(i - j) == s.charAt(i + j); j++) cuts[i + j + 1] = Math.min(cuts[i + j + 1], 1 + cuts[i - j]);
            // even palin
            for (int j = 1; i - j + 1 >= 0 && i + j < len && s.charAt(i - j + 1) == s.charAt(i + j); j++) cuts[i + j + 1] = Math.min(cuts[i + j + 1], 1 + cuts[i - j + 1]);
        }
        return cuts[len];
    }
    
    /**
     * Calculate and maintain 2 DP states:
     * 
     * pal[i][j] , which is whether s[i..j] forms a pal
     * 
     * d[i], which is the minCut for s[i..n-1]
     * 
     * Once we comes to a pal[i][j]==true:
     * 
     * if j==n-1, the string s[i..n-1] is a Pal, minCut is 0, d[i]=0;
     * else: the current cut num (first cut s[i..j] and then cut the rest
     * s[j+1...n-1]) is 1+d[j+1], compare it to the exisiting minCut num d[i],
     * repalce if smaller.
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        boolean[][] p = new boolean[len][len];
        for (int i = 0; i < len; i++) Arrays.fill(p[i], false);
        int[] results = new int[len];
        
        for (int start = len - 1; start >= 0; start--) {
            results[start] = len - start - 1;
            for (int end = start; end < len; end++) {
                if (s.charAt(start) == s.charAt(end)) {
                    if (end - start < 2) p[start][end] = true;
                    else 
                        p[start][end] = p[start + 1][end - 1];
                }
                if (p[start][end]) {
                    if (end = len - 1) results[start] = 0;
                    else results[start] = Math.min(results[start], results[end + 1] + 1);
                }
            }
        }
        return results[0];
    }
    
    /**
     * Backtracking, generate all cuts
     */
    public static int minCut(String s) {
        Set<String> palin = new HashSet<String>();
        return minCut(s, 0, palin);
    }
    
    public static int minCut(String s, int count, Set<String> palin) {
        // System.out.println("s: " + s + " \tcount: " + count);
        if (s == null || s.length() == 0 || isPalindrome(s)) {
            palin.add(s);
            return count;
        }
            
        int min = Integer.MAX_VALUE;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isPalindrome(s.substring(0, i))) {
                palin.add(s.substring(0, i));
                // add DP here
                int result = palin.contains(s.substring(i)) ? count : minCut(s.substring(i), count + 1, palin);;
                min = Math.min(min, result);
            }
        }
        return min;
    }
    
    /**
     * judge whether a string is a Palindrome
     */
    private static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        if (s.length() == 1) return true;
        
        int i = 0;
        int len = s.length();
        while (i < len / 2) {
            if (s.charAt(i) != s.charAt(len - i - 1)) return false;
            i++;
        }
        return true;
    }
}
