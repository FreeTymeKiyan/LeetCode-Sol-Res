package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 737. Sentence Similarity II
 * <p>
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs,
 * determine if two sentences are similar.
 * <p>
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the
 * similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and
 * "good" are similar, then "great" and "fine" are similar.
 * <p>
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being
 * similar.
 * <p>
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs
 * = [] are similar, even though there are no specified similar word pairs.
 * <p>
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"]
 * can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Note:
 * <p>
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 * <p>
 * Related Topics: Depth-first Search, Union Find
 * Similar Questions: (M) Friend Circles, (M) Accounts Merge, (E) Sentence Similarity
 */
public class SentenceSimilarity2 {

    private int[] ids = new int[2000];

    /**
     * Union Find.
     * The pairs given can be treated as graph edges.
     * Each word is a node. Similar words are connected. Dissimilar words are not.
     * Connected words form a connected component.
     * Checking whether 2 words are similar is checking whether they are in the same connected component.
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || pairs == null) return false;
        if (words1.length != words2.length) return false;
        for (int i = 0; i < ids.length; i++) { // Initialize each connected component with a different id.
            ids[i] = i;
        }
        int id = 0;
        Map<String, Integer> wordToId = new HashMap<>(); // Map each word to a connected component.
        for (String[] p : pairs) {
            if (!wordToId.containsKey(p[0])) {
                wordToId.put(p[0], id++);
            }
            if (!wordToId.containsKey(p[1])) {
                wordToId.put(p[1], id++);
            }
            union(wordToId.get(p[0]), wordToId.get(p[1])); // Union the 2 words.
        }
        for (int i = 0; i < words1.length; i++) {
            if (!wordToId.containsKey(words1[i])) { // Note that word may not exist in pairs. Assign id again here.
                wordToId.put(words1[i], id++);
            }
            if (!wordToId.containsKey(words2[i])) {
                wordToId.put(words2[i], id++);
            }
            if (find(wordToId.get(words1[i])) != find(wordToId.get(words2[i]))) {
                return false;
            }
        }
        return true;
    }

    private void union(int p, int q) {
        int r1 = find(p);
        int r2 = find(q);
        if (r1 != r2) {
            ids[r1] = r2; // Connect p's root with q's root.
        }
    }

    private int find(int id) {
        while (id != ids[id]) {
            ids[id] = ids[ids[id]];
            id = ids[id];
        }
        return id;
    }
}