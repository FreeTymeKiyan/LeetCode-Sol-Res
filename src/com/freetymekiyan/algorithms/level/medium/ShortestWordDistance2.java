package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your
 * method will be called repeatedly many times with different parameters. How would you optimize it?
 * <p>
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 * and word2 and return the shortest distance between these two words in the list.
 * <p>
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * <p>
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Hash Table, Design
 * Similar Problems: (E) Merge Two Sorted Lists, (E) Shortest Word Distance, (M) Shortest Word Distance III
 */
public class ShortestWordDistance2 {

    @Test
    public void testExamples() {
        WordDistance w = new WordDistance(new String[]{"a", "b"});
        Assert.assertEquals(1, w.shortest("a", "b"));
        Assert.assertEquals(1, w.shortest("b", "a"));
    }

    /**
     * Hash Table.
     * Store the word to all its indices mapping.
     * Get the shortest distance from two lists.
     */
    public class WordDistance {

        Map<String, List<Integer>> locs;

        public WordDistance(String[] words) {
            locs = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                if (!locs.containsKey(words[i])) {
                    locs.put(words[i], new ArrayList<>());
                }
                locs.get(words[i]).add(i);
            }
        }

        /**
         * Merge. O(m + n).
         * The indices are already sorted in the list.
         * To get shortest distance, just move the pointer with smaller value.
         * For i < indices1.size(), j < indices2.size():
         * | Get indices in two lists, index1 and index2.
         * | If index1 > index2:
         * |   Update shortest with min(shortest, index1 - index2).
         * |   j++.
         * | Else
         * |   Update shortest with min(shortest, index2 - index1).
         * |   i++.
         */
        public int shortest(String word1, String word2) {
            List<Integer> indices1 = locs.get(word1);
            List<Integer> indices2 = locs.get(word2);
            int shortest = Integer.MAX_VALUE;
            for (int i = 0, j = 0; i < indices1.size() && j < indices2.size(); ) {
                int index1 = indices1.get(i);
                int index2 = indices2.get(j);
                if (index1 > index2) {
                    shortest = Math.min(shortest, index1 - index2);
                    j++;
                } else {
                    shortest = Math.min(shortest, index2 - index1);
                    i++;
                }
            }
            return shortest;
        }
    }

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
}
