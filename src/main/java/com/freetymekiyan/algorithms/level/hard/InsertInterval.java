package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.LinkedList;
import java.util.List;

/**
 * 57. Insert Interval
 * <p>
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
     * Array. In-place solution.
     * Skip the non-overlapping intervals whose end time is before new interval's start.
     * For overlapped intervals that start before new interval end.
     * | Remove this overlapped interval from list.
     * | Merge it with the new interval by: 1. update start to min start times; 2. update end to max end times.
     * Add new interval in the position i.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        // Skip the non-overlapping intervals.
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            i++;
        }
        // Now i == intervals.size() OR intervals.get(i).end >= newInterval.start
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) { // Compare current interval with merged new interval.
            Interval inter = intervals.remove(i); // Remove the overlapped interval.
            newInterval.start = Math.min(inter.start, newInterval.start);
            newInterval.end = Math.max(inter.end, newInterval.end);
        }
        intervals.add(i, newInterval);
        return intervals;
    }

    /**
     * Array. Sort. Not in place solution. O(n) Time.
     * Make use of intervals are sorted.
     * Create a result list of intervals, res.
     * Add new interval to res first.
     * Go through the given intervals.
     * For each interval i, there are 3 situations:
     * 1) i and previous interval not overlapping, in front of the new interval.
     * 2) No overlap, behind the new interval
     * 3) Overlap, merge with the new interval
     */
    public List<Interval> insertB(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<>();
        res.add(newInterval);
        for (Interval i : intervals) {
            int start = res.get(res.size() - 1).start; // Last interval.
            int end = res.get(res.size() - 1).end;
            if (i.end < start) { // i ends before last interval.
                res.add(res.size() - 1, i); // Insert it before last interval.
            } else if (end < i.start) { // i starts after last interval.
                res.add(i); // Append it to last.
            } else { // i overlaps with last interval.
                start = Math.min(start, i.start);
                end = Math.max(end, i.end);
                res.set(res.size() - 1, new Interval(start, end));
            }
        }
        return res;
    }
}