package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
 * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * <p>
 * Tags: Linked List
 * Similar Problems: (M) Add Two Numbers
 */
public class AddTwoNumbers2 {

    /**
     * Get the lengths of the two lists first, the difference of which can be used to add list nodes.
     * If the diff is 0, the nodes can be added.
     * If the diff is > 0, the longer list node value is kept.
     * The result node can be linked to the next node, which is calculated recursively from the rest of the lists.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = getLength(l1);
        int length2 = getLength(l2);
        ListNode head = new ListNode(1);
        head.next = (length1 >= length2) ? addLists(l1, l2, length1 - length2) : addLists(l2, l1, length2 - length1);
        if (head.next != null && head.next.val > 9) {
            head.next.val = head.next.val % 10;
            return head;
        }
        return head.next;
    }

    private ListNode addLists(ListNode l1, ListNode l2, int offset) {
        if (l1 == null) { // The longer list is null, then both are null.
            return null;
        }
        // Add and check if l1 and l2 are of same length.
        ListNode currentNode = (offset == 0) ? new ListNode(l1.val + l2.val) : new ListNode(l1.val);
        ListNode nextNode = (offset == 0) ? addLists(l1.next, l2.next, 0) : addLists(l1.next, l2, offset - 1);
        // Handle carry.
        if (nextNode != null && nextNode.val > 9) {
            currentNode.val += 1;
            nextNode.val %= 10;
        }
        // Link nodes.
        currentNode.next = nextNode;
        return currentNode;
    }

    private int getLength(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
}
