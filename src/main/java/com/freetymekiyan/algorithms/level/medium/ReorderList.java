package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * 143. Reorder List
 * <p>
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * <p>
 * Related Topics: Linked List
 */
public class ReorderList {

  public void reorderList(ListNode head) {
    if (head == null) return;
    if (head.next == null) return;

    ListNode mid = head;
    ListNode tail = head;
    while (tail != null && tail.next != null) {
      mid = mid.next;
      tail = tail.next.next;
    }
    ListNode cur = mid.next;
    mid.next = null;
    while (cur != null) {
      ListNode temp = cur.next;
      cur.next = mid.next;
      mid.next = cur;
      cur = temp;
    }
    ListNode left = head;
    ListNode right = mid.next;
    while (right != null) {
      mid.next = right.next;
      right.next = left.next;
      left.next = right;
      left = right.next;
      right = mid.next;
    }
  }
}