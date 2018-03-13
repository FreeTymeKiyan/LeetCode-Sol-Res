package com.freetymekiyan.algorithms.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given user events one by one containing begin time and end time, return the total number of seconds user stayed on
 * the website.
 * <p>
 * Example:
 * (0, 1), (1, 2) -> 2
 * (0, 5), (4, 6) -> 6
 * (4, 6), (1, 3) -> 5
 * <p>
 * Company: Facebook
 */
public class TotalNumberOfSeconds {

    /**
     * Total number of seconds represented by the intervals.
     */
    private int seconds;

    /**
     * A sorted, non-overlapping list of events.
     */
    private List<int[]> events = new ArrayList<>();

    /**
     * Roughly the same as LC 57 Insert Interval.
     * Only that we have to maintain a number of total seconds.
     * <p>
     * Insert an event into a sorted, non-overlapping list of events.
     * If the list is empty, directly insert and calculate the total.
     * If the list is not empty, first find the position that this event should be inserted.
     * i.e., skip events if there is no merge.
     * Merge the event with the ones that overlap by checking if event in list's start <= new event's end.
     * Since we already know that event in list's end >= new event's start.
     * While merging, subtract the seconds from total when an event is merged.
     * After merging, add event to list and add its seconds to total.
     * <p>
     * The empty special case can be combined with general case.
     */
    public int totalNumberOfSeconds(int[] event) {
        int i = 0;
        while (i < events.size() && events.get(i)[1] < event[0]) {
            i++;
        }
        // Now i == events.size() OR events.get(i)[1] >= event[0]
        // Every event after i should have end time >= event[0]
        // since the list sorted and events are non-overlapping.
        while (i < events.size() && events.get(i)[0] <= event[1]) {
            int[] toMerge = events.remove(i);
            seconds -= (toMerge[1] - toMerge[0]);
            event[0] = Math.min(toMerge[0], event[0]);
            event[1] = Math.max(toMerge[1], event[1]);
        }
        events.add(i, event);
        seconds += (event[1] - event[0]);
        return seconds;
    }
}