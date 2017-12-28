package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 251. Meeting Rooms
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine
 * if a person could attend all meetings.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 * <p>
 * Company Tags: Facebook
 * Tags: Sort
 * Similar Problems: (H) Merge Intervals, (M) Meeting Rooms II
 */
public class MeetingRooms {

    /**
     * Sort.
     * The actual problem is detecting whether there is any overlap between meetings.
     * Sort the intervals.
     * From second interval, check each interval with the previous one.
     * If current interval's start < previous interval's end, return false.
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        return true;
    }
}