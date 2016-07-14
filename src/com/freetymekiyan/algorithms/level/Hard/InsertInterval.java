import java.util.*;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * 
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
 * [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 
 * Tags: Array, Sort
 */
class InsertInterval {
    public static void main(String[] args) {
        
    }
    
    /**
     * O(n), not in place solution, make use of intervals are sorted
     * Go through the list, compare interval's start and end with the last 
     * interval of result, they may overlap
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        res.add(newInterval);
        if (intervals == null || intervals.size() == 0) return res;
        for (Interval i : intervals) {
            int a = res.get(res.size() - 1).start;
            int b = res.get(res.size() - 1).end;
            if (i.end < a) res.add(res.size() - 1, i); // no overlap, add to second last
            else if (b < i.start) res.add(i); // no overlap, add to last
            else {
                a = Math.min(a, i.start);
                b = Math.max(b, i.end);
                res.set(res.size()- 1, new Interval(a, b));
            }
        }
        return res;
    }
    
    /**
     * In place solution
     * Find start and end point of the interval to be merged
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> results = new ArrayList<Interval>();
        if (intervals == null||intervals.size() == 0){
            results.add(newInterval);
            return results;
        }
        // find position for new interval
        int start = 0, end = 0; // end points to the position after
        for (Interval i : intervals){
            if (newInterval.start > i.end) start++;
            if (newInterval.end >= i.start) end++;
            else break;
        }
        if (start == end) {
            results.addAll(intervals);
            results.add(start, newInterval);
            return results;
        }
        // add intervals from 0 to start
        for (int i = 0; i < start; i++) results.add(intervals.get(i));
        // build and add overlapped interval
        Interval interval = new Interval(Math.min(newInterval.start, intervals.get(start).start), Math.max(newInterval.end, intervals.get(end - 1).end)); // note that it's end - 1
        results.add(interval);
        // add remainning intervals
        for (int i = end; i < intervals.size(); i++) results.add(intervals.get(i));
        return results;
    }
    
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
