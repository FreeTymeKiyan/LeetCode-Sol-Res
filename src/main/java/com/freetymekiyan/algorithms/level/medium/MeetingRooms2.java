package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 * <p>
 * Company Tags: Google Facebook
 * Tags: Heap, Greedy, Sort
 * Similar Problems: (H) Merge Intervals, (E) Meeting Rooms
 */
public class MeetingRooms2 {

    /**
     * Sort. Heap. Greedy.
     * Always put the next starting meeting after the first ending meeting.
     * If the start time overlaps with the nearest end time, need a new room.
     * So, sort the meetings according to start time first.
     * Then for each interval in the array:
     * | If min heap is not empty or start time doesn't overlap with first ending time:
     * |   Poll first ending time from the heap.
     * | Add the ending time for current meeting.
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
        PriorityQueue<Integer> firstEnd = new PriorityQueue<>();
        for (Interval i : intervals) {
            if (!firstEnd.isEmpty() && i.start >= firstEnd.peek()) {
                firstEnd.poll();
            }
            firstEnd.add(i.end);
        }
        return firstEnd.size();
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
