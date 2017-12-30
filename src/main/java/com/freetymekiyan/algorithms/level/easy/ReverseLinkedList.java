package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * 206. Reverse Linked List
 * <p>
 * Reverse a singly linked list.
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
     * Divide the list into 2 parts - head and the rest starts from head.next.
     * Reverse the rest of the linked list.
     * Append head to the tail of reversed linked list, which is head's next.
     * Return newHead of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) { // Empty list or just 1 node.
            return head;
        }
        /*
         * Reverse the rest of the list has to be done first.
         * Otherwise we lose the reference to the head of the rest of the linked list.
         * Unless we save it with some other variable.
         */
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // Connect next node with current node.
        head.next = null; // Disconnect current node.
        return newHead;
    }

    /**
     * Iterative.
     * Get one node each time and make it the new head of the reversed list.
     * Create a head of the linked list as null.
     * Use the original head as a pointer to iterate the list.
     * While the original head:
     * | First store the next head.
     * | Then set head.next to newHead.
     * | Move newHead to head.
     * | Move head to its stored next.
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

    /**
     * Iterative.
     * Store the previous node.
     * So that we can connect current node with previous node.
     * Easier to understand.
     */
    public ListNode reverseList3(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}