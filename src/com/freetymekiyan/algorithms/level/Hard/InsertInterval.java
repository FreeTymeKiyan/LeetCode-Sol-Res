package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * <p>
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * <p>
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * Company Tags: LinkedIn, Google, Facebook
 * Tags: Array, Sort
 * Similar Problems: (H) Merge Intervals
 */
public class InsertInterval {

    /**
     * Array, Sort. O(n).
     * Not in place solution, make use of intervals are sorted.
     * Go through the given intervals.
     * For each of them, there are 3 situations:
     * 1) No overlap, in front of the new interval
     * 2) No overlap, behind the new interval
     * 3) Overlap, merge with the new interval
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        res.add(newInterval);
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        for (Interval i : intervals) {
            int start = res.get(res.size() - 1).start;
            int end = res.get(res.size() - 1).end;
            if (i.end < start) {
                res.add(res.size() - 1, i); // No overlap, insert to second last
            } else if (end < i.start) {
                res.add(i); // No overlap, append to last
            } else {
                start = Math.min(start, i.start);
                end = Math.max(end, i.end);
                res.set(res.size() - 1, new Interval(start, end));
            }
        }
        return res;
    }

    /**
     * In place solution.
     * Skip the intervals not overlapping with the new one.
     * Merge all intervals overlap with the new interval.
     * And remove them.
     * Add new interval in position.
     */
    public List<Interval> insertB(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        // Skip those intervals with no overlap with new interval
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            i++;
        }
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval =
                new Interval(Math.min(intervals.get(i).start, newInterval.start),
                             Math.max(intervals.get(i).end, newInterval.end));
            intervals.remove(i);
        }
        intervals.add(i, newInterval);
        return intervals;
    }

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
