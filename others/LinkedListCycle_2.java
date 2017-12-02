package com.freetymekiyan.algorithms.level.medium;

/**
 * Linked List Cycle II
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note: Do not modify the linked list.
 * Tag : Linked List Two Pointers
 * Similar Problems: (M) Linked List Cycle (H) Find the Duplicate Number
 * <p>
 * Analysis:
 * fast point: 2d = x + y + mC
 * slow point: d = x + y + nC
 * x + y = (2n - m) * C
 *
 * @author chenshuna
 */

public class LinkedListCycle_2 {
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (slow == fast) {
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        ListNode res = new ListNode(1);
        res.next = new ListNode(2);
        res.next.next = new ListNode(3);
        res.next.next.next = new ListNode(4);
        res.next.next.next.next = new ListNode(5);
        res.next.next.next.next.next = new ListNode(6);
        res.next.next.next.next.next = res.next.next.next;
        System.out.print(detectCycle(res).val);
    }
}
