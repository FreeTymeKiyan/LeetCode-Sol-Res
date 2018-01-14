package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 23. Merge k Sorted Lists
 * <p>
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
     * Add all non-null heads to the heap.
     * Create a dummy head and a current pointer c from dummy.
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
        Queue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(n -> n.val));
        for (ListNode n : lists) {
            if (n != null) {
                pq.offer(n);
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
     * 1) If start > end, return null;
     * 2) If start == end, there is only 1 list, return the head of that list;
     * 3) If start == end - 1, there are 2 lists, return the merged list.
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeKLists2(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists2(ListNode[] lists, int s, int e) {
        // Base cases.
        if (s > e) {
            return null;
        }
        if (s == e) { // Only 1 list, just return.
            return lists[s];
        }
        if (s == e - 1) { // 2 lists, merge.
            return mergeTwoLists(lists[s], lists[s + 1]);
        }
        // Merge the two halves: s to s + (e - s) / 2, s + (e - s) / 2 + 1 to e.
        return mergeTwoLists(mergeKLists2(lists, s, s + (e - s) / 2),
                mergeKLists2(lists, s + (e - s) / 2 + 1, e));
    }

    public ListNode mergeKLists3(List<ListNode> lists) {
        /* base cases */
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        if (lists.size() == 2) return mergeTwoLists(lists.get(0), lists.get(1));
        /* merge two halves */
        return mergeTwoLists(mergeKLists3(lists.subList(0, lists.size() / 2)),
                mergeKLists3(lists.subList(lists.size() / 2, lists.size())));
    }

    /**
     * Recursive.
     * Recurrence Relation:
     * Pick the node with smaller value as current head h.
     * Then concatenate h with the merged result of h.next and the other node.
     * Base case:
     * 1. l1 is null, l2 is null, return null
     * 2. l1 is null, l2 is not, return l2.
     * 3. l1 is not, l2 is null, return l1.
     * Combined: l1 is null, return l2. l2 is null, return l1.
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
            l1.next = mergeTwoLists(l1.next, l2); // Merge head.next and the other head.
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}