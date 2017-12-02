package com.freetymekiyan.algorithms.level.easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * <p>
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into
 * the memory at once?
 * <p>
 * Tags: Binary Search, Hash Table, Two Pointers, Sort
 * Similar Problems: (E) Intersection of Two Arrays
 */
public class IntersectionofTwoArrays2 {

    /**
     * Use a hash map to record count of each integer in one array.
     * Then go through the other array to find intersection.
     * Count should be updated when an intersection is found.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums1) {
            count.put(n, count.containsKey(n) ? count.get(n) + 1 : 1);
        }
        List<Integer> intersections = new ArrayList<>(Math.min(nums1.length, nums2.length));
        for (int n : nums2) {
            if (count.containsKey(n)) {
                intersections.add(n);
                count.put(n, count.get(n) - 1);
                if (count.get(n) == 0)
                    count.remove(n);
            }
        }
        int[] res = new int[intersections.size()];
        for (int i = 0; i < intersections.size(); i++) {
            res[i] = intersections.get(i);
        }
        return res;
    }

    public int[] intersectJava8(int[] nums1, int[] nums2) {
        // Create an integer array stream to boxed value and collect to a map grouping by element and count as value
        Map<Integer, Long> map = Arrays.stream(nums2).boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        // Return by filtering the other array stream
        return Arrays.stream(nums1).filter(i -> {
            if (!map.containsKey(i) || map.get(i) < 1) return false;
            map.put(i, map.get(i) - 1);
            return true;
        }).toArray();
    }
}
