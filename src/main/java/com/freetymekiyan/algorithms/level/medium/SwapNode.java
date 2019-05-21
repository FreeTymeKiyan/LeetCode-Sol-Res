package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * <p>
 * Tags: Linkedlist
 */
class SwapNode {

  /**
   * create a node at before the head
   * swap two next nodes on the node before them
   */
  public ListNode swapPairs(ListNode head) {
    final ListNode dummy = new ListNode(0); // Create a dummy node to hold the head
    dummy.next = head;
    ListNode cur = dummy; // Start from dummy, the node before head

    while (cur != null && cur.next != null && cur.next.next != null) { // Has at least 2 nodes
      cur.next = swap(cur.next, cur.next.next); // Link current node to the new head
      cur = cur.next.next; // Move to the next node before head
    }

    return dummy.next;
  }

  private ListNode swap(ListNode first, ListNode second) {
    first.next = second.next; // Link first node to node after second
    second.next = first; // Link second node to first
    return second; // Return the new head
  }

  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }
}
