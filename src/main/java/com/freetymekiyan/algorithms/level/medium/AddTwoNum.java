package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
 * order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * <p>
 * Company Tags: Amazon, Microsoft, Bloomberg, Airbnb, Adobe
 * Tags: Linked List, Math
 * Similar Problems: (M) Multiply Strings, (E) Add Binary, (E) Sum of Two Integers, (E) Add Strings, (M) Add Two Numbers
 * II
 */
public class AddTwoNum {

    /**
     * Math.
     * Check the two input list first. If one is null, return the other.
     * Now the two heads are not null.
     * Create a dummy node and a current pointer start from dummy.
     * Create an integer to store the carry.
     * While l1 is not null or l2 is not null or carry is not zero:
     * | If l1 is not null:
     * |   Add its value to carry. Move l1 to next.
     * | If l2 is not null:
     * |   Add its value to carry. Move l2 to next.
     * | Create a new node with value carry % 10.
     * | Append new node after current. Move current to next.
     * | Update carry.
     * Return dummy.next.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(carry % 10);
            cur.next = node;
            cur = cur.next;
            carry /= 10;
        }
        return dummy.next;
    }

}
