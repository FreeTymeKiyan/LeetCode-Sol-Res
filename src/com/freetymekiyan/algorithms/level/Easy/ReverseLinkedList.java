package com.freetymekiyan.algorithms.level.easy;

/**
 * Reverse a singly linked list.
 * <p>
 * click to show more hints.
 * <p>
 * Hint:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 * <p>
 * Company Tags: Uber, Facebook, Twitter, Zenefits, Amazon, Microsoft, Snapchat, Apple, Yahoo, Bloomberg, Yelp, Adobe
 * Tags: Linked List
 * Similar Problems: (M) Reverse Linked List II, (M) Binary Tree Upside Down, (E) Palindrome Linked List
 */
public class ReverseLinkedList {

    /**
     * Recursive.
     * <p>
     * Divide the list into 2 parts - head and the rest starts from head.next.
     * Reverse the rest of the linked list.
     * Append head to the tail of reversed linked list, which is head's next.
     * Return newHead of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) { // 1 or no node
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * Iterative.
     * Create a new head.
     * Loop through the list.
     * For each node, get its next first.
     * Point itself to the previous head.
     * Move previous head to current node.
     * Then move to the next node and do the same thing.
     * Stop when reach the end.
     */
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
