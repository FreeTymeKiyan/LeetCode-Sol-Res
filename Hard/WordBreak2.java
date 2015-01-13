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
    Map<String, List<String>> res = new HashMap<String, List<String>>();
    
    /**
     * DP, Backtracking
     * Store previous backtracking result in a map
     * Generate every substring from front
     * If not a word, skip
     * If is a word, and if the length run out, add to current solution
     * If within length do the following: 
     * check whether the rest of the string is already broken
     * If not, backtracking the rest of the string
     * If yes, get the result from memory function
     * If there is an result, add each word to current solution with front in
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>(); 

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String prefix = s.substring(0, i); // get prefix
            if (dict.contains(prefix)) { // is in dictionary
                if (i == len) words.add(prefix); // reach the end
                else {
                    String remain = s.substring(i, len); // rest of the string
                    List<String> remainDecomp = res.containsKey(remain) ?
                        res.get(remain) : wordBreak(remain, dict); // avoid backtracking if a decomposition is already there
                    if (remainDecomp != null) {
                        for (String w : remainDecomp) words.add(prefix + " " + w);
                        res.put(remain, remainDecomp); // add to memory func
                    }
                }
            }
        }
        return words;
    }

    /**
     * Simple backtracking
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String prefix = s.substring(0, i);
            if (dict.contains(prefix)) {
                if (i == len) {
                    words.add(prefix); // single word
                } else {
                    String remain = s.substring(i, len);
                    // backtracking
                    List<String> remainDecomp = wordBreak(remain, dict);
                    if (remainDecomp != null) { // has decomposition
                        for (String item : remainDecomp) {
                            words.add(prefix + " " + item); // item is already words with spaces between
                        }
                    }
                }
            }
        }
        return words;
    }
}
