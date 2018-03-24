package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.*;

/**
 * 56. Merge Intervals
 * <p>
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
     * Sort. Greedy. O(nlogn) Time.
     * Sort the intervals by start time, ascending.
     * Use a pointer, prev, for previous merged interval.
     * For each of the intervals:
     * | If prev == null, merged is empty, add directly and update prev.
     * | If prev.end < i.start, no overlap, add directly and update prev.
     * | Else if prev.end >= i.start, there is overlap.
     * |   We already know i.start >= prev.start because of sorting. No need to update start.
     * |   Only update end is enough.
     * |   If prev.end >= i.end, no need to update end.
     * |   If prev.end < i.end, update end to i.end.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        List<Interval> merged = new ArrayList<>();
        Collections.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));
        Interval prev = null; // A pointer to the last interval in merged list.
        for (Interval i : intervals) {
            if (prev == null || prev.end < i.start) { // Empty list or no overlap.
                merged.add(i);
                prev = i; // Update previous pointer.
            } else if (prev.end < i.end) { // Overlap and the end of current interval is larger.
                prev.end = i.end; // Update previous end to merge.
            }
        }
        return merged;
    }

    /**
     * Sort. Greedy. In-place.
     * Practically this implement is slower because for each loop runs faster.
     */
    public List<Interval> merge2(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));
        for (int i = 1; i < intervals.size(); i++) {
            Interval prev = intervals.get(i - 1);
            Interval current = intervals.get(i);
            if (prev.end < current.start) continue;
            prev.end = current.end > prev.end ? current.end : prev.end;
            intervals.remove(i);
            i--;
        }
        return intervals;
    }

    /**
     * Sort.
     * Sort starts and ends of all intervals separately.
     * Compare start[i+1] with end[i].
     * Use j to track next start to be added.
     * If start[i+1] > end[i], no overlap, add start[j] and end[i] as an interval.
     * Update j with i+1, which is the next start after start[i] and end[i].
     */
    public List<Interval> merge3(List<Interval> intervals) {
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        List<Interval> res = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
            if (i == n - 1 || starts[i + 1] > ends[i]) {
                res.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }
        return res;
    }

}