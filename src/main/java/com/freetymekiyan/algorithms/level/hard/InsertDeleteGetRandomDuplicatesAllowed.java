package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * <p>
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being
 * returned is linearly related to the number of same value the collection contains.
 * Example:
 * <p>
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 * <p>
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 * <p>
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 * <p>
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 * <p>
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 * <p>
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 * <p>
 * Companies: Facebook, Affirm, Uber, Google, LinkedIn, Amazon, Two Sigma, Yelp
 * <p>
 * Related Topics: Array, Hash Table, Design
 * <p>
 * Similar Questions: (M) Insert Delete GetRandom O(1)
 */
public class InsertDeleteGetRandomDuplicatesAllowed {

  public class RandomizedCollection {

    private List<Integer> nums;
    private Map<Integer, LinkedHashSet<Integer>> locs;
    private Random r;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
      nums = new ArrayList<>();
      locs = new HashMap<>();
      r = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
      boolean contain = locs.containsKey(val);
      if (!contain)
        locs.put(val, new LinkedHashSet<>());
      locs.get(val).add(nums.size());
      nums.add(val);
      return !contain;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
      if (!locs.containsKey(val)) return false;
      int pos = locs.get(val).iterator().next();
      locs.get(val).remove(pos);
      if (locs.get(val).isEmpty()) locs.remove(val);

      int last = nums.size() - 1;
      if (pos < last) {
        int lastNum = nums.get(last);
        nums.set(pos, lastNum);
        locs.get(lastNum).remove(last);
        locs.get(lastNum).add(pos);
      }
      nums.remove(last);
      return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
      return nums.get(r.nextInt(nums.size()));
    }
  }
}