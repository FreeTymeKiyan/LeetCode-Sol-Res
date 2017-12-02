package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class Asteroids {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int size : asteroids) {
            if (!stack.isEmpty() && stack.peek() > 0 && size < 0) {
                collide(stack, size);
            } else {
                stack.push(size);
            }
        }
//      stack.stream().mapToInt(i -> i).toArray(); will return in queue order.
        return stackToArray(stack);
    }

    private void collide(Deque<Integer> stack, int current) {
        if (stack.isEmpty() || stack.peek() < 0) {
            stack.push(current);
            return;
        }
        int top = stack.peek();
        int sum = top + current;
        if (sum == 0) {
            stack.pop();
        } else if (sum < 0) {
            stack.pop();
            collide(stack, current);
        }
    }

    private int[] stackToArray(Deque<Integer> stack) {
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}