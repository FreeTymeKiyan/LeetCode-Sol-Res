package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * <p>
 * Company Tags: LinkedIn, Google, Facebook, Twitter, Microsoft, Bloomberg, Yelp
 * Tags: Array, Sort
 * Similar Problems: (H) Insert Interval, (E) Meeting Rooms, (M) Meeting Rooms II
 */
public class MergeIntervals {


    /**
     * Sort and merge, O(nlogn) time.
     * Sort the intervals according to the start value in asc order.
     * For each of the intervals,
     * 1) add to result directly if the result list is empty.
     * 2) add to result directly if there is no overlap between current interval and previous interval.
     * 3) If there is overlap, we already know current interval's start is larger than previous interval.
     * We need to update the end of previous interval if current interval's end is larger.
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        Interval prev = null; // A pointer to the last interval in list
        for (Interval i : intervals) {
            if (prev == null || prev.end < i.start) { // Empty list or no overlap
                res.add(i);
                prev = i; // Update pointer
            } else if (i.end > prev.end) { // Overlap and the end of new interval is larger
                prev.end = i.end; // Update end
            }
        }
        return res;
    }

    /**
     * Interval class provided by leetcode.
     */
    public class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
