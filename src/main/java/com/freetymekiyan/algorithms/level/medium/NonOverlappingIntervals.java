package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.Arrays;

/**
 * 435. Non-overlapping Intervals
 * <p>
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the
 * intervals non-overlapping.
 * <p>
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * Example 1:
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 * <p>
 * Output: 1
 * <p>
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 * <p>
 * Output: 2
 * <p>
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 * Input: [ [1,2], [2,3] ]
 * <p>
 * Output: 0
 * <p>
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * Related Topics: Greedy
 * Similar Questions: (M) Minimum Number of Arrows to Burst Balloons
 */
public class NonOverlappingIntervals {

    /**
     * Greedy.
     * When two intervals overlap with each other, we have to remove one of them.
     * Which one? The one with larger end.
     * Why? When intervals are sorted by start ascending, larger end has higher possibility to overlap with following
     * intervals.
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length <= 1) return 0;
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
        int count = 0;
        Interval prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (prev.end > intervals[i].start) { // Overlaps.
                count++; // Must remove one of the two intervals.
                if (prev.end > intervals[i].end) {
                    prev = intervals[i]; // Keep the one with smaller end.
                }
            } else { // No overlap.
                prev = intervals[i];
            }
        }
        return count;
    }
}
