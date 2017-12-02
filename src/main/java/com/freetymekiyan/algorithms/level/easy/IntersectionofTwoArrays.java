package com.freetymekiyan.algorithms.level.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * <p>
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 * <p>
 * Tags: Binary Search, Hash Table, Two Pointers, Sort
 * Similar Problems: (E) Intersection of Two Arrays II
 */
public class IntersectionofTwoArrays {

    /**
     * Two hash sets.
     * One hash set for one array. The other for the intersection.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            intersection(nums2, nums1);
        Set<Integer> set = new HashSet<>(nums1.length);
        for (int n : nums1) {
            set.add(n);
        }
        Set<Integer> intersect = new HashSet<>(nums1.length);
        for (int n : nums2) {
            if (set.contains(n)) {
                intersect.add(n);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }

    /**
     * Java 8 stream.
     * Convert one of the arrays to set using collect.
     * Then generate a distinct filtered stream from the other array.
     */
    public int[] intersectionJava8(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums1).distinct().filter(set::contains).toArray();
    }
}
