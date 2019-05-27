package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * 369. Plus One Linked List
 * <p>
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 * <p>
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * Example :
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * <p>
 * Companies: Google, Amazon
 * <p>
 * Related Topics: Linked List
 * <p>
 * Similar Questions: (E) Plus One
 */
public class PlusOneLinkedList {

  public ListNode plusOne(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode i = dummy;
    ListNode j = dummy;

    while (j.next != null) {
      j = j.next;
      if (j.val != 9) {
        i = j;
      }
    }

    if (j.val != 9) {
      j.val++;
    } else {
      i.val++;
      i = i.next;
      while (i != null) {
        i.val = 0;
        i = i.next;
      }
    }

    if (dummy.val == 0) {
      return dummy.next;
    }

    return dummy;
  }

  public ListNode plusOne2(ListNode head) {
    if (dfs(head) == 0) {
      return head;
    } else {
      ListNode newHead = new ListNode(1);
      newHead.next = head;
      return newHead;
    }
  }

  public int dfs(ListNode head) {
    if (head == null) return 1;

    int carry = dfs(head.next);

    if (carry == 0) return 0;

    int val = head.val + 1;
    head.val = val % 10;
    return val / 10;
  }
}