package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * <p>
 * Tags: Linked List
 * Similar Problems: (E) Remove Element, (E) Delete Node in a Linked List
 */
public class RemoveLinkedListElements {

    private RemoveLinkedListElements r;

    /**
     * Create a dummy head and check the next element.
     * If the element is head, the head needs to be updated.
     * If in the middle, we skip this element by connecting previous one to the next next.
     * If the element is tail, doesn't really matter.
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy, next = head;
        while (next != null) {
            if (next.val == val) {
                cur.next = next.next;
            } else {
                cur = cur.next;
            }
            next = next.next;
        }
        return dummy.next;
    }

    /**
     * Recursive version.
     * Base case: if head is null, just return null.
     * Relation: remove elements from current linked list can be divided into two parts:
     * 1. Remove elements from the rest of the list except head
     * 2. Check whether head should be removed
     * The resulted list should be the combination of them.
     */
    public ListNode removeElementsRecursive(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElementsRecursive(head.next, val);
        return head.val == val ? head.next : head;
    }

    @Before
    public void setUp() {
        r = new RemoveLinkedListElements();
    }

    @Test
    public void testEdgeCases() {
        Assert.assertNull(r.removeElements(null, 0));
        ListNode head = new ListNode(1);
        Assert.assertNull(r.removeElements(head, 1));
        Assert.assertNotNull(r.removeElements(head, 0));
        Assert.assertEquals(1, r.removeElements(head, 0).val);
    }

    @Test
    public void testExamples() {
        // 1 --> 1, val 1
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        n1.next = n2;
        Assert.assertNull(r.removeElements(n1, 1));
        // 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val 6
        n1 = new ListNode(1);
        n2 = new ListNode(2);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode res = r.removeElements(n1, 6);
        Assert.assertNotNull(res);
        int[] vals = {1, 2, 3, 4, 5};
        for (int val : vals) {
            Assert.assertEquals(val, res.val);
            res = res.next;
        }
    }

    @After
    public void tearDown() {
        r = null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
