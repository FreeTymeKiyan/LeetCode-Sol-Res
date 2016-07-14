import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * 
 * Tags: Array, Sort
 */
class MergeIntervals {
    public static void main(String[] args) {
        
    }
    
    /**
     * Sort and merge, O(nlogn)
     * Sort the intervals according to their start value
     * Go through the intervals and update last interval
     * If last interval in result overlap with current interval
     * Remove last interval and add new interval with updated end value
     * Which is the bigger of last.end and i.end
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) return res;
        Collections.sort(intervals, new MyComparator());
        for (Interval i : intervals) {
            if (res.isEmpty()) res.add(i); // first interval
            else {
                Interval last = res.get(res.size() - 1); // get last interval
                if (last.end >= i.start) { // overlap
                    res.remove(last);
                    res.add(new Interval(last.start, Math.max(last.end, i.end))); // extend end
                } else res.add(i); //no overlap
            }
        }
        return res;
    }
    
    /**
     * Comparator for interval
     * Sort according to start date
     */
    class MyComparator implements Comparator<Interval> {
        
        @Override
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
    }
    
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
