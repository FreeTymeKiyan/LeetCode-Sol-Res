package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * <p>
 * Only constant memory is allowed.
 * <p>
 * For example,
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Company Tags: Microsoft, Facebook
 * Tags: Linked List
 * Similar Problems: (E) Swap Nodes in Pairs
 */
public class ReverseNodesInKGroup {

    /**
     * Recursive.
     * Recurrence relation:
     * Reverse the current group and connect with reverseKGroup result on the rest of the list.
     * Implementation:
     * Try to find the head of the rest of the list by moving pointer.
     * Increment a counter for each step.
     * If counter != k, the group doesn't exist, return head directly.
     * Else reverse the rest of the list.
     * Store the node
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode restHead = head;
        int count = 0;
        while (restHead != null && count != k) { // Find the head of the rest of the list.
            restHead = restHead.next;
            count++;
        }
        if (count != k) {
            return head;
        }
        ListNode newHead = reverseKGroup(restHead, k); // Recurse on the rest of the list.
        // Head of reversed list. Init as the restHead which is the tail.
        while (count-- > 0) { // Reverse node k times.
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /**
     * Iterative.
     * Divide linked list into two parts:
     * The first K nodes as a group, L1, might not exist.
     * The rest of the linked list, L2.
     * So first we move a pointer to the head of L2.
     * If we reach the end before K step, no more reverse, break and return.
     * If we find the head of L2, restHead, use it as a stop point for reverse.
     * Then we reverse the current group.
     */
    public ListNode reverseKGroupB(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preHead = dummy; // Current group dummy. Or the tail of previous group.
        ListNode curHead = head; // Current group head.

        while (curHead != null) {
            ListNode restHead = curHead;
            int group = k;
            while (restHead != null && group > 0) { // Find nextHead.
                group--;
                restHead = restHead.next;
            }
            if (group > 0) { // Reach list end.
                break;
            }
            // Similar to reverse linked list.
            while (curHead.next != restHead) { // Ends at the head of remaining list.
                ListNode next = curHead.next.next;
                curHead.next.next = preHead.next;
                preHead.next = curHead.next;
                curHead.next = next;
            }
            preHead = curHead; // Move current dummy to the end of current group.
            curHead = curHead.next; // Move current head to the next group.
        }

        return dummy.next;
    }
}
