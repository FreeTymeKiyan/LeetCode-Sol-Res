package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * <p>
 * Tags: Hash Table, Heap
 * Similar Problems: (M) Word Frequency, (M) Kth Largest Element in an Array
 */
public class TopKFrequentElements {

    private TopKFrequentElements t;

    /**
     * Use a map to store frequency.
     * Create a bucket of lists, indexed by frequency.
     * Each list contains the number of that frequency.
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        // Build frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }
        // Build frequency buckets
        List<Integer>[] lists = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : frequencyMap.entrySet()) {
            int frequency = e.getValue();
            if (lists[frequency] == null) lists[frequency] = new ArrayList<>();
            lists[frequency].add(e.getKey());
        }
        // Add top k most frequent integer to result
        List<Integer> res = new ArrayList<>();
        for (int i = lists.length - 1; i >= 0; i--) {
            if (lists[i] == null) continue;
            for (int j = 0; j < lists[i].size() && res.size() < k; j++) {
                res.add(lists[i].get(j));
            }
        }
        return res;
    }

    @Before
    public void setUp() {
        t = new TopKFrequentElements();
    }

    @Test
    public void testInvalidInput() {
        Assert.assertEquals(Collections.EMPTY_LIST, t.topKFrequent(null, 0));
        Assert.assertEquals(Collections.EMPTY_LIST, t.topKFrequent(new int[]{}, 0));
    }

    @Test
    public void testEdgeCase() {
        List<Integer> res = t.topKFrequent(new int[]{1}, 1);
        Assert.assertEquals(1, res.size());
        Assert.assertEquals(1, res.get(0).intValue());
    }

    @Test
    public void testExample() {
        List<Integer> res = t.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Assert.assertEquals(2, res.size());
        Assert.assertEquals(1, res.get(0).intValue());
        Assert.assertEquals(2, res.get(1).intValue());
    }

    @After
    public void tearDown() {
        t = null;
    }
}
