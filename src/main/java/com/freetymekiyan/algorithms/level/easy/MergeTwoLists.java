package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * 21. Merge Two Sorted Lists
 * <p>
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes
 * of the first two lists.
 * <p>
 * Company Tags: Amazon, LinkedIn, Apple, Microsoft
 * Tags: Linked List
 * Similar Problems: (H) Merge k Sorted Lists, (E) Merge Sorted Array, (M) Sort List, (M) Shortest Word Distance II
 */
public class MergeTwoLists {

    /**
     * Recursive.
     * Pick node with smaller value as the current head.
     * Concat head with the merged result of the rest of the two lists.
     * Base case:
     * If one of the node is null, return the other node.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // The node with smaller value is picked as current head.
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); // Merge the rest.
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * Iterative.
     * Create a dummy head.
     * Create a current pointer initialized at dummy, always keep it at tail.
     * While at least one of l1 and l2 is not null:
     * | If l1 and l2 both exist, compare their values:
     * |   The node with smaller value is the next.
     * |   Set the node as the next of current.
     * |   Then move the node to its next.
     * | If l1 exist, l2 is null
     * |   l1 is the next of current.
     * |   Move l1 to l1.next.
     * | If l1 is null, l2 exist
     * |   l2 is the next of current.
     * |   Move l2 to l2.next.
     * | Move current to current's next.
     * Return dummy's next as head.
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            if ((l1 != null && l2 != null && l1.val < l2.val) || (l1 != null && l2 == null)) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}