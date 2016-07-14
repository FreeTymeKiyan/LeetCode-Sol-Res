package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.ListNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * <p>
 * Tags: Linked List, Two Pointers
 * Similar Problems: (E) Palindrome Number, (E) Valid Palindrome, (E) Reverse Linked List
 */
public class PalindromeLinkedList {

    private PalindromeLinkedList p;

    /**
     * Use two pointers, one slow pointer, one fast pointer.
     * Move slow pointer to the middle.
     * Reverse the right half of the list.
     * Then compare.
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
        if (fast != null) slow = slow.next;
        slow = reverseList(slow);
        while (slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }
        return slow == null;
    }

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

    @Before
    public void setUp() {
        p = new PalindromeLinkedList();
    }

    @Test
    public void testExamples() {
        ListNode h = Utils.buildLinkedList(new int[]{1, 2});
        Assert.assertFalse(p.isPalindrome(h));
    }

    @After
    public void tearDown() {
        p = null;
    }


}
