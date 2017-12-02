package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.ListNode;

/**
 * Partition List
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * <p>
 * Node: create 2 ListNode one for node which is less than target , one for greater.
 * Time Complexity O(n)
 *
 * @author chenshuna
 */
class PartitionList86 {

    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode big = new ListNode(0);
        ListNode b = big;
        ListNode res = small;
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x) {
                small.next = temp;
                small = small.next;
                temp = temp.next;
            } else {
                big.next = temp;
                big = big.next;
                temp = temp.next;
            }
        }
        big.next = null;
        if (b.next != null) {
            small.next = b.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode res = new ListNode(1);
        res.next = new ListNode(2);
        res.next.next = new ListNode(4);
        res.next.next.next = new ListNode(5);
        res.next.next.next.next = new ListNode(3);
        res.next.next.next.next.next = new ListNode(7);
        while (res != null) {
            System.out.print(partition(res, 4).val);
            res = res.next;
        }
    }
}
