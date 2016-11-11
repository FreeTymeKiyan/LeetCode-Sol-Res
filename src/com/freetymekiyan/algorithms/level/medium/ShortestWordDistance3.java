package com.freetymekiyan.algorithms.level.medium;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * <p>
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the
 * list.
 * <p>
 * word1 and word2 may be the same and they represent two individual words in the list.
 * <p>
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 * <p>
 * Note:
 * You may assume word1 and word2 are both in the list.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Array
 * Similar Problems: (E) Shortest Word Distance, (M) Shortest Word Distance II
 */
public class ShortestWordDistance3 {

    /**
     * Array. Only one index.
     * Remember the previous index word1 or word2 seen.
     * Iterate through the array.
     * If we find either word1 or word2.
     * If previous index is initialized,
     * and the two words are the same or previous word is not the same as the current one.
     * Update min.
     * Then we update previous index.
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int prevIndex = -1;
        int min = words.length;
        boolean same = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (prevIndex != -1 && (same || !words[prevIndex].equals(words[i]))) {
                    min = Math.min(i - prevIndex, min);
                }
                prevIndex = i;
            }
        }
        return min;
    }

    /**
     * Array. Two indices.
     * Words may be the same but indices are not.
     * i1 and i2 are the last seen positions for word1 and word2.
     * When word1 and word2 are the same,
     * i1 is the first position, i2 is the latest position.
     */
    public int shortestWordDistanceB(String[] words, String word1, String word2) {
        int i1 = -1;
        int i2 = -1;
        int shortest = Integer.MAX_VALUE;
        boolean same = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (same) {
                    i1 = i2;
                    i2 = i;
                } else {
                    i1 = i;
                }
            } else if (words[i].equals(word2)) {
                i2 = i;
            }
            shortest = Math.min(shortest, Math.abs(i1 - i2));
        }
        return shortest;
    }

}
