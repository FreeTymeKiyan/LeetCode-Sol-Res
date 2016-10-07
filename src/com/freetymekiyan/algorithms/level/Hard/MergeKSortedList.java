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
     * Keep track of all heads in a heap, so that we know the next value to be inserted in O(log(k)) time.
     * Create a result list head, and poll from the queue to get the next value.
     * Keep track of the list's tail with a pointer.
     * Add the next of what we polled from queue, if it's not null.
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
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            if (tail.next != null) {
                pq.add(tail.next);
            }
        }
        return dummy.next;
    }

    /**
     * Divide and conquer.
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
     * Get the smaller value of the two lists' heads.
     * The final merged result should be the smaller node concatenate with the merge result of the other nodes.
     * For example, suppose l1 is smaller.
     * Then the result is l1's head + mergeTwoLists(l1.next, l2).
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next); // notice l2.next
            return l2;
        }
    }


}
