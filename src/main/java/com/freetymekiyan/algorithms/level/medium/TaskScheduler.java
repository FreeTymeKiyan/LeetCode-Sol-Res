package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 621. Task Scheduler
 * <p>
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters
 * represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For
 * each interval, CPU could finish one task or just be idle.
 * <p>
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n
 * intervals that CPU are doing different tasks or just be idle.
 * <p>
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * <p>
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 * <p>
 * Related Topics: Array, Greedy, Queue
 * Similar Questions: (H) Rearrange String k Distance Apart
 */
public class TaskScheduler {

    public static final int NUM_OF_LETTERS = 26;

    /**
     * Priority Queue.
     * The actual task name does not matter.
     * As long as we know the number of tasks for each task, we can get the intervals needed.
     * First, go through the tasks and generate the counts.
     * Then add these counts to a priority queue, in descending order (Max-heap).
     * Now we are able to get the highest count during scheduling.
     * We perform the actual scheduling every n intervals. Since each task can be used only once.
     * 1. Get highest count task from max heap
     * | 1.1 If the heap is empty, then this interval is idle.
     * | 1.2 If not, remove the count from the heap to mark the task as scheduled.
     * |   1.2.1 If the task still has more, add it the the next tasks.
     * 2. Increment number of cycles used.
     * 3. Check if during this n intervals, all tasks are done already. If so, stop.
     * 4. Add next tasks to the max-heap for the next scheduling.
     * Return the number of cycles recorded.
     */
    public int leastInterval(char[] tasks, int n) {
        // Generate a task to count mapping.
        int[] map = new int[NUM_OF_LETTERS]; // Only 26 characters and can be converted to integer.
        for (char c : tasks) {
            map[c - 'A']++;
        }
        // Add all tasks to a max heap.
        PriorityQueue<Integer> pq = new PriorityQueue<>(NUM_OF_LETTERS, Collections.reverseOrder());
        for (int count : map) {
            if (count > 0) {
                pq.offer(count);
            }
        }
        // Perform scheduling.
        int intervals = 0;
        while (!pq.isEmpty()) {
            List<Integer> nextTasks = new ArrayList<>();
            for (int i = 0; i <= n; i++) { // i <= n because n is the cooling interval. Schedule interval should be n+1.
                if (!pq.isEmpty()) {
                    int count = pq.poll();
                    if (count > 1) {
                        nextTasks.add(count - 1); // Add count - 1 since 1 task is finish in this interval.
                    }
                }
                intervals++;
                if (pq.isEmpty() && nextTasks.size() == 0) {
                    break;
                }
            }
            for (int task : nextTasks) {
                pq.offer(task);
            }
        }
        return intervals;
    }

    /**
     * Math.
     * Find the idle intervals.
     * Assuming A is the task with most count, and all of them are scheduled in a grid.
     * The maximum number of idle intervals can be: (max(# of one task) - 1)* n.
     * Then we fill the idle intervals with the rest of the tasks.
     * If n < distinct number of tasks, every interval is always used.
     * # of idles calculated this way can be negative.
     * The actual # of intervals is the same as # of tasks.
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[NUM_OF_LETTERS];
        for (char c : tasks) {
            map[c - 'A']++; // Task names are always capitalized.
        }
        Arrays.sort(map);
        int maxCount = map[NUM_OF_LETTERS - 1] - 1;
        int idles = maxCount * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idles -= (Math.min(map[i], maxCount));
        }
        return tasks.length + (idles > 0 ? idles : 0);
    }
}