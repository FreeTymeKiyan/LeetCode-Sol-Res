import java.util.*;

/**
 * Word Break II
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 * Tags:  Dynamic Programming Backtracking
 * @author chenshuna
 */ 

public class WordBreakII {
    static HashMap<Integer, List<String>> map = new HashMap<>();

    public static List<String> wordBreak(String s, Set<String> wordDict) {
        int maxLengthOfWordDict = 0;
        for(String str : wordDict)
            maxLengthOfWordDict = Math.max(maxLengthOfWordDict, str.length());
        return helper(s, wordDict, 0, maxLengthOfWordDict);
    }

    public static List<String> helper(String s, Set<String> wordDict, int start, int max){
        List<String> res = new ArrayList<>();
        if(start == s.length()) {
            res.add("");
            return res;
        }
        for(int i = start + 1 ; i <= max + start && i <= s.length(); i++){
            String temp  = s.substring(start, i);
            if(wordDict.contains(temp)){
                List<String> l;
                if(map.containsKey(i))
                    l = map.get(i);
                else
                    l = helper(s, wordDict, i, max);
                for(String ss : l)
                    res.add(temp + (ss.equals("") ? "" : " ") + ss);
            }
        }
        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        Set<String> wordDict = new HashSet<String>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        String s = "catsanddog";
        System.out.print(wordBreak(s, wordDict));
    }
}