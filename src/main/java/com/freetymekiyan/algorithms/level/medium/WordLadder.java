package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * Company Tags: Amazon, LinkedIn, Snapchat, Facebook, Yelp
 */
public class WordLadder {

    /**
     * BFS. Level-order Traversal.
     * Search from begin word to end word.
     * Use an integer to track ladder length.
     * Add begin word to queue.
     * Remove begin word from word list to set it as visited.
     * While queue is not empty:
     * | Traverse each word w in this level.
     * | If w == end word, return true.
     * | Else from w generate all possible next word by changing one character.
     * | If next word also in word list:
     * |   Add it to queue and remove it from word list.
     * | Update ladder length after this level is finished.
     * If search failed, return 0;
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        wordList.remove(beginWord); // Remove from set as visited.
        int length = 1; // Length should be 1 at least.

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return length;
                }
                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) { // Generate neighbors.
                        char[] chars = word.toCharArray();
                        if (chars[j] == c) { // Skip same character.
                            continue;
                        }
                        chars[j] = c;
                        String nextWord = new String(chars);
                        if (wordList.contains(nextWord)) { // Neighboring word is in list.
                            queue.add(nextWord);
                            wordList.remove(nextWord);
                        }
                    }
                }
            }
            length++; // Next level.
        }
        return 0; // Not found, return 0.
    }

    /**
     * BFS, search from both ends.
     */
    public int ladderLengthB(String beginWord, String endWord, Set<String> wordList) {
        int pathLength = 2;

        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();
        start.add(beginWord);
        end.add(endWord);
        wordList.remove(beginWord);
        wordList.remove(endWord);

        while (!start.isEmpty()) {
            if (start.size() > end.size()) {
                Set<String> temp = start;
                start = end;
                end = temp;
            }
            Set<String> next = new HashSet<>();
            for (String cur : start) {
                char[] strArray = cur.toCharArray();
                for (int i = 0; i < strArray.length; i++) {
                    char old = strArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        strArray[i] = c;
                        String str = String.valueOf(strArray);
                        if (end.contains(str)) {
                            return pathLength;
                        }
                        if (wordList.contains(str)) {
                            next.add(str);
                            wordList.remove(str);
                        }
                    }
                    strArray[i] = old;
                }
            }
            start = next;
            pathLength++;
        }
        return 0;
    }

    @Test
    public void testExamples() {
        String start = "hot";
        String end = "dog";
        Set<String> s = new HashSet<>();
        s.add("hot");
        s.add("dog");
        s.add("dot");
        Assert.assertEquals(3, ladderLength(start, end, s));
    }
}
