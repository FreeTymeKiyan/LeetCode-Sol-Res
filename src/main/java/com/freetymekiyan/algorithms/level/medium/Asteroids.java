package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
 * right, negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If
 * both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * <p>
 * Example 1:
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 * Input:
 * asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 * Example 3:
 * Input:
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 * Example 4:
 * Input:
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 * Note:
 * <p>
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 * <p>
 * Tags: Stack
 * Similar Problems: (E) Can Place Flowers
 */
public class Asteroids {

    /**
     * O(n) Time, O(n) Space.
     * Keep track of collisions using a Stack (represented by Deque in Java).
     * For each asteroid, ast:
     * | If stack is not empty, asteroid size < 0, and previous asteroid size > 0
     * |   Deal with collision.
     * | Else, no collision, just add to stack.
     * After all asteroids are done, convert the resulting stack to an array.
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int ast : asteroids) {
            if (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                collide(stack, ast);
            } else {
                stack.push(ast);
            }
        }
        return stackToArray(stack);
    }

    /**
     * Recursive.
     * Collision will keep happening if:
     * 1. Stack is not empty.
     * 2. Stack top is > 0.
     * 3. Current asteroid is < 0. (Guaranteed when calling collide).
     * Base case:
     * | If stack is empty or stack top < 0, no collision, push current asteroid and stop.
     * Collision:
     * | If same size, pop top from stack since both asteroids are destroyed.
     * | If current is larger, pop top from stack, check if there is further collisions by recursively calling collide.
     * | If current is smaller, stop.
     */
    private void collide(Deque<Integer> stack, int current) {
        if (stack.isEmpty() || stack.peek() < 0) {
            stack.push(current);
            return;
        }
        if (stack.peek() == -current) {
            stack.pop();
        } else if (stack.peek() < -current) {
            stack.pop();
            collide(stack, current);
        }
    }

    /**
     * Non-recursive.
     */
    private void collide2(Deque<Integer> stack, int ast) {
        while (!stack.isEmpty() && stack.peek() > 0 && 0 > ast) {
            if (-ast > stack.peek()) {
                stack.pop();
                continue;
            } else if (-ast == stack.peek()) {
                stack.pop();
            }
            return;
        }
        stack.push(ast);
    }

    private int[] stackToArray(Deque<Integer> stack) {
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    /**
     * Directly using java.util.stack.
     * Not recommended since stack has been deprecated.
     * Create a stack to deal with collisions.
     * For each asteroids, ast:
     * | Check if there is a collision, when stack is not empty, and asteroid size < 0, and previous asteroid > 0
     * |   If yes, compare asteroids' sizes:
     * |     If ast is larger, pop previous asteroid from stack, continue comparison with the next stack top.
     * |     If ast is the same size, just pop since both asteroids are destroyed. Then continue to next asteroid.
     * |     If ast is smaller, it's destroyed. Continue to next asteroid.
     * |   If no, push the current asteroid to the stack and continue to next asteroid.
     * After all asteroids are done, convert the resulting stack to an array.
     */
    public int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                if (stack.peek() < -ast) {
                    stack.pop();
                    continue;
                } else if (stack.peek() == -ast) {
                    stack.pop();
                }
                break;
            }
            stack.push(ast);
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }
}