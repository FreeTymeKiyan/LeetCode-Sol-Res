package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.*;

/**
 * 436. Find Right Interval
 * <p>
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger
 * than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 * <p>
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum
 * start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the
 * interval i. Finally, you need output the stored value of each interval as an array.
 * <p>
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 * Example 1:
 * Input: [ [1,2] ]
 * <p>
 * Output: [-1]
 * <p>
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * Example 2:
 * Input: [ [3,4], [2,3], [1,2] ]
 * <p>
 * Output: [-1, 0, 1]
 * <p>
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 * <p>
 * Output: [
 * ]
 * <p>
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 * <p>
 * Related Topics: Binary Search
 * Similar Questions: (H) Data Stream as Disjoint Intervals
 */
public class FindRightInterval {

    /**
     * Binary Search.
     * Create a start to index mapping.
     * Sort the intervals by start asc.
     * For each interval i, binary search i.end among starts and return the index.
     */
    public int[] findRightInterval(Interval[] intervals) {
        if (intervals.length == 1) return new int[]{-1};
        Map<Integer, Integer> startToIndex = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            startToIndex.put(intervals[i].start, i);
        }
        Interval[] copy = Arrays.copyOf(intervals, intervals.length);
        Arrays.sort(copy, Comparator.comparingInt(i -> i.start));
        int[] results = new int[intervals.length];
        for (int i = 0; i < copy.length; i++) {
            int start = binarySearch(copy, intervals[i].end);
            results[i] = start < intervals[i].end ? -1 : startToIndex.get(start);
        }
        return results;
    }

    private int binarySearch(Interval[] intervals, int end) {
        int lo = 0;
        int hi = intervals.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (end == intervals[mid].start) {
                return end;
            } else if (end > intervals[mid].start) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return intervals[lo].start;
    }

    /**
     * Tree Map.
     * Same idea, but tree map already has the implementation ready.
     * Tree map's keys are sorted and has the API to get ceiling/floor or lower/higher key/entry.
     */
    public int[] findRightInterval2(Interval[] intervals) {
        if (intervals.length == 0) return new int[]{};
        if (intervals.length == 1) return new int[]{-1};
        TreeMap<Integer, Integer> startToIndex = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            startToIndex.put(intervals[i].start, i);
        }
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Map.Entry<Integer, Integer> right = startToIndex.ceilingEntry(intervals[i].end);
            result[i] = right == null ? -1 : right.getValue();
        }
        return result;
    }
}
