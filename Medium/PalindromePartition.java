import java.util.*;
/**
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * 
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 * 
 * Tags: Backtracking
 */
class PalindromePartition {
    
    public static void main(String[] args) {
        System.out.println(partition("aab"));
        System.out.println(partition("aa"));
    }
    
    /**
     * Backtracking
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) return res;
        partition(s, 0, res, new ArrayList<String>());
        return res;
    }
    
    public static void partition(String s, int pos, List<List<String>> res, List<String> cut) {
        if (pos == s.length()) { // note the stop condition
            res.add(new ArrayList<String>(cut)); // dereference
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (isPalindrome(prefix)) {
                cut.add(prefix);
                partition(s, i, res, cut); // update pos with i
                cut.remove(cut.size() - 1);
            }
        }
    }
    
    private static boolean isPalindrome(String str) {
        int s = 0;
        int e = str.length() - 1;
        while (s < e) {
            if (str.charAt(s) != str.charAt(e)) return false;
            s++;
            e--;
        }
        return true;
    }
}
