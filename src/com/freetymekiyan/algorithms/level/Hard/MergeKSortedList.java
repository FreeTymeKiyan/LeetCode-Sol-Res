package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Company Tags: LinkedIn, Google, Uber, Airbnb, Facebook, Twitter, Amazon, Microsoft
 * Tags: Divide and Conquer, Linked List, Heap
 * Similar Problems: (E) Merge Two Sorted Lists, (M) Ugly Number II
 */
public class MergeKSortedList {

    /**
     * Heap. O(k) + O(n * log(k)) Time, O(k) Space.
     * Keep track of all heads in a min heap, so that we know the next value to be inserted in O(log(k)) time.
     * Create a min heap of ListNode.
     * Add all heads if not null.
     * Create a dummy head and a current pointer c.
     * While heap is not empty:
     * | Set c.next to the node get from heap top.
     * | Move c to c.next.
     * | Now c.next is the new head of that list.
     * | If c.next is not null, add it to heap.
     * Return dummy.next, which is the merged head.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> pq = new PriorityQueue<>(lists.length, (n1, n2) -> n1.val - n2.val);
        for (ListNode n : lists) {
            if (n != null) {
                pq.add(n);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }

    /**
     * Divide and conquer. O(nlogn) Time.
     * Merge k sorted lists can be divided, suppose we have k lists,
     * 1) Merge the first k / 2 lists
     * 2) Merge k / 2 + 1 to k lists
     * Then just implement merge two lists.
     * Base cases:
     * 1) If there is no list, return null;
     * 2) If there is only 1 list, return the head of that list;
     * 3) If there are only 2 lists, return the merge two list result.
     */
    public ListNode mergeKListsB(ListNode[] lists) {
        return mergeKListsB(lists, 0, lists.length - 1);
    }

    public ListNode mergeKListsB(ListNode[] lists, int s, int e) {
        // Base cases
        if (s > e) {
            return null;
        }
        if (s == e) {
            return lists[s];
        }
        if (s == e - 1) {
            return mergeTwoLists(lists[s], lists[s + 1]);
        }
        // Merge two halves
        return mergeTwoLists(mergeKListsB(lists, s, s + (e - s) / 2),
                             mergeKListsB(lists, s + (e - s) / 2 + 1, e));
    }

    /**
     * Recursive.
     * Recurrence Relation:
     * Pick the node with smaller value as current head h.
     * Then concatenate h with the merged result of h.next and the other node.
     * Base case:
     * l1 is null, return l2. l2 is null, return l1.
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // Node with smaller value is the head.
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); // Merge the rest.
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


}
