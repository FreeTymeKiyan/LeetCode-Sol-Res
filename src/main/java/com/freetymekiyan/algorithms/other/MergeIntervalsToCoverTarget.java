package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of intervals, List<Interval> intervals, and a target interval, target. Merge these intervals and try to
 * cover target. Find the minimum number of intervals needed.
 * <p>
 * Example:
 * intervals: [[-1, 9], [1, 10], [0, 3], [9, 10], [3, 14], [2, 9], [10, 16]]
 * target: [2, 15]
 * output: 2
 * Explanation: We may cover [2, 15] with [1, 10] and [10, 16], which is only 2 intervals.
 * <p>
 * Company: Facebook
 */
public class MergeIntervalsToCoverTarget {

    /**
     * Greedy.
     * Go through intervals and keep only overlapping ones.
     * | If none, return 0.
     * Sort the overlapping intervals by start first.
     * For each overlapping interval i:
     * | If i.start <= target.start and i.end > maxEnd, the interval can be expanded.
     * |   Record the end index to find the max.
     * |   The greedy idea here means every time we try to cover target as much as possible with maximum end.
     * Update target's start with maximum end.
     * Now this iteration ends, we've covered target with 1 merged interval, so the # of intervals increments by 1.
     * At maximum we merge all overlapping intervals.
     */
    public int mergeToCover(List<Interval> intervals, Interval target) {
        List<Interval> overlaps = new ArrayList<>(intervals);
        // overlaps.removeIf(i -> i.end <= target.start || i.start >= target.end);
        for (Iterator<Interval> iter = overlaps.iterator(); iter.hasNext(); ) {
            Interval i = iter.next();
            if (i.end <= target.start || i.start >= target.end) { // Remove non-overlapping intervals.
                iter.remove();
            }
        }
        if (overlaps.isEmpty()) return 0;

        overlaps.sort(Comparator.comparingInt(i -> i.start));
        int numOfIntervals = 0;
        while (numOfIntervals < overlaps.size() && target.start < target.end) { // Not all used and target hasn't been covered.
            // Build a merged interval to cover target.
            int maxEnd = Integer.MIN_VALUE; // Max end current intervals can expand to.
            for (int i = numOfIntervals; i < overlaps.size(); i++) {
                if (overlaps.get(i).start <= target.start && overlaps.get(i).end > maxEnd) {
                    maxEnd = overlaps.get(i).end;
                }
            }
            target.start = maxEnd; // maxEnd might make start even smaller. But it'll still exit when all intervals are used.
            numOfIntervals++;
        }
        return target.start >= target.end ? numOfIntervals : 0; // Check whether target is covered before return.
    }
}