package com.freetymekiyan.algorithms.level.easy;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the
 * list.
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
 * Tags: Array
 * Similar Problems: (M) Shortest Word Distance II, (M) Shortest Word Distance III
 */
public class ShortestWordDistance {

    /**
     * Array. Only one index.
     * Remember the previous index word1 or word2 seen.
     * Iterate through the array.
     * If we find either word1 or word2.
     * If previous index is initialized, and previous word is not same as the current one, update min.
     * Then we update previous index.
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int prevIndex = -1;
        int min = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (prevIndex != -1 && !words[prevIndex].equals(words[i])) {
                    min = Math.min(i - prevIndex, min);
                }
                prevIndex = i;
            }
        }
        return min;
    }

    /**
     * Array. Two indices.
     * Words can have duplicates, so we cannot use a map directly.
     * The distance between is abs(index1 - index2).
     * We can update this distance every time index1 or index2 is updated.
     * Then we store the minimum among these values.
     */
    public int shortestDistanceB(String[] words, String word1, String word2) {
        int i1 = -1;
        int i2 = -1;
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                i1 = i;
                if (i2 != -1) {
                    shortest = Math.min(shortest, Math.abs(i1 - i2));
                }
            }
            if (words[i].equals(word2)) {
                i2 = i;
                if (i1 != -1) {
                    shortest = Math.min(shortest, Math.abs(i1 - i2));
                }
            }
        }
        return shortest;
    }

}
