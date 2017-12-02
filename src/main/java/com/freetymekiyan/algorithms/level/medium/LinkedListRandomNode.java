package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same
 * probability of being chosen.
 * <p>
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without
 * using extra space?
 * <p>
 * Example:
 * <p>
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * <p>
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 * <p>
 * Tags: Reservoir Sampling
 */
public class LinkedListRandomNode {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {

        private final ListNode head;
        private final Random r;
        private ListNode current;

        /**
         * @param head The linked list's head.
         *             Note that the head is guaranteed to be not null, so it contains at least one node.
         */
        public Solution(ListNode head) {
            this.head = head;
            this.current = head;
            r = new Random();
        }

        /**
         * Returns a random node's value.
         */
        public int getRandom() {
            int res = head.val;
            ListNode n = head.next;
            int i = 2;
            while (n != null) {
                if (r.nextInt(i) == 0) { // For ith item, with probability 1/i, replace with new item.
                    res = n.val;
                }
                n = n.next;
                i++;
            }
            return res;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
}
