package com.freetymekiyan.algorithms.level.medium;

import java.util.Stack;

/**
 * 739. Daily Temperatures
 * <p>
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would
 * have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * <p>
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1,
 * 0, 0].
 * <p>
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range
 * [30, 100].
 * <p>
 * Related Topics: Hash Table, Stack
 * Similar Questions: (E) Next Greater Element I
 */
public class DailyTemperatures {

    /**
     * Stack.
     * Let's say we are now at temperatures[i], t[i]. We need to:
     * 1. Update all t[j], j in [0..i-1], that t[j] < t[i].
     * 2. t[j] has not been updated.
     * How can we know t[j] has not been updated yet?
     * We can remove t[j] from some data structure when it gets updated.
     * How to avoid scanning through all values in the data structure we are to use?
     * The data structure records insertion order. Queue or stack?
     * If queue, first removed value is the largest. If stack, first removed value is the smallest.
     * Since t[i] is looking for smaller values and will stop and larger or equal value, we should use stack.
     * Note that temperatures can duplicate.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>(); // A stack of indices.
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }
}