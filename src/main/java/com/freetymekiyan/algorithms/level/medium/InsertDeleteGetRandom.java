package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 * <p>
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 * Example:
 * <p>
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 * <p>
 * Related Topics: Array, Hash Table, Design
 * Similar Questions: (H) Insert Delete GetRandom O(1) - Duplicates allowed
 */
public class InsertDeleteGetRandom {

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    class RandomizedSet {

        private List<Integer> nums;
        private Map<Integer, Integer> numToIndex;
        private Random r;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            nums = new ArrayList<>();
            numToIndex = new HashMap<>();
            r = new Random();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         * Check if the value exists.
         * If yes, return false.
         * If not, insert the value to the list.
         * Then put the <num, index> to the map.
         * Return true.
         */
        public boolean insert(int val) {
            if (numToIndex.containsKey(val)) return false;
            nums.add(val);
            numToIndex.put(val, nums.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         * Check if the value exists.
         * If not, nothing to remove, return false.
         * If yes, we try to remove it.
         * If we remove it directly from where it is, we will have to update all the numbers after it in the list.
         * To avoid that, we swap it with the last number. Then we won't need to update a bunch of numbers.
         * So get its index, get the last number, put the last number to the position.
         * Then remove the val from the list and map.
         */
        public boolean remove(int val) {
            if (!numToIndex.containsKey(val)) return false;
            int index = numToIndex.get(val);
            int lastNum = nums.get(nums.size() - 1);
            numToIndex.put(lastNum, index);
            nums.set(index, lastNum);
            nums.remove(nums.size() - 1);
            numToIndex.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         * Just generate a random index.
         */
        public int getRandom() {
            return nums.get(r.nextInt(nums.size()));
        }
    }
}