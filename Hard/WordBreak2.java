/**
 * Given a string s and a dictionary of words dict, add spaces in s to
 * construct a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * Tags: DP, Backtracking
 */
public class Solution {
    
    /**
     * memory function
     * store how a word can be decomposed
     */
    Map<String, List<String>> results = new HashMap<String, List<String>>();
    
    /**
     * dp + backtracking
     * generate every substring from front
     * if not a word, skip
     * if is a word, and if the length run out, add to current solution
     * if within length do the following: 
     * check whether the rest of the string is already broken
     * if not, backtracking the rest of the string
     * if yes, get the result from memory function
     * if there is an result, add each word to current solution with front in
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>(); // one possible result

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String front = s.substring(0, i); // get front substring
            /*backtracking template*/
            if (dict.contains(front)) { // is a word
                if (i == len) {
                    words.add(front); // single word
                } else {
                    String remain = s.substring(i, len); // rest of the string
                    // dp, if there is a decomposition already, don't recurse
                    List<String> remainSet = results.containsKey(remain) ?
                        results.get(remain) : wordBreak(remain, dict);
                    if (remainSet != null) {
                        for (String item : remainSet) {
                            words.add(front + " " + item);
                        }
                        results.put(remain, remainSet); // add to memory func
                    }
                }
            }
        }
        return words;
    }

    /**
     * bare recursion
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String front = s.substring(0, i);
            if (dict.contains(front)) {
                if (i == len) {
                    words.add(front); // single word
                } else {
                    String remain = s.substring(i, len);
                    // backtracking
                    List<String> remainSet = wordBreak(remain, dict);
                    if (remainSet != null) { // has decomposition
                        for (String item : remainSet) {
                            words.add(front + " " + item); // item is already words with spaces between
                        }
                    }
                }
            }
        }
        return words;
    }
}