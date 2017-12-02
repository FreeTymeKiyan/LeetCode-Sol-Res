package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in
 * linear time and in O(1) space.
 * <p>
 * Hint:
 * <p>
 * How many majority elements could it possibly have?
 * <p>
 * Tags: Array
 * Similar Problems: (E) Majority Element
 */
public class MajorityElement2 {

    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        int num1 = 0;
        int count1 = 0;
        int num2 = 0;
        int count2 = 0;
        List<Integer> res = new ArrayList<>();
        for (int n : nums) {
            if (n == num1) {
                count1++;
            } else if (n == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = n;
                count1 = 1;
            } else if (count2 == 0) {
                num2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int n : nums) {
            if (n == num1) {
                count1++;
            } else if (n == num2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(num1);
        }
        if (count2 > nums.length / 3) {
            res.add(num2);
        }
        return res;
    }

}
