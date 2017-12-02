package com.freetymekiyan.algorithms.level.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Largest Number
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * Tags: Sort
 * Notes: PriorityQueue and Comparator
 *
 * @author chenshuna
 */

class LargestNumber_Shuna {

    public String largestNumber(int[] nums) {
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            public int compare(String num1, String num2) {
                return (num2 + num1).compareTo(num1 + num2);
            }
        });
        for (int num : nums) {
            queue.offer(num + "");
        }
        StringBuilder sb = new StringBuilder();
        while (queue.size() > 0) {
            sb.append(queue.poll());
        }
        //Need care the special case like [0,0], return 0;  
        if (sb.toString().charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}