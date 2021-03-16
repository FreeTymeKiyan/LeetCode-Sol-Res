import java.util.*;

/**
 * 30. Substring with Concatenation of All Words   
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman" words: ["foo", "bar"]  You should return the indices: [0,9].
 * Tags: Hash Table Two Pointers String
 * Similar Problems: (H) Minimum Window Substring
 * @author chenshuna
 */ 

public class SubstringwithConcatenationofAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        if( s==null || words == null || s.length() == 0 || words.length == 0 ) return res;
        HashMap<String, Integer> wordDict = new HashMap<String, Integer>();
        int wordLen = words[0].length();
        for(String word : words){
            if(wordDict.containsKey(word))
                wordDict.put(word, wordDict.get(word) + 1);
            else 
                wordDict.put(word, 1);
        }
        /**
         * outside loop
         *  bar/foo/the/foo/bar/man
         *  b/arf/oot/hef/oob/arm/an
         *  ba/rfo/oth/efo/oba/rma/n
         */ 
        for(int i = 0; i < wordLen; i++){
            HashMap<String, Integer> sMap = new HashMap<String, Integer>();
            int count = 0;
            int start = i;
            for(int j = i; j <= s.length() - wordLen; j = j + wordLen){
                String temp = s.substring(j, j + wordLen);
                if(wordDict.containsKey(temp)){
                    if(sMap.containsKey(temp))
                         sMap.put(temp, sMap.get(temp) + 1);
                    else 
                         sMap.put(temp, 1);
                    
                    if(sMap.get(temp) <= wordDict.get(temp))
                        count++;
                    else
                    {
                        while(sMap.get(temp) > wordDict.get(temp))
                        {
                            String temp2 = s.substring(start, start + wordLen);
                            sMap.put(temp2, sMap.get(temp2) - 1);
                            if(sMap.get(temp2) < wordDict.get(temp2)) count--;
                            start = start + wordLen;
                        }
                    }
                
                    if(count == words.length){
                        res.add(start);
                        String word = s.substring(start, start + wordLen);
                        sMap.put(word, sMap.get(word) - 1);
                        count--;
                        start = start + wordLen;
                    }
                }
                else
                {
                    sMap.clear();
                    count = 0;
                    start = j + wordLen;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(findSubstring(s, words));
    }
}