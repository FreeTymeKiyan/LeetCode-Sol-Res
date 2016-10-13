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
 * Similar Problems: (H) Merge Intervals (E) Meeting Rooms
 */
public class MeetingRooms2 {

    public class Solution {

        /**
         * Sort, heap, greedy.
         * Always put the next starting meeting after the first ending meeting.
         * If the start time overlaps with the nearest end time, need a new room.
         * So, sort the meetings according to start time first.
         * Then for each interval in the array:
         * 1) If min heap is empty, add to heap directly.
         * 2) Compare with the min ending time, if doesn't overlap, add this meeting after.
         * 3) If overlap, need a new room.
         */
        public int minMeetingRooms(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) {
                return 0;
            }
            Arrays.sort(intervals, (a, b) -> a.start - b.start);
            PriorityQueue<Interval> minHeap = new PriorityQueue<>(intervals.length, (a, b) -> a.end - b.end);
            for (Interval i : intervals) {
                if (!minHeap.isEmpty() && i.start >= minHeap.peek().end) {
                    minHeap.poll();
                }
                minHeap.add(i);
            }
            return minHeap.size();
        }
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
