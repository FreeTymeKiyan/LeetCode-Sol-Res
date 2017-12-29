package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * 234. Palindrome Linked List
 * <p>
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * <p>
 * Company Tags: Amazon, Facebook
 * Tags: Linked List, Two Pointers
 * Similar Problems: (E) Palindrome Number, (E) Valid Palindrome, (E) Reverse Linked List
 */
public class PalindromeLinkedList {

    /**
     * Two Pointers.
     * Find the middle node, reverse the right half list, then check each node.
     * Use two pointers, one slow pointer s, one fast pointer f.
     * Move s to the head of the right half list.
     * If there are odd number of nodes, move s one step further.
     * Reverse the right half of the list starting from s.
     * Then compare each node's value while s is not null.
     * If diff, return false. Else continue.
     * After all nodes checked, return true.
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) { // Odd # of nodes. Make sure slow is always the head of right half.
            slow = slow.next;
        }
        slow = reverseList(slow); // Reverse the right half of the list.
        ListNode cur = head;
        while (slow != null) { // Slow can reach tail early.
            if (cur.val != slow.val) {
                return false;
            }
            cur = cur.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * Iterative.
     * Create a new head as null, which will be the tail of the reversed list.
     * While head is not null:
     * | Store the next node.
     * | Reverse head.
     * | Update new head.
     * | Move to next.
     * Return new head.
     */
    private ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}