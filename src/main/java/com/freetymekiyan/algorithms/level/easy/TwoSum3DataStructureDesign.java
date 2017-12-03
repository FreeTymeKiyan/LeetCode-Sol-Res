package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * <p>
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * <p>
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * <p>
 * Company Tags: LinkedIn
 * Tags: Hash Table, Design
 * Similar Problems: (E) Two Sum, (E) Unique Word Abbreviation
 */
public class TwoSum3DataStructureDesign {

    public class TwoSum {

        List<Integer> nums = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>(); // Map iterator is slow.

        // Add the number to an internal data structure.
        public void add(int number) {
            if (!count.containsKey(number)) {
                count.put(number, 0);
                nums.add(number);
            }
            count.put(number, count.get(number) + 1);
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            for (int num1 : nums) {
                int num2 = value - num1;
                if (!count.containsKey(num2)) {
                    continue;
                }
                if (num1 != num2 && count.get(num2) > 0) {
                    return true;
                }
                if (num1 == num2 && count.get(num2) >= 2) {
                    return true;
                }
            }
            return false;
        }
    }

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
}
