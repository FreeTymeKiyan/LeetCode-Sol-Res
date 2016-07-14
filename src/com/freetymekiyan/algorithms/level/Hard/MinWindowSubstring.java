import java.util.*;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * 
 * Note:
 * If there is no such window in S that covers all characters in T, return the
 * emtpy string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will
 * always be only one unique minimum window in S.
 * 
 * Tags: Hashtable, Two Pointers, String
 */
class MinWindowSubstring {
    public static void main(String[] args) {
        
    }
    
    /**
     * Use two maps to store count of characters
     * One for T and one for S
     * Use minLength to remember the minimum length of window
     * Use letterCnt to store whether S include all characters in T
     * Use two pointers, one slow, one fast
     * Traverse through S with fast pointer
     * If the character is in T, update it in window, update letterCnt
     * If letterCnt >= length of T, try to update slow pointer position
     * When we have chars that not in T or more than the count in T, update
     * Update count in window as well
     * Compare with minLength and update result as well
     */
    public String minWindow(String S, String T) {
        if (S == null || T == null) return "";
        if (S.length() == 0 || T.length() == 0 || T.length() > S.length()) return "";
        String res = "";
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        
        for (int i = 0; i < T.length(); i++) { // build map for T
            char c = T.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        
        int minLen = Integer.MAX_VALUE; // record minimum length
        int letterCnt = 0; // record letter count
        for (int slow = 0, fast = 0; fast < S.length(); fast++) {
            char c1 = S.charAt(fast); // get letter in S with fast pointer
            if (map.containsKey(c1)) { // build window map
                window.put(c1, window.containsKey(c1) ? window.get(c1) + 1 : 1);
                if (window.get(c1) <= map.get(c1)) letterCnt++;
            }
            if (letterCnt >= T.length()) { // already cover all letters in T
                // update slow pointer and letter count in window map
                char c2 = S.charAt(slow);
                while (!map.containsKey(c2) || window.get(c2) > map.get(c2)) {
                    if (window.containsKey(c2)) window.put(c2, window.get(c2) - 1);
                    slow++;
                }
                if (fast - slow + 1 < minLen) { // update result
                    minLen = fast - slow + 1;
                    res = S.substring(slow, fast + 1);
                }
            }
        }
        return res;
    }
}
